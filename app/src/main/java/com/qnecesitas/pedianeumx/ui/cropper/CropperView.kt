package com.qnecesitas.pedianeumx.ui.cropper

import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.viewinterop.AndroidView
import com.canhub.cropper.CropImageView
import com.qnecesitas.pedianeumx.R

@Composable
fun CropperView(
    viewModel: ICropperViewModel,
){
    var cropper: CropImageView? by remember { mutableStateOf(null)}
    if(viewModel.capturedImageUri != null) {

        AndroidView(
            factory = { inflaterContext ->
                LayoutInflater.from(inflaterContext).inflate(
                    R.layout.cropper, null
                )
            },
            update = { view ->
                cropper = view.findViewById<CropImageView>(R.id.cropImageView)
                cropper?.setImageUriAsync(viewModel.capturedImageUri)
                cropper?.setOnCropImageCompleteListener{_, result ->
                    if(result.isSuccessful) {
                        result.uriContent?.let {
                            viewModel.onImageCropped(it)
                        }
                    }
                }
            }
        )

    }

    if(viewModel.cropImage){
        cropper?.croppedImageAsync()
    }

}
