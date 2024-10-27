package com.qnecesitas.pedianeumx.ui.camera

import android.content.Context
import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.qnecesitas.pedianeumx.navigation.Routes
import com.qnecesitas.pedianeumx.ui.main.interfaces.IViewModelNavigation
import com.qnecesitas.pedianeumx.ui.main.interfaces.IViewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import java.io.File
import javax.inject.Inject

interface ICameraViewModel :
    IViewModelScope,
    IViewModelNavigation{

    var capturedImageUri: Uri?
    fun takePhoto(
        context: Context,
        imageCapture: ImageCapture,
        onImageCaptured: ((Uri?) -> Unit)? = null,
        onError: ((Exception) -> Unit)? = null,
    )

    fun toResultView()
}

abstract class BaseCameraViewModel :
    ViewModel(), ICameraViewModel

@HiltViewModel
class CameraViewModel @Inject constructor(): BaseCameraViewModel() {

    override var capturedImageUri: Uri? by mutableStateOf(null)
    override lateinit var navController: NavHostController
    override val scope: CoroutineScope
        get() = viewModelScope


    override fun takePhoto(
        context: Context,
        imageCapture: ImageCapture,
        onImageCaptured: ((Uri?) -> Unit)?,
        onError: ((Exception) -> Unit)?
    ) {

        val photoFile = File(
            context.cacheDir,
            "${System.currentTimeMillis()}.jpg"
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri
                    onImageCaptured?.invoke(savedUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    onError?.invoke(exception)
                }

            },
        )

    }

    override fun toResultView(){
        navController.navigate(Routes.Result.route)
    }



}