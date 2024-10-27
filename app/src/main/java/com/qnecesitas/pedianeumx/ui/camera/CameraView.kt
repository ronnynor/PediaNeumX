package com.qnecesitas.pedianeumx.ui.camera

import android.Manifest
import android.net.Uri
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.qnecesitas.pedianeumx.R
import com.qnecesitas.pedianeumx.ui.permissions.PermissionsUI
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun CameraView(
    viewModel: CameraViewModel
){
    val context = LocalContext.current
    val onImageCaptured : (Uri?) -> Unit= { uri->
        viewModel.capturedImageUri = uri
    }

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
        val imageCapture = remember { ImageCapture.Builder().build() }

        CameraPreviewer(
            modifier = Modifier.fillMaxSize(),
            imageCapture = imageCapture
        )

        Crossfade(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(50.dp),
            targetState = viewModel.capturedImageUri,
            label = "Camera CrossFade"
        ) {
            if (viewModel.capturedImageUri != null) {

                Surface(
                    modifier = Modifier.rotate(12f),
                    color = colorResource(R.color.white),
                    shadowElevation = 20.dp
                ) {
                    AsyncImage(
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 4.dp),
                        model = ImageRequest.Builder(context)
                            .data(viewModel.capturedImageUri)
                            .crossfade(true)
                            .build(),
                        contentDescription = stringResource(R.string.radiography),
                        onSuccess = {
                            viewModel.scope.launch{
                                delay(2000)
                                viewModel.toResultView()
                            }
                        }
                    )

                }

            }
        }

        var isPressed by remember { mutableStateOf(false)}
        IconButton(
            modifier = Modifier
                .size(140.dp)
                .align(Alignment.BottomCenter)
                .pointerInput(Unit){
                    detectTapGestures(
                        onPress = {
                            isPressed = true
                            tryAwaitRelease()
                            isPressed = false
                        }
                    )
                }
                .padding(bottom = 40.dp),
            onClick = {
                if(viewModel.capturedImageUri == null) {
                    viewModel.takePhoto(
                        context = context,
                        imageCapture = imageCapture,
                        onImageCaptured = onImageCaptured
                    )
                }
            },
        ){
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = if(isPressed){
                    painterResource(R.drawable.takephoto_pressed)
                }else{
                    painterResource(R.drawable.takephoto)
                },
                contentDescription = stringResource(R.string.take_image)
            )

        }
    }

}



@Composable
fun CameraPreviewer(
    modifier: Modifier = Modifier,
    imageCapture: ImageCapture
){
    val context = LocalContext.current
    Box(modifier = modifier) {
        val lifecycleOwner = LocalLifecycleOwner.current
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
                        previewUseCase.value,
                        imageCapture
                    )
                } catch (e : Exception) {
                    e.printStackTrace()
                }
                previewView
            }
        )
    }

}