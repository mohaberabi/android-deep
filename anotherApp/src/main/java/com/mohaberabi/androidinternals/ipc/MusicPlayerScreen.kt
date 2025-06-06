package com.mohaberabi.androidinternals.ipc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun MusicPlayerScreen(
    modifier: Modifier = Modifier,
    onBind: () -> Unit,
    onUnBind: () -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    currentSong: String,
) {


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Text("AnotherApp")
        Text(
            currentSong, style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onPrevious) { Text("Previous") }
            Spacer(Modifier.width(8.dp))
            Button(onBind) { Text("Play") }
            Spacer(Modifier.width(8.dp))
            Button(onNext) { Text("Next") }

        }
        Button(onUnBind) { Text("Stop") }

    }

}