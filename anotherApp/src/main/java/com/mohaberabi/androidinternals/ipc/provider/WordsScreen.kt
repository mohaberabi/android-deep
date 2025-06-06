package com.mohaberabi.androidinternals.ipc.provider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun WordsScreen(
    modifier: Modifier = Modifier,
    wordsProviderClient: WordsProviderClient
) {

    var list = remember {
        mutableStateListOf<WordModel>()
    }

    LaunchedEffect(Unit) {
        list.addAll(wordsProviderClient.getWords())
    }
    Scaffold { padding ->


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(list) { word ->

                Column {
                    Text("WORDD:${word.word}")
                    Text("DEFINE:${word.define}")

                }
            }
        }
    }

}