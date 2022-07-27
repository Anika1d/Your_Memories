package com.template.ym.composable.navigation

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.template.ym.composable.screens.initial.AuthScreen
import com.template.ym.composable.screens.initial.RegisterScreen
import com.template.ym.composable.screens.initial.SigInScreen
import com.template.ym.composable.screens.initial.SplashScreen
import com.template.ym.composable.screens.profile.ProfileScreen
import com.template.ym.composable.tools.stdModifier

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@Composable
fun ScreenNavigation(
    navController: NavHostController,
    imageUri: Uri?,
    getImageContent: ActivityResultLauncher<String>
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Route.SplashScreen.route
    ) {
        composable(route = Route.SplashScreen.route) {
            SplashScreen(modifier = stdModifier(), navController = navController)
        }
        composable(route = Route.RegisterScreen.route) {
            RegisterScreen(
                modifier = stdModifier(),
                navController = navController,
                imageUri = imageUri,
                getImageContent = getImageContent
            )
        }
        composable(route = Route.SigInScreen.route) {
            SigInScreen(
                modifier = stdModifier(),
                navController = navController
            )
        }

        composable(route = Route.AuthScreen.route) {
            AuthScreen(
                modifier = stdModifier(),
                navController = navController,
            )
        }
        composable(route = Route.ProfileScreen.route) {
            ProfileScreen(
                modifier = stdModifier(h = 0.94f),
                navController = navController,
            )
        }
    }
}

sealed class Route(val route: String) {
    object RegisterScreen : Route("register_screen")
    object AuthScreen : Route("auth_screen")
    object SigInScreen : Route("sig_in_screen")
    object SplashScreen : Route("splash_screen")
    object ProfileScreen : Route("profile_screen")

}