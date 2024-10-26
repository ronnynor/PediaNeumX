package com.qnecesitas.pedianeumx.ui.camera

import android.Manifest
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.qnecesitas.pedianeumx.ui.permissions.PermissionsUI
import com.qnecesitas.pedianeumx.R


@Composable
fun CameraView(
    viewModel: CameraViewModel
){

    PermissionsUI(
        context = LocalContext.current,
        permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ),
        permissionRationale = String(),
        snackbarHostState = remember { SnackbarHostState()},
    ){

    }

    Box(modifier = Modifier.fillMaxSize()) {
        Camera(modifier = Modifier.fillMaxSize())

        Image(
            modifier = Modifier
                .size(140.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp),
            painter = painterResource(R.drawable.take_photo),
            contentDescription = stringResource(R.string.take_image)
        )
    }

}



@Composable
private fun Camera(
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    Box(modifier = modifier) {
        val lifecycleOwner = LocalLifecycleOwner.current
        val coroutineScope = rememberCoroutineScope()
        var previewUseCase = remember { mutableStateOf<UseCase>(Preview.Builder().build()) }
        val cameraProviderFuture = remember {
            ProcessCameraProvider.getInstance(context)
        }
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                val previewView = PreviewView(context).apply {
                    this.scaleType = scaleType
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }

                val selector = CameraSelector.Builder().build()

                previewUseCase.value = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }

                try {
                    cameraProviderFuture.get().unbindAll()
                    cameraProviderFuture.get().bindToLifecycle(
                        lifecycleOwner,
                        selector,
                        previewUseCase.value
                    )
                } catch (e : Exception) {
                    e.printStackTrace()
                }
                previewView
            }
        )
    }

}