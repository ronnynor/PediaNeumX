package com.qnecesitas.pedianeumx.utility

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

object FileUtility {

    fun toMappedFileFromFile(context: Context, fileName: String ): MappedByteBuffer{
        val fileDescriptor = context.assets.openFd(fileName)

        FileInputStream(fileDescriptor.fileDescriptor).use { inputStream ->

            val fileChannel = inputStream.channel
            val startOffset = fileDescriptor.startOffset
            val declaredLength = fileDescriptor.declaredLength
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset,declaredLength )

        }
    }

    fun toBitmapFromUri(
        context: Context,
        imageUri: Uri,
        resizeWidth: Int,
        resizeHeight: Int
    ): Bitmap {
        val inputStream = context.contentResolver.openInputStream(imageUri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream?.close()

        return Bitmap.createScaledBitmap(bitmap, resizeWidth, resizeHeight, true)
    }

    fun toByteBufferFromBitmap(
        bitmap: Bitmap, inputSize: Int
    ): ByteBuffer{
        val byteBuffer = ByteBuffer.allocateDirect(4 * inputSize * inputSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())

        val  intValues = IntArray(inputSize * inputSize)

        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        for (pixelValue in intValues){
            byteBuffer.putFloat(((pixelValue shr 16 and 0xFF) / 255.0f))
            byteBuffer.putFloat(((pixelValue shr 8 and 0xFF) / 255.0f))
            byteBuffer.putFloat(((pixelValue and 0xFF) / 255.0f))
        }
        return byteBuffer
    }

}