package com.qnecesitas.pedianeumx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
                viewModel.topAppBarComposer.title = {
                    Text(
                       text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                }

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