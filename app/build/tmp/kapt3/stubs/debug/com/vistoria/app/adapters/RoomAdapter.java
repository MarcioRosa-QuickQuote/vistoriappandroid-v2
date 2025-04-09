package com.vistoria.app.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0014B;\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00020\b2\n\u0010\u000e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\fH\u0016J\u001c\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/vistoria/app/adapters/RoomAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/vistoria/app/adapters/RoomAdapter$RoomViewHolder;", "rooms", "", "Lcom/vistoria/app/models/Room;", "onAddPhotoClick", "Lkotlin/Function1;", "", "onViewPhotosClick", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "RoomViewHolder", "app_debug"})
public final class RoomAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.vistoria.app.adapters.RoomAdapter.RoomViewHolder> {
    private final java.util.List<com.vistoria.app.models.Room> rooms = null;
    private final kotlin.jvm.functions.Function1<com.vistoria.app.models.Room, kotlin.Unit> onAddPhotoClick = null;
    private final kotlin.jvm.functions.Function1<com.vistoria.app.models.Room, kotlin.Unit> onViewPhotosClick = null;
    
    public RoomAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<com.vistoria.app.models.Room> rooms, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.vistoria.app.models.Room, kotlin.Unit> onAddPhotoClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.vistoria.app.models.Room, kotlin.Unit> onViewPhotosClick) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.vistoria.app.adapters.RoomAdapter.RoomViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.vistoria.app.adapters.RoomAdapter.RoomViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012\u00a8\u0006\u0015"}, d2 = {"Lcom/vistoria/app/adapters/RoomAdapter$RoomViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/vistoria/app/adapters/RoomAdapter;Landroid/view/View;)V", "buttonAddPhoto", "Landroid/widget/Button;", "getButtonAddPhoto", "()Landroid/widget/Button;", "buttonViewPhotos", "getButtonViewPhotos", "imageViewRoom", "Landroid/widget/ImageView;", "getImageViewRoom", "()Landroid/widget/ImageView;", "textViewRoomDescription", "Landroid/widget/TextView;", "getTextViewRoomDescription", "()Landroid/widget/TextView;", "textViewRoomName", "getTextViewRoomName", "app_debug"})
    public final class RoomViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView textViewRoomName = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView textViewRoomDescription = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageView imageViewRoom = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.Button buttonAddPhoto = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.Button buttonViewPhotos = null;
        
        public RoomViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getTextViewRoomName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.TextView getTextViewRoomDescription() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.ImageView getImageViewRoom() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.Button getButtonAddPhoto() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.widget.Button getButtonViewPhotos() {
            return null;
        }
    }
}