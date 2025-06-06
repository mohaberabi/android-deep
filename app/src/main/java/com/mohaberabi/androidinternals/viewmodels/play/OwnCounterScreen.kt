package com.mohaberabi.androidinternals.viewmodels.play

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mohaberabi.androidinternals.viewmodels.api.OwnViewModel


@Composable
fun OwnCounterScreen(
    modifier: Modifier = Modifier,
    viewModel: CounterOwnViewModel,
) {
    val state by viewModel.state.collectAsState()

    Scaffold { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            if (state.loading) {
                CircularProgressIndicator()
            } else {
                Text(state.counter.toString())
                Button(onClick = { viewModel.incrementCounter() }) {
                    Text("+")
                }
            }
        }
    }
}