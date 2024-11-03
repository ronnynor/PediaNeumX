package com.qnecesitas.pedianeumx.navigation

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
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

        LaunchedEffect(Unit) {
            mainViewModel.topAppBarComposer.apply {
                visible = false
            }
            mainViewModel.bottomAppBarComposer.visible = false
        }

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

        LaunchedEffect(Unit) {
            mainViewModel.topAppBarComposer.apply {
                visible = true
                showDefaultAppNameTitle()
                showDefaultStorageOption {
                    cameraViewModel.useStorageImagePicker = true
                }
            }
            mainViewModel.bottomAppBarComposer.visible = false
        }


        CameraView(
            viewModel = cameraViewModel
        )

    }
}

fun NavGraphBuilder.generateResult(
    navController: NavHostController,
    mainViewModel: IMainViewModel
){
    composable(
        route = Routes.Result.route,
        arguments = Routes.Result.arguments()
    ){

        val viewModel = hiltViewModel<ResultViewModel>()
        viewModel.navController = navController
        viewModel.GetParam<Uri>(Routes.Result.RESULT_IMAGE_URI_PARAM) { uri->
            viewModel.capturedImageUri = uri
        }

        LaunchedEffect(Unit) {
            mainViewModel.topAppBarComposer.apply {
                visible = true
                showDefaultAppNameTitle()
                rightActions = {}
            }
            mainViewModel.bottomAppBarComposer.visible = true
            mainViewModel.bottomAppBarComposer.showRepeatDefaultAction {
                viewModel.toCameraView()
            }
        }

        ResultView(viewModel)

    }
}

fun NavGraphBuilder.generateCropper(
    navController: NavHostController,
    mainViewModel: IMainViewModel
){
    composable(
        route = Routes.Cropper.route,
        arguments = Routes.Cropper.arguments()
    ){

        val viewModel = hiltViewModel<CropperViewModel>()
        viewModel.navController = navController

        LaunchedEffect(Unit) {
            mainViewModel.topAppBarComposer.apply {
                visible = true
                showDefaultAppNameTitle()
            }
            mainViewModel.bottomAppBarComposer.apply {
                visible = true
                showCropDefaultAction {
                    viewModel.cropImage = true
                }
            }
        }

        viewModel.GetParam<Uri>(Routes.Cropper.CROPPER_IMAGE_URI_PARAM) { uri->
            viewModel.capturedImageUri = uri
        }

        CropperView(viewModel)

    }
}