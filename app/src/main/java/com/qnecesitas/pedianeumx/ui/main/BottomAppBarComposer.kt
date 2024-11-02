package com.qnecesitas.pedianeumx.ui.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.qnecesitas.pedianeumx.R
import com.qnecesitas.pedianeumx.ui.main.interfaces.IBottomAppBar

class BottomAppBarComposer: IBottomAppBar {

    override var visible: Boolean by mutableStateOf(true)
    override var leftActions: @Composable (RowScope) -> Unit by mutableStateOf({})
    override var rightActions: @Composable (RowScope) -> Unit by mutableStateOf({})
    override var backgroundContainerColor: @Composable () -> Color by mutableStateOf({MaterialTheme.colorScheme.primaryContainer})
    override var actionIconContentColor: @Composable () -> Color by mutableStateOf({MaterialTheme.colorScheme.onPrimaryContainer})

    override fun showCropDefaultAction(onClick: () -> Unit){
        rightActions = {
            OutlinedButton(
                onClick = onClick,
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primaryContainer
                ),
            ) {
                Text(
                    text = stringResource(R.string.crop),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        }
    }

    override fun showRepeatDefaultAction(onClick: () -> Unit){
        rightActions = {
            OutlinedButton(
                onClick = onClick,
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primaryContainer
                ),
            ) {
                Text(
                    text = stringResource(R.string.repeat),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        }
    }

}