package com.qnecesitas.pedianeumx.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController

class DefaultSetGetParamsViewModel : IViewModelSetGetParams {
    override lateinit var navController: NavController
    override val currentBackStackEntry: NavBackStackEntry?
        get() = navController.currentBackStackEntry
    override val previousBackStackEntry: NavBackStackEntry?
        get() = navController.previousBackStackEntry
}