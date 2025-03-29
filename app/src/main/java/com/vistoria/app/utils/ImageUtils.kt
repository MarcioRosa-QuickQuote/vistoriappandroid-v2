package com.vistoria.app.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import androidx.exifinterface.media.ExifInterface
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Classe utilitária para manipulação de imagens
 */
object ImageUtils {
    private const val TAG = "ImageUtils"
    private const val QUALITY = 85
    private const val MAX_WIDTH = 1600
    private const val MAX_HEIGHT = 1200
    private const val THUMB_WIDTH = 400
    private const val THUMB_HEIGHT = 300
    
    /**
     * Cria um arquivo temporário para a câmera
     */
    @Throws(IOException::class)
    fun createImageFile(context: Context): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }
    
    /**
     * Obtém um Uri para o arquivo de foto
     */
    fun getUriForFile(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }
    
    /**
     * Comprime e otimiza uma imagem
     */
    fun optimizeImage(file: File): File? {
        try {
            // Carregar as dimensões da imagem
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
            BitmapFactory.decodeFile(file.absolutePath, options)
            
            // Calcular fator de escala
            var width = options.outWidth
            var height = options.outHeight
            var scale = 1
            
            while (width > MAX_WIDTH || height > MAX_HEIGHT) {
                width /= 2
                height /= 2
                scale *= 2
            }
            
            // Carregar bitmap com escala reduzida
            val bitmap = if (scale > 1) {
                val scaleOptions = BitmapFactory.Options().apply {
                    inSampleSize = scale
                }
                BitmapFactory.decodeFile(file.absolutePath, scaleOptions)
            } else {
                BitmapFactory.decodeFile(file.absolutePath)
            }
            
            // Corrigir a orientação
            val rotatedBitmap = fixOrientation(bitmap, file.absolutePath)
            
            // Gravar a imagem otimizada
            val optimizedFile = File(file.parentFile, "opt_${file.name}")
            FileOutputStream(optimizedFile).use { out ->
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY, out)
            }
            
            // Limpar recursos
            bitmap.recycle()
            if (bitmap != rotatedBitmap) {
                rotatedBitmap.recycle()
            }
            
            return optimizedFile
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao otimizar imagem: ${e.message}")
            return null
        }
    }
    
    /**
     * Cria uma miniatura da imagem
     */
    fun createThumbnail(file: File): File? {
        try {
            // Carregar as dimensões da imagem
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = true
            }
            BitmapFactory.decodeFile(file.absolutePath, options)
            
            // Calcular fator de escala para thumbnail
            val scale = Math.max(
                options.outWidth / THUMB_WIDTH,
                options.outHeight / THUMB_HEIGHT
            )
            
            // Carregar bitmap com escala reduzida
            val scaleOptions = BitmapFactory.Options().apply {
                inSampleSize = scale
            }
            val bitmap = BitmapFactory.decodeFile(file.absolutePath, scaleOptions)
            
            // Corrigir a orientação
            val rotatedBitmap = fixOrientation(bitmap, file.absolutePath)
            
            // Gravar a thumbnail
            val thumbFile = File(file.parentFile, "thumb_${file.name}")
            FileOutputStream(thumbFile).use { out ->
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY, out)
            }
            
            // Limpar recursos
            bitmap.recycle()
            if (bitmap != rotatedBitmap) {
                rotatedBitmap.recycle()
            }
            
            return thumbFile
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao criar thumbnail: ${e.message}")
            return null
        }
    }
    
    /**
     * Corrige a orientação da imagem com base nos dados EXIF
     */
    private fun fixOrientation(bitmap: Bitmap, path: String): Bitmap {
        try {
            val ei = ExifInterface(path)
            val orientation = ei.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            
            return when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270f)
                ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> flipImage(bitmap, horizontal = true)
                ExifInterface.ORIENTATION_FLIP_VERTICAL -> flipImage(bitmap, horizontal = false)
                else -> bitmap
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao corrigir orientação: ${e.message}")
            return bitmap
        }
    }
    
    /**
     * Rotaciona uma imagem
     */
    private fun rotateImage(bitmap: Bitmap, degrees: Float): Bitmap {
        val matrix = Matrix().apply { postRotate(degrees) }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
    
    /**
     * Espelha uma imagem
     */
    private fun flipImage(bitmap: Bitmap, horizontal: Boolean): Bitmap {
        val matrix = Matrix().apply {
            if (horizontal) {
                postScale(-1f, 1f, bitmap.width / 2f, bitmap.height / 2f)
            } else {
                postScale(1f, -1f, bitmap.width / 2f, bitmap.height / 2f)
            }
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}