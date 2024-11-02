package com.qnecesitas.pedianeumx.navigation

sealed class Routes(route: String) : BaseScreen(route = route) {

    data object Splash: Routes(route = "splash")

    data object Camera: Routes(route = "camera")

    data object Result: Routes(route = "result") {
        const val RESULT_IMAGE_URI_PARAM = "IMAGE_URI_PARAM"
    }

    data object Cropper: Routes(route = "cropper"){
        const val CROPPER_IMAGE_URI_PARAM = "IMAGE_URI_PARAM"
    }

}