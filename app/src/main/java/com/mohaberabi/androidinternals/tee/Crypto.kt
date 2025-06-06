package com.mohaberabi.androidinternals.tee

import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec


class AppCrypto() {
    companion object {
        private const val KEY_ALIAS = "mohab"
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }


    private val cipher = Cipher.getInstance(TRANSFORMATION)

    private val keyStore = KeyStore.getInstance("AndroidKeyStore")
        .apply {
            load(null)
        }

    private fun getKey(): SecretKey {
        val existingKey = keyStore.getEntry(
            KEY_ALIAS,
            null
        ) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey {
        val purposes = KeyProperties.PURPOSE_DECRYPT or KeyProperties.PURPOSE_ENCRYPT
        val params = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            purposes
        ).setBlockModes(BLOCK_MODE)
            .setEncryptionPaddings(PADDING)
            .setRandomizedEncryptionRequired(true)
            .apply {
//                if (Build.VERSION.SDK_INT >= 28) {
//                    setIsStrongBoxBacked(true)
//                }
            }
//            .setUserAuthenticationRequired(true)
            .build()
        return KeyGenerator.getInstance(ALGORITHM).apply { init(params) }.generateKey()
    }

    fun encrypt(bytes: ByteArray): ByteArray {
        val key = getKey()
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val iv = cipher.iv
        val encrypted = cipher.doFinal(bytes)
        return iv + encrypted
    }

    fun decrypt(bytes: ByteArray): ByteArray {
        val iv = bytes.copyOfRange(0, cipher.blockSize)
        val encryptedBytes = bytes.copyOfRange(cipher.blockSize, bytes.size)
        val key = getKey()
        cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv))
        return cipher.doFinal(encryptedBytes)
    }
}