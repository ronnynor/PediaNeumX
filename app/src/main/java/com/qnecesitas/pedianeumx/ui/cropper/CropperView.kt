package com.qnecesitas.pedianeumx.ui.cropper

import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.canhub.cropper.CropImageView
import com.qnecesitas.pedianeumx.R

@Composable
fun CropperView(
    viewModel: ICropperViewModel
){

    if(viewModel.capturedImageUri != null) {

        AndroidView(
            factory = { inflaterContext ->
                LayoutInflater.from(inflaterContext).inflate(
                    R.layout.cropper, null
                )
            },
            update = { view ->
                val cropper = view.findViewById<CropImageView>(R.id.cropImageView)
                cropper.setImageUriAsync(viewModel.capturedImageUri)
            }
        )

    }

}
