package com.template.ym

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.mutableStateOf
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.template.ym.composable.navigation.ScreenNavigation
import com.template.ym.ui.theme.YMTheme

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {

    val imageUriState = mutableStateOf<Uri?>(null)
    val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUriState.value = uri
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()

            YMTheme {
                ScreenNavigation(
                    navController = navController,
                    imageUri = imageUriState.value,
                    getImageContent = getImageContent)
            }
        }
    }
}
