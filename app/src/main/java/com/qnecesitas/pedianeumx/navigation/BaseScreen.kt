package com.qnecesitas.pedianeumx.navigation

import androidx.navigation.NamedNavArgument

abstract class BaseScreen(val route: String) {
    open fun createRoute(): String = route
    open fun routeFormat(): String = route
    open fun arguments(): List<NamedNavArgument> = emptyList()
}
