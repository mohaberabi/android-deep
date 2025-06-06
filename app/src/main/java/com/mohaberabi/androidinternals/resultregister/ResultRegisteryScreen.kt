package com.mohaberabi.androidinternals.resultregister

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ResultRegisteryScreen(
    modifier: Modifier = Modifier,
    greet: String? = null,
    onStartActivity: () -> Unit,
) {


    Scaffold { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {

            TextButton(
                onClick = onStartActivity,
            ) { Text("Start Another Activity") }
            greet?.let {
                Text(
                    it,
                )
            } ?: Text("We are waiting for the other activity to greet us ")


        }
    }
}