package com.qnecesitas.pedianeumx.ui.main.interfaces

import androidx.compose.foundation.layout.RowScope
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

    fun showDefaultAppNameTitle()

    fun showDefaultStorageOption(onClick: () -> Unit)

}