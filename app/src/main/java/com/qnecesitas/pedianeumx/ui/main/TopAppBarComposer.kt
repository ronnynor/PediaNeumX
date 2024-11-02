package com.qnecesitas.pedianeumx.ui.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.qnecesitas.pedianeumx.R
import com.qnecesitas.pedianeumx.ui.main.interfaces.ITopAppBar

class TopAppBarComposer: ITopAppBar {

    override var visible: Boolean by mutableStateOf(false)
    override var leftActions: @Composable (RowScope) -> Unit by mutableStateOf({})
    override var rightActions: @Composable (RowScope) -> Unit by mutableStateOf({})
    override var title: @Composable () -> Unit by mutableStateOf({})
    override var backgroundContainerColor: @Composable () -> Color by mutableStateOf({MaterialTheme.colorScheme.primaryContainer})
    override var titleContentColor: @Composable () -> Color by mutableStateOf({MaterialTheme.colorScheme.onPrimaryContainer})
    override var actionIconContentColor: @Composable () -> Color by mutableStateOf({MaterialTheme.colorScheme.onPrimaryContainer})

    override fun showDefaultAppNameTitle(){
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }

}