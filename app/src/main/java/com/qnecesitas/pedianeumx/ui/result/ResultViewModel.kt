package com.qnecesitas.pedianeumx.ui.result

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface IResultViewModel

abstract class BaseResultViewModel: IResultViewModel, ViewModel()

@HiltViewModel
class ResultViewModel @Inject constructor(

): BaseResultViewModel(){



}