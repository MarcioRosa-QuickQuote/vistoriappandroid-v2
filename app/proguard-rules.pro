# Regras do ProGuard para o aplicativo

# Preservar classes e métodos do iText
-keep class com.itextpdf.** { *; }

# Preservar classes de modelos
-keep class com.vistoria.app.models.** { *; }

# Regras gerais para Android
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

# Para o ViewBinding
-keep class * implements androidx.viewbinding.ViewBinding {
    public static *** bind(android.view.View);
    public static *** inflate(android.view.LayoutInflater, android.view.ViewGroup, boolean);
}

# CameraX
-keep class androidx.camera.** { *; }

# Regras para Kotlin coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepnames class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}