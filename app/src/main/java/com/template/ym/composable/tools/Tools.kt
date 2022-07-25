package com.template.ym.composable.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.template.ym.ui.theme.BackgroundIconColor

fun stdModifier(h: Float = 1f, w: Float = 1f): Modifier {
    return Modifier
        .fillMaxHeight(h)
        .fillMaxWidth(w)
        .background(BackgroundIconColor)
        .padding(4.dp)
}