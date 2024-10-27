package com.qnecesitas.pedianeumx.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.qnecesitas.pedianeumx.ui.main.interfaces.IAppBars
import com.qnecesitas.pedianeumx.ui.main.interfaces.ITopAppBar
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface IMainViewModel:
    IAppBars

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel(), IMainViewModel {


    override val topAppBarComposer: ITopAppBar = TopAppBarComposer()


}