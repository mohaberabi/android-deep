package com.mohaberabi.androidinternals

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mohaberabi.androidinternals.ipc.broadcasts.EXPLICIT_ACTION
import com.mohaberabi.androidinternals.ipc.broadcasts.EXTRA_KEY
import com.mohaberabi.androidinternals.ipc.broadcasts.OTHER_APP_PACKAGE
import com.mohaberabi.androidinternals.ipc.MusicPlayerScreen
import com.mohaberabi.androidinternals.ipc.aidl.MusicServiceAidController
import com.mohaberabi.androidinternals.ipc.servicemessenger.MusicMessengerManager
import com.mohaberabi.androidinternals.ui.theme.AndroidInternalsTheme


class MainActivity : ComponentActivity() {

    private val controller = MusicServiceAidController(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val song by controller.currentSong.collectAsState()
            AndroidInternalsTheme {
                Scaffold { padding ->
                    MusicPlayerScreen(
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize(),
                        onBind = { controller.bind() },
                        onNext = { controller.next() },
                        onPrevious = { controller.previous() },
                        onUnBind = { controller.unbind() },
                        currentSong = song,
                    )
                }
            }
        }
    }

    private fun sendExplicitBroadcast() {
        val intent = Intent().apply {
            `package` = OTHER_APP_PACKAGE
            action = EXPLICIT_ACTION
            putExtra(EXTRA_KEY, "Welcome to the ipc section")
        }
        sendBroadcast(intent)
    }
}

