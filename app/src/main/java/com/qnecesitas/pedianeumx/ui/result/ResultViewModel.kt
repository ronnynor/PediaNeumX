package com.qnecesitas.pedianeumx.ui.result

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.qnecesitas.pedianeumx.datamodel.Diagnosis
import com.qnecesitas.pedianeumx.navigation.DefaultSetGetParamsViewModel
import com.qnecesitas.pedianeumx.navigation.IViewModelSetGetParams
import com.qnecesitas.pedianeumx.utility.FileUtility
import dagger.hilt.android.lifecycle.HiltViewModel
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.TensorFlowLite
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import javax.inject.Inject

interface IResultViewModel:
    IViewModelSetGetParams
{
    var capturedImageUri: Uri?
    val diagnosisResult: Diagnosis?
    val diagnosisPrecision: Double?
}

abstract class BaseResultViewModel:
    IResultViewModel,
    IViewModelSetGetParams by DefaultSetGetParamsViewModel(),
    ViewModel()


@HiltViewModel
class ResultViewModel @Inject constructor(

): BaseResultViewModel(){

    override var capturedImageUri: Uri? by mutableStateOf(null)

    private val modelFileName = "model.tflite"
    private val numClasses = 2

    override val diagnosisResult: Diagnosis? by mutableStateOf(null)
    override val diagnosisPrecision: Double by mutableDoubleStateOf(0.0)


    fun initModel(context: Context){
        capturedImageUri?.let { capturedImageUri->
            val imageBitmap = FileUtility.toBitmapFromUri(
                context,
                imageUri = capturedImageUri,
                resizeWidth = 224,
                resizeHeight = 224
            )

            val tfliteModel = FileUtility.toMappedFileFromFile(context, modelFileName)
            val interpreter = Interpreter(tfliteModel)

            val inputBuffer = FileUtility.toByteBufferFromBitmap(imageBitmap, 224)
            val outputBuffer =
                TensorBuffer.createFixedSize(intArrayOf(1, numClasses), DataType.FLOAT32)

            interpreter.run(inputBuffer, outputBuffer)
            val outputArray = outputBuffer.floatArray

        }
    }


}