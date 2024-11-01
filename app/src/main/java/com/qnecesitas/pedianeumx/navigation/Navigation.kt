package com.qnecesitas.pedianeumx.navigation

import android.net.Uri
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.qnecesitas.pedianeumx.ui.camera.CameraView
import com.qnecesitas.pedianeumx.ui.camera.CameraViewModel
import com.qnecesitas.pedianeumx.ui.cropper.CropperView
import com.qnecesitas.pedianeumx.ui.cropper.CropperViewModel
import com.qnecesitas.pedianeumx.ui.main.IMainViewModel
import com.qnecesitas.pedianeumx.ui.result.ResultView
import com.qnecesitas.pedianeumx.ui.result.ResultViewModel
import com.qnecesitas.pedianeumx.ui.splash.SplashView
import okhttp3.Route

@Composable
fun MainNavigation(
    mainViewModel: IMainViewModel
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash.route) {
        generateSplash(navController, mainViewModel)
        generateCamera(navController, mainViewModel)
        generateResult(navController, mainViewModel)
        generateCropper(navController, mainViewModel)
    }


}


fun NavGraphBuilder.generateSplash(
    navController: NavHostController,
    mainViewModel: IMainViewModel
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
    navController: NavHostController,
    mainViewModel: IMainViewModel
){
    composable(
        route = Routes.Camera.route,
        arguments = Routes.Camera.arguments()
    ){


        val cameraViewModel = hiltViewModel<CameraViewModel>()
        cameraViewModel.navController = navController

        CameraView(
            viewModel = cameraViewModel
        )

    }
}

fun NavGraphBuilder.generateResult(
    navController: NavHostController,
    minViewModel: IMainViewModel
){

    composable(
        route = Routes.Result.route,
        arguments = Routes.Result.arguments()
    ){

        val viewModel = hiltViewModel<ResultViewModel>()

        ResultView(viewModel)

    }
}

fun NavGraphBuilder.generateCropper(
    navController: NavHostController,
    minViewModel: IMainViewModel
){

    composable(
        route = Routes.Cropper.route,
        arguments = Routes.Cropper.arguments()
    ){

        val viewModel = hiltViewModel<CropperViewModel>()
        viewModel.navController = navController

        viewModel.GetParam<Uri>(Routes.Cropper.CROPPER_IMAGE_URI_PARAM) { uri->
            viewModel.capturedImageUri = uri
        }

        CropperView(viewModel)

    }
}