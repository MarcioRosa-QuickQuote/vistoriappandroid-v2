package com.vistoria.app.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.vistoria.app.models.Inspection
import com.vistoria.app.models.Room
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Classe responsável pela geração de PDFs de inspeção
 */
class PdfGenerator(private val context: Context) {
    
    companion object {
        private const val TAG = "PdfGenerator"
        private const val PAGE_WIDTH = 595 // A4
        private const val PAGE_HEIGHT = 842 // A4
        private const val MARGIN = 40
        private const val PHOTO_WIDTH = 250
        private const val PHOTO_HEIGHT = 170
        private const val TEXT_SIZE_TITLE = 18f
        private const val TEXT_SIZE_SUBTITLE = 14f
        private const val TEXT_SIZE_NORMAL = 12f
    }
    
    /**
     * Gera um relatório PDF da inspeção
     */
    fun generateInspectionPdf(inspection: Inspection, configManager: PreferenceManager): File? {
        val document = PdfDocument()
        val config = configManager.getConfig()
        
        try {
            // Obter a data formatada
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val dateString = dateFormat.format(inspection.createdAt)
            
            // Criar página de capa
            val coverPage = document.startPage(
                PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, 1).create()
            )
            val canvas = coverPage.canvas
            
            // Configurar a cor de fundo
            canvas.drawColor(Color.parseColor(config.backgroundColor))
            
            // Desenhar título
            val titlePaint = Paint().apply {
                color = Color.BLACK
                textSize = TEXT_SIZE_TITLE
                isFakeBoldText = true
                textAlign = Paint.Align.CENTER
            }
            canvas.drawText("RELATÓRIO DE VISTORIA", PAGE_WIDTH / 2f, MARGIN * 2f, titlePaint)
            
            // Desenhar subtítulo com o nome do cliente
            val subtitlePaint = Paint().apply {
                color = Color.BLACK
                textSize = TEXT_SIZE_SUBTITLE
                textAlign = Paint.Align.CENTER
            }
            canvas.drawText(
                "Cliente: ${inspection.clientName}",
                PAGE_WIDTH / 2f,
                MARGIN * 3f,
                subtitlePaint
            )
            
            // Desenhar data
            canvas.drawText(
                "Data: $dateString",
                PAGE_WIDTH / 2f,
                MARGIN * 4f,
                subtitlePaint
            )
            
            // Desenhar nome do inspetor
            if (config.inspectorName.isNotEmpty()) {
                canvas.drawText(
                    "Inspetor: ${config.inspectorName}",
                    PAGE_WIDTH / 2f,
                    MARGIN * 5f,
                    subtitlePaint
                )
            }
            
            // Tentar carregar o logo, se disponível
            if (config.logoUrl.isNotEmpty()) {
                try {
                    val logoBitmap = BitmapFactory.decodeFile(config.logoUrl)
                    
                    // Redimensionar logo mantendo proporção
                    val maxLogoWidth = PAGE_WIDTH - MARGIN * 4
                    val maxLogoHeight = PAGE_HEIGHT / 4
                    
                    val scale = Math.min(
                        maxLogoWidth.toFloat() / logoBitmap.width,
                        maxLogoHeight.toFloat() / logoBitmap.height
                    )
                    
                    val scaledWidth = logoBitmap.width * scale
                    val scaledHeight = logoBitmap.height * scale
                    
                    // Posicionar logo centralizado
                    val left = (PAGE_WIDTH - scaledWidth) / 2
                    val top = PAGE_HEIGHT / 2 - scaledHeight / 2
                    
                    canvas.drawBitmap(
                        logoBitmap,
                        null,
                        android.graphics.Rect(
                            left.toInt(),
                            top.toInt(),
                            (left + scaledWidth).toInt(),
                            (top + scaledHeight).toInt()
                        ),
                        Paint()
                    )
                    
                    logoBitmap.recycle()
                } catch (e: Exception) {
                    Log.e(TAG, "Erro ao carregar logo: ${e.message}")
                }
            }
            
            document.finishPage(coverPage)
            
            // Criar páginas para cada ambiente
            var pageIndex = 2
            for (room in inspection.rooms) {
                createRoomPage(document, room, pageIndex)
                pageIndex++
            }
            
            // Salvar o documento
            val file = createPdfFile()
            FileOutputStream(file).use { out ->
                document.writeTo(out)
            }
            
            return file
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao gerar PDF: ${e.message}", e)
            return null
        } finally {
            document.close()
        }
    }
    
    /**
     * Cria uma página para um ambiente
     */
    private fun createRoomPage(document: PdfDocument, room: Room, pageIndex: Int) {
        val page = document.startPage(
            PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, pageIndex).create()
        )
        val canvas = page.canvas
        
        // Título do ambiente
        val titlePaint = Paint().apply {
            color = Color.BLACK
            textSize = TEXT_SIZE_SUBTITLE
            isFakeBoldText = true
        }
        canvas.drawText(room.name, MARGIN.toFloat(), MARGIN.toFloat(), titlePaint)
        
        // Descrição
        val textPaint = Paint().apply {
            color = Color.BLACK
            textSize = TEXT_SIZE_NORMAL
        }
        
        if (room.description.isNotEmpty()) {
            canvas.drawText(
                "Descrição: ${room.description}",
                MARGIN.toFloat(),
                MARGIN * 1.5f,
                textPaint
            )
        }
        
        // Desenhar as fotos
        if (room.photos.isNotEmpty()) {
            var yPosition = MARGIN * 2f
            
            room.photos.forEachIndexed { index, photo ->
                try {
                    // Carregar a imagem
                    val bitmap = BitmapFactory.decodeFile(photo.url)
                    
                    // Verificar se há espaço na página
                    if (yPosition + PHOTO_HEIGHT + MARGIN > PAGE_HEIGHT) {
                        // Finalizar a página atual e criar uma nova
                        document.finishPage(page)
                        val newPage = document.startPage(
                            PdfDocument.PageInfo.Builder(PAGE_WIDTH, PAGE_HEIGHT, pageIndex + 1).create()
                        )
                        
                        // Atualizar canvas e posição
                        newPage.canvas.drawText(
                            "${room.name} (continuação)",
                            MARGIN.toFloat(),
                            MARGIN.toFloat(),
                            titlePaint
                        )
                        yPosition = MARGIN * 2f
                        
                        document.finishPage(newPage)
                    }
                    
                    // Desenhar a imagem
                    canvas.drawBitmap(
                        bitmap,
                        null,
                        android.graphics.Rect(
                            MARGIN,
                            yPosition.toInt(),
                            MARGIN + PHOTO_WIDTH,
                            (yPosition + PHOTO_HEIGHT).toInt()
                        ),
                        Paint()
                    )
                    
                    // Atualizar posição
                    yPosition += PHOTO_HEIGHT + MARGIN / 2
                    
                    // Liberar recursos
                    bitmap.recycle()
                } catch (e: Exception) {
                    Log.e(TAG, "Erro ao processar foto: ${e.message}")
                    canvas.drawText(
                        "Erro ao carregar foto ${index + 1}",
                        MARGIN.toFloat(),
                        yPosition,
                        textPaint
                    )
                    yPosition += MARGIN / 2
                }
            }
        } else {
            canvas.drawText(
                "Nenhuma foto disponível para este ambiente.",
                MARGIN.toFloat(),
                MARGIN * 2f,
                textPaint
            )
        }
        
        document.finishPage(page)
    }
    
    /**
     * Cria um arquivo para salvar o PDF
     */
    @Throws(IOException::class)
    private fun createPdfFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        
        return File(storageDir, "vistoria_${timeStamp}.pdf")
    }
    
    /**
     * Obtém um Uri para o arquivo PDF
     */
    fun getUriForFile(file: File): Uri {
        return Uri.fromFile(file)
    }
}