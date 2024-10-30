package com.qnecesitas.pedianeumx.ui.result

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.qnecesitas.pedianeumx.utility.FileUtility
import dagger.hilt.android.lifecycle.HiltViewModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.TensorFlowLite
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import javax.inject.Inject

interface IResultViewModel

abstract class BaseResultViewModel: IResultViewModel, ViewModel()

@HiltViewModel
class ResultViewModel @Inject constructor(

): BaseResultViewModel(){

    val imageUri: String = ""
    val modelFileName = "model.tflite"
    val numClasses = 2


    fun initModel(context: Context){
        val imageBitmap = FileUtility.toBitmapFromUri(
            context,
            imageUri = Uri.parse(imageUri),
            resizeWidth = 224,
            resizeHeight = 224
        )

        val tfliteModel = FileUtility.toMappedFileFromFile(context, modelFileName)
        val interpreter = Interpreter(tfliteModel)

        val inputBuffer = FileUtility.toByteBufferFromBitmap(imageBitmap,224)
        val outputBuffer = TensorBuffer.createFixedSize(intArrayOf(1, numClasses), DataType.FLOAT32)

        interpreter.run(inputBuffer, outputBuffer)
        val outputArray = outputBuffer.floatArray

    }


}