package com.mohaberabi.androidinternals.uipipeline.compose

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color


@Composable
fun CustomAnimatedCircle(modifier: Modifier = Modifier) {

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .drawBehind {
                drawOval(
                    color = color,
                    topLeft = Offset(
                        x = size.width * 0.25f,
                        y = size.height * 0.25f
                    ),
                    size = Size(
                        width = size.width * 0.5f,
                        height = size.height * 0.5f
                    )
                )
            }
    )
}