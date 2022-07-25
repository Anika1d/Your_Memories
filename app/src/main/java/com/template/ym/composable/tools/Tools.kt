package com.template.ym.composable.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.template.ym.ui.theme.BackgroundIconColor

fun stdModifier(): Modifier {
  return  Modifier
        .fillMaxSize()
        .background(BackgroundIconColor)
        .padding(4.dp)
}