package com.template.ym.composable.screens.initial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.template.ym.composable.screens.logo.Logo
import com.template.ym.ui.theme.BackgroundIconColor

@Composable
fun SplashScreen(modifier: Modifier) {
    Logo(
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundIconColor)
            .padding(end = 90.dp)
    )
}