package com.qnecesitas.pedianeumx.ui.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.qnecesitas.pedianeumx.R
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.qnecesitas.pedianeumx.ui.main.interfaces.ITopAppBar
import com.qnecesitas.pedianeumx.ui.theme.commonTopAppBarGradient


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    modifier: Modifier = Modifier,
    viewModel: ITopAppBar
){
    val navigationIcon: @Composable () -> Unit = {
        AnimatedContent(
            targetState = viewModel.leftActions,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "Navigation Icon",
        ) { targetState ->
            Row {
                targetState(this)
            }
        }
    }

    val actionIcon: @Composable () -> Unit = {
        AnimatedContent(
            targetState = viewModel.rightActions,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "App Bar actions"
        ) { targetState ->
            Row {

                targetState(this@Row)
            }
        }
    }

    val titleContent: @Composable () -> Unit = {
        AnimatedContent(
            targetState = viewModel.title,
            transitionSpec = { fadeIn() togetherWith fadeOut() },
            label = "App Bar Title/Icon"
        ) { targetState ->
            targetState()
        }
    }

    val appBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = colorResource(R.color.transparent),
        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    )

    Box(
        modifier.background(brush = commonTopAppBarGradient)
    ) {
        TopAppBar(
            title = titleContent,
            modifier = Modifier.fillMaxWidth(),
            navigationIcon = { navigationIcon },
            actions = { actionIcon },
            colors = appBarColors
        )
    }
}

@Composable
private fun AppNameText(){
    Text(
        text =  stringResource(R.string.app_name),
        style = MaterialTheme.typography.titleLarge.copy(
            color = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    )

}
