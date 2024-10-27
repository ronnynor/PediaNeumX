package com.qnecesitas.pedianeumx

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.qnecesitas.pedianeumx.navigation.MainNavigation
import com.qnecesitas.pedianeumx.ui.main.MainTopBar
import com.qnecesitas.pedianeumx.ui.main.MainViewModel
import com.qnecesitas.pedianeumx.ui.main.interfaces.ISystemAppBar
import com.qnecesitas.pedianeumx.ui.main.interfaces.ITopAppBar
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

                handleDefaultTopAppBar(viewModel.topAppBarComposer)
                handleDefaultSystemBar(viewModel.systemAppBarComposer)

                Scaffold(
                    topBar = {MainTopBar(viewModel = viewModel.topAppBarComposer)}
                ){

                    Surface(
                        modifier = Modifier.fillMaxSize(),
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

    fun handleDefaultTopAppBar(topAppBar: ITopAppBar) {
        topAppBar.title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}