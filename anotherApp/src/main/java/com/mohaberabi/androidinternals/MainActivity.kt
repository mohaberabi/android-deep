package com.mohaberabi.androidinternals

import android.os.Bundle
import android.text.Layout.Alignment
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohaberabi.androidinternals.ipc.provider.WordsProviderClient
import com.mohaberabi.androidinternals.ipc.provider.WordsScreen
import com.mohaberabi.androidinternals.tee.AppCrypto

import com.mohaberabi.androidinternals.ui.theme.AndroidInternalsTheme

class MainActivity : ComponentActivity() {
    val crypto = com.mohaberabi.androidinternals.tee.AppCrypto()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidInternalsTheme {
                CryptoAppScreen(crypto = crypto)
            }
        }

    }
}

@Composable
fun CryptoAppScreen(
    modifier: Modifier = Modifier,
    crypto: com.mohaberabi.androidinternals.tee.AppCrypto
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center
        ) {
            var encrypted by remember {
                mutableStateOf<ByteArray?>(null)
            }
            var decrypted by remember {
                mutableStateOf<ByteArray?>(null)
            }
            Button(
                onClick = {
                    encrypted = crypto.encrypt("Hello world!".encodeToByteArray())
                }
            ) {
                Text("Encrypt")
            }
            encrypted?.let {
                Text(it.decodeToString())
            }
            Button(
                onClick = {
                    encrypted?.let {
                        decrypted = crypto.decrypt(it)
                    }
                }
            ) {
                Text("Decrypt")
            }
            decrypted?.let {
                Text(it.decodeToString())
            }
        }

    }

}