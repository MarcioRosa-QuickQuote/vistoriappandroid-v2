package com.vistoria.app.adapters;

/**
 * Adaptador para exibir miniaturas de fotos na tela da câmera
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u0015\u0016B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u001c\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u001c\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016J\u0014\u0010\u0013\u001a\u00020\f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/vistoria/app/adapters/PhotoThumbnailAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/vistoria/app/adapters/PhotoThumbnailAdapter$PhotoThumbnailViewHolder;", "photos", "", "Lcom/vistoria/app/models/Photo;", "listener", "Lcom/vistoria/app/adapters/PhotoThumbnailAdapter$PhotoThumbnailClickListener;", "(Ljava/util/List;Lcom/vistoria/app/adapters/PhotoThumbnailAdapter$PhotoThumbnailClickListener;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updatePhotos", "newPhotos", "PhotoThumbnailClickListener", "PhotoThumbnailViewHolder", "app_debug"})
public final class PhotoThumbnailAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.vistoria.app.adapters.PhotoThumbnailAdapter.PhotoThumbnailViewHolder> {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.vistoria.app.models.Photo> photos;
    @org.jetbrains.annotations.NotNull
    private final com.vistoria.app.adapters.PhotoThumbnailAdapter.PhotoThumbnailClickListener listener = null;
    
    public PhotoThumbnailAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<com.vistoria.app.models.Photo> photos, @org.jetbrains.annotations.NotNull
    com.vistoria.app.adapters.PhotoThumbnailAdapter.PhotoThumbnailClickListener listener) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.vistoria.app.adapters.PhotoThumbnailAdapter.PhotoThumbnailViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.vistoria.app.adapters.PhotoThumbnailAdapter.PhotoThumbnailViewHolder holder, int position) {
    }
    
    /**
     * Atualiza a lista de fotos
     */
    public final void updatePhotos(@org.jetbrains.annotations.NotNull
    java.util.List<com.vistoria.app.models.Photo> newPhotos) {
    }
    
    /**
     * Interface para comunicação com o fragmento pai
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\t"}, d2 = {"Lcom/vistoria/app/adapters/PhotoThumbnailAdapter$PhotoThumbnailClickListener;", "", "onThumbnailClick", "", "photo", "Lcom/vistoria/app/models/Photo;", "position", "", "onThumbnailDeleteClick", "app_debug"})
    public static abstract interface PhotoThumbnailClickListener {
        
        public abstract void onThumbnailClick(@org.jetbrains.annotations.NotNull
        com.vistoria.app.models.Photo photo, int position);
        
        public abstract void onThumbnailDeleteClick(@org.jetbrains.annotations.NotNull
        com.vistoria.app.models.Photo photo, int position);
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2 = {"Lcom/vistoria/app/adapters/PhotoThumbnailAdapter$PhotoThumbnailViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/vistoria/app/adapters/PhotoThumbnailAdapter;Landroid/view/View;)V", "imageButtonDelete", "Landroid/widget/ImageButton;", "getImageButtonDelete", "()Landroid/widget/ImageButton;", "imageViewThumbnail", "Landroid/widget/ImageView;", "getImageViewThumbnail", "()Landroid/widget/ImageView;", "app_debug"})
    public final class PhotoThumbnailViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView imageViewThumbnail = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton imageButtonDelete = null;
        
        public PhotoThumbnailViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getImageViewThumbnail() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageButton getImageButtonDelete() {
            return null;
        }
    }
}