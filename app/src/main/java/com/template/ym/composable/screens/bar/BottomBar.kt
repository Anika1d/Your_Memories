package com.template.ym.composable.screens.bar

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(modifier: Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.BottomCenter) {
        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(40.dp),
            containerColor = Color.Black

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val sizeButton = 60.dp
                val flagButton =
                    remember { mutableStateListOf(true, false, false) }
                Button(
                    modifier = Modifier.width(sizeButton),
                    enabled = !flagButton[0],
                    onClick = {
                        for (i in 0 until flagButton.size)
                            flagButton[i] = false
                        flagButton[0] = true
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    content = {
                        Text(text = "F", color = Color.White, textAlign = TextAlign.Center)
                    })
                Button(
                    modifier = Modifier.width(sizeButton),
                    enabled = !flagButton[1],
                    onClick = {
                        for (i in 0 until flagButton.size)
                            flagButton[i] = false
                        flagButton[1] = true

                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    content = {
                        Text(
                            text = "M", color = Color.White, textAlign = TextAlign.Center
                        )
                    })
                Button(
                    modifier = Modifier.width(sizeButton),
                    enabled = !flagButton[2],
                    onClick = {
                        for (i in 0 until flagButton.size)
                            flagButton[i] = false
                        flagButton[2] = true

                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    content = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "settings",
                            tint = Color.White
                        )
                    }
                )
            }
        }
    }
}
