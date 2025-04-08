package com.vistoria.app.utils;

/**
 * Classe utilitária para manipulação de imagens
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0010\u001a\u00020\fJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\bH\u0002J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\fJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0010\u001a\u00020\fJ\u0018\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001e"}, d2 = {"Lcom/vistoria/app/utils/ImageUtils;", "", "()V", "MAX_HEIGHT", "", "MAX_WIDTH", "QUALITY", "TAG", "", "THUMB_HEIGHT", "THUMB_WIDTH", "createImageFile", "Ljava/io/File;", "context", "Landroid/content/Context;", "createThumbnail", "file", "fixOrientation", "Landroid/graphics/Bitmap;", "bitmap", "path", "flipImage", "horizontal", "", "getUriForFile", "Landroid/net/Uri;", "optimizeImage", "rotateImage", "degrees", "", "app_debug"})
public final class ImageUtils {
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String TAG = "ImageUtils";
    private static final int QUALITY = 85;
    private static final int MAX_WIDTH = 1600;
    private static final int MAX_HEIGHT = 1200;
    private static final int THUMB_WIDTH = 400;
    private static final int THUMB_HEIGHT = 300;
    @org.jetbrains.annotations.NotNull
    public static final com.vistoria.app.utils.ImageUtils INSTANCE = null;
    
    private ImageUtils() {
        super();
    }
    
    /**
     * Cria um arquivo temporário para a câmera
     */
    @kotlin.jvm.Throws(exceptionClasses = {java.io.IOException.class})
    @org.jetbrains.annotations.NotNull
    public final java.io.File createImageFile(@org.jetbrains.annotations.NotNull
    android.content.Context context) throws java.io.IOException {
        return null;
    }
    
    /**
     * Obtém um Uri para o arquivo de foto
     */
    @org.jetbrains.annotations.NotNull
    public final android.net.Uri getUriForFile(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.io.File file) {
        return null;
    }
    
    /**
     * Comprime e otimiza uma imagem
     */
    @org.jetbrains.annotations.Nullable
    public final java.io.File optimizeImage(@org.jetbrains.annotations.NotNull
    java.io.File file) {
        return null;
    }
    
    /**
     * Cria uma miniatura da imagem
     */
    @org.jetbrains.annotations.Nullable
    public final java.io.File createThumbnail(@org.jetbrains.annotations.NotNull
    java.io.File file) {
        return null;
    }
    
    /**
     * Corrige a orientação da imagem com base nos dados EXIF
     */
    private final android.graphics.Bitmap fixOrientation(android.graphics.Bitmap bitmap, java.lang.String path) {
        return null;
    }
    
    /**
     * Rotaciona uma imagem
     */
    private final android.graphics.Bitmap rotateImage(android.graphics.Bitmap bitmap, float degrees) {
        return null;
    }
    
    /**
     * Espelha uma imagem
     */
    private final android.graphics.Bitmap flipImage(android.graphics.Bitmap bitmap, boolean horizontal) {
        return null;
    }
}