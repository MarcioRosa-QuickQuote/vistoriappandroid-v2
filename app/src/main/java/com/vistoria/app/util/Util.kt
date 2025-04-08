package com.vistoria.app.util

import android.graphics.BitmapFactory
import android.widget.ImageView
import java.io.File

fun ImageView.loadImageFromAsset(assetPath: String) {
    val inputStream = context.assets.open(assetPath)
    val bitmap = BitmapFactory.decodeStream(inputStream)
    setImageBitmap(bitmap)
}

fun ImageView.loadImageFromPath(path: String) {
    val file = File(path)
    if (file.exists()) {
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        setImageBitmap(bitmap)
    }
}
