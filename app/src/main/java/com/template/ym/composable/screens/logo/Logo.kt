package com.template.ym.composable.screens.logo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.template.ym.R

@Composable
fun Logo(modifier: Modifier) {
    AsyncImage(
        modifier = modifier,
        model = R.drawable.ic_custom_launcher_foreground,
        contentDescription = stringResource(id = R.string.icon_name),
    )
}