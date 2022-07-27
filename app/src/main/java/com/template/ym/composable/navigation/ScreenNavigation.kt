package com.template.ym.composable.navigation

import android.net.Uri
import androidx.activity.result.ActivityResultLauncher
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.template.ym.composable.screens.initial.AuthScreen
import com.template.ym.composable.screens.initial.RegisterScreen
import com.template.ym.composable.screens.initial.SigInScreen
import com.template.ym.composable.screens.initial.SplashScreen
import com.template.ym.composable.screens.memories.ChangeOrCreateMemories
import com.template.ym.composable.screens.memories.InfoMemories
import com.template.ym.composable.screens.memories.ListMemories
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
        composable(route = Route.InfoMemoriesScreen.route) {
            InfoMemories(
                modifier = stdModifier(0.94f),
                navController = navController
            )
        }
        composable(route = Route.ListMemoriesScreen.route) {
            ListMemories(modifier = stdModifier(0.94f), navController = navController)
        }

        composable(
            route = Route.ChangeOrCreateMemoriesScreen.route + "{mode}",
            arguments = listOf(navArgument("mode") { type = NavType.StringType })
        ) { bundle ->
            ChangeOrCreateMemories(
                modifier = stdModifier(0.94f),
                imageUri = imageUri,
                getImageContent = getImageContent,
                navController = navController,
                modeScreen = bundle.arguments?.getString("mode") ?: "create"
            )
        }

    }
}

//fun NavGraphBuilder.memoriesGraph(navController: NavController) {
//    navigation(startDestination = "username", route = "login") {
//        composable(Route.InfoMemoriesScreen.route) { ... }
//        composable(Route.ListMemoriesScreen.route) { ... }
//        composable(Route.ChangeOrCreateMemoriesScreen.route + "mode") {
//
//        }
//    }
//}

sealed class Route(val route: String) {
    object RegisterScreen : Route("register_screen/")
    object AuthScreen : Route("auth_screen/")
    object SigInScreen : Route("sig_in_screen/")
    object SplashScreen : Route("splash_screen/")
    object ProfileScreen : Route("profile_screen/")
    object InfoMemoriesScreen : Route("info_memories_screen/")
    object ChangeOrCreateMemoriesScreen : Route("change_or_create_memories_screen/")
    object ListMemoriesScreen : Route("list_memories_screen/")

}