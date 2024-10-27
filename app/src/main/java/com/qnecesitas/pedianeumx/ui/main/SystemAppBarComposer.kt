package com.qnecesitas.pedianeumx.ui.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.qnecesitas.pedianeumx.ui.main.interfaces.ISystemAppBar
import com.qnecesitas.pedianeumx.ui.main.interfaces.ITopAppBar

class SystemAppBarComposer: ISystemAppBar {
    override var statusBarUseDarkIcons: Boolean by mutableStateOf(false)
    override var statusBarColor: Color? by mutableStateOf(null)
//    override val navigationBarColor: Color? by mutableStateOf(null)
//    override val navigationBarUseDarkIcons: Boolean by mutableStateOf(false)
}