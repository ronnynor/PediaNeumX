package com.qnecesitas.pedianeumx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.qnecesitas.pedianeumx.navigation.MainNavigation
import com.qnecesitas.pedianeumx.ui.main.MainTopBar
import com.qnecesitas.pedianeumx.ui.main.MainViewModel
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
}