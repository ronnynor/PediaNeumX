package com.qnecesitas.pedianeumx.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import kotlin.let

interface IViewModelSetGetParams:
    IViewModelNavigation {
    val currentBackStackEntry: NavBackStackEntry?
    val previousBackStackEntry: NavBackStackEntry?

    // didn't make Composable due to mainly being used in VM
    fun <T> setParam(key: String, value: (T)) {
        currentBackStackEntry?.savedStateHandle?.set(
            key, value
        )
    }

    // is composable due to mainly being used in View
    @Composable
    fun <T> GetParam(key: String, action: (T) -> Unit) {
        val isPreview = LocalInspectionMode.current
        if(!isPreview) {
            LaunchedEffect(Unit) {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_RESUME) {
                        val result = previousBackStackEntry?.savedStateHandle?.remove<T>(key)
                        result?.let {
                            action(it)
                        }
                    }
                }

                currentBackStackEntry?.lifecycle?.addObserver(observer)
                currentBackStackEntry?.lifecycle?.addObserver(
                    LifecycleEventObserver { _, event ->
                        if (event == Lifecycle.Event.ON_DESTROY)
                            currentBackStackEntry?.lifecycle?.removeObserver(observer)
                    }
                )
            }
        }
    }
}

val IViewModelSetGetParamsPlaceholder: IViewModelSetGetParams
    get() = object : IViewModelSetGetParams {
        override val currentBackStackEntry: NavBackStackEntry?
            get() = TODO("Not yet implemented")
        override val previousBackStackEntry: NavBackStackEntry?
            get() = TODO("Not yet implemented")
        override var navController: NavController
            get() = TODO("Not yet implemented")
            set(value) {}
    }