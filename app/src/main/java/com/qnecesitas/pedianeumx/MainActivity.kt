package com.qnecesitas.pedianeumx

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.qnecesitas.pedianeumx.navigation.MainNavigation
import com.qnecesitas.pedianeumx.ui.main.MainBottomBar
import com.qnecesitas.pedianeumx.ui.main.MainTopBar
import com.qnecesitas.pedianeumx.ui.main.MainViewModel
import com.qnecesitas.pedianeumx.ui.main.interfaces.ISystemAppBar
import com.qnecesitas.pedianeumx.ui.theme.PediaNeumXTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //enableEdgeToEdge()
        setContent {
            PediaNeumXTheme {

                val viewModel = hiltViewModel<MainViewModel>()

                handleDefaultSystemBar(viewModel.systemAppBarComposer)

                Scaffold(
                    topBar = {
                        if (viewModel.topAppBarComposer.visible) {
                            MainTopBar(viewModel = viewModel.topAppBarComposer)
                        }
                    },
                    bottomBar = {
                        if (viewModel.bottomAppBarComposer.visible) {
                            MainBottomBar(viewModel = viewModel.bottomAppBarComposer)
                        }
                    }
                ){ innerPadding->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MainNavigation(viewModel)
                    }

                }


            }
        }
    }

    @Composable
    fun handleDefaultSystemBar(systemAppBar: ISystemAppBar) {
        systemAppBar.statusBarColor = MaterialTheme.colorScheme.background
        systemAppBar.statusBarUseDarkIcons = true

        val view = LocalView.current
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                systemAppBar.statusBarColor?.let {
                    window.statusBarColor = it.toArgb()
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    val insetsController = window.insetsController
                    if (systemAppBar.statusBarUseDarkIcons) {
                        insetsController?.setSystemBarsAppearance(
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        )
                    } else {
                        insetsController?.setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS)
                    }
                } else {
                    ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = !systemAppBar.statusBarUseDarkIcons
                }
            }
        }


    }

}