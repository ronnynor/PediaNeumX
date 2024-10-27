package com.qnecesitas.pedianeumx.ui.main.interfaces

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.FabPosition
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

interface ITopAppBar {
    var visible: Boolean
    var leftActions: @Composable (RowScope) -> Unit
    var title: @Composable () -> Unit
    var rightActions: @Composable (RowScope) -> Unit
    var backgroundContainerColor: @Composable () -> Color
    var titleContentColor: @Composable () -> Color
    var actionIconContentColor: @Composable () -> Color
}