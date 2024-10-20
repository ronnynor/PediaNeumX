package com.qnecesitas.pedianeumx.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.qnecesitas.pedianeumx.ui.splash.SplashView
import kotlinx.coroutines.delay

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash.route) {
        generateSplash(navController)
        generateCamera(navController)
    }


}


fun NavGraphBuilder.generateSplash(
    navController: NavHostController
){
    composable(
        route = Routes.Splash.route,
        arguments = Routes.Splash.arguments(),
        enterTransition = { fadeIn(animationSpec = tween(1000)) },
        exitTransition = { fadeOut(animationSpec = tween(1000)) },
    ){

        SplashView(navController)

    }

}

fun NavGraphBuilder.generateCamera(
    navController: NavHostController
){
    composable(
        route = Routes.Camera.route,
        arguments = Routes.Camera.arguments()
    ){

        Surface(color = Color.White) {

        }

    }
}