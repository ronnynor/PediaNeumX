package com.qnecesitas.pedianeumx.navigation

sealed class Routes(route: String) : BaseScreen(route = route) {

    data object Splash: Routes(route = "splash")

    data object Camera: Routes(route = "camera")

    data object Result: Routes(route = "result")

}