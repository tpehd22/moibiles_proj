package com.example.mobileteamproj

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class TypeConverter {
    @TypeConverter
    fun toByteArray(bitmap: Bitmap?): ByteArray? {
        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, outputStream) ?: return null
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(bytes: ByteArray?): Bitmap? {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes?.size?: return null)
    }
}