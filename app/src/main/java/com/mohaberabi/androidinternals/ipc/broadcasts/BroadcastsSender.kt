package com.mohaberabi.androidinternals.ipc.broadcasts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

const val EXPLICIT_ACTION = "com.mohaberabi.androidinternal.explicit.broadcast"
const val OTHER_APP_PACKAGE = "com.mohaberabi.androidinternals.another"
const val PERMISSION = "com.mohaberabi.TOP_SECRET_PERMISSION"
const val EXTRA_KEY = "welcome"

@Composable
fun BroadcastsSender(
    modifier: Modifier = Modifier,
    onSendBroadcast: () -> Unit,
) {


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "You can send broadcasts by clicking the belwo button",
            textAlign = TextAlign.Center
        )

        Button(
            onClick = onSendBroadcast,
        ) {
            Text("Send Broadcast")
        }

    }

}