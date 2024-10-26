package com.qnecesitas.pedianeumx.ui.camera

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface ICameraViewModel

abstract class BaseCameraViewModel :
    ViewModel(), ICameraViewModel

@HiltViewModel
class CameraViewModel @Inject constructor(): BaseCameraViewModel() {



}