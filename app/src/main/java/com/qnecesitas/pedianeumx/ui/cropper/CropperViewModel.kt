package com.qnecesitas.pedianeumx.ui.cropper

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.qnecesitas.pedianeumx.navigation.DefaultSetGetParamsViewModel
import com.qnecesitas.pedianeumx.navigation.IViewModelSetGetParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface ICropperViewModel:
    IViewModelSetGetParams{

    var capturedImageUri: Uri?

    }

abstract class BaseCropperViewModel:
    ICropperViewModel,
    IViewModelSetGetParams by DefaultSetGetParamsViewModel(),
    ViewModel()

@HiltViewModel
class CropperViewModel @Inject constructor(

): BaseCropperViewModel() {

    override var capturedImageUri: Uri? by mutableStateOf(null)

}