package com.qnecesitas.pedianeumx.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.qnecesitas.pedianeumx.ui.splash.SplashViewModel

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash.route) {
        generateSplash()
    }


}


fun NavGraphBuilder.generateSplash(){
    composable(
        route = Routes.Splash.route,
        arguments = Routes.Splash.arguments(),
    ){

        Text(
            text = "PediaNeumX",
            style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
            )
    }

}