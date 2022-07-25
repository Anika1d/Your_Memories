package com.template.ym.composable.screens.initial

import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import com.template.ym.composable.screens.logo.Logo
import kotlinx.coroutines.delay
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.template.ym.R
import com.template.ym.composable.navigation.Route


@Composable
fun SplashScreen(navController: NavController, modifier: Modifier) {

    var startAnimation by remember { mutableStateOf(false) }
    val sizeAnimation = animateSizeAsState(
        targetValue = if (startAnimation) Size(1f, 1f) else Size(0f, 0f),
        animationSpec = tween(1600)
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1500)
        navController.navigate(Route.SigInScreen.route)
    }
    Box(modifier = modifier, contentAlignment = Alignment.Center)
    {
        Splash(sizeAnimation.value)
    }
}

@Composable
fun Splash(value: Size = Size(1f, 1f)) {
    Image(
        modifier = Modifier
            .fillMaxWidth(value.width)
            .fillMaxHeight(value.height)
            .padding(end = 90.dp),
        painter = painterResource(id = R.drawable.ic_custom_launcher_foreground),
        contentDescription = stringResource(id = R.string.icon_name),
    )
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSplashScreen() {
//    SplashScreen(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(BackgroundIconColor)
//            .padding(end = 90.dp)
//    )
//}