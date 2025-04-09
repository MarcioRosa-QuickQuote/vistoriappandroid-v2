package com.vistoria.app.adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0013B\'\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00020\b2\n\u0010\r\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u001c\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/vistoria/app/adapter/AmbienteAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/vistoria/app/adapter/AmbienteAdapter$AmbienteViewHolder;", "ambientes", "", "Lcom/vistoria/app/model/Ambiente;", "onItemClick", "Lkotlin/Function1;", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "AmbienteViewHolder", "app_debug"})
public final class AmbienteAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.vistoria.app.adapter.AmbienteAdapter.AmbienteViewHolder> {
    private final java.util.List<com.vistoria.app.model.Ambiente> ambientes = null;
    private final kotlin.jvm.functions.Function1<com.vistoria.app.model.Ambiente, kotlin.Unit> onItemClick = null;
    
    public AmbienteAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<com.vistoria.app.model.Ambiente> ambientes, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.vistoria.app.model.Ambiente, kotlin.Unit> onItemClick) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.vistoria.app.adapter.AmbienteAdapter.AmbienteViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.vistoria.app.adapter.AmbienteAdapter.AmbienteViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/vistoria/app/adapter/AmbienteAdapter$AmbienteViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/vistoria/app/databinding/ItemAmbienteCardBinding;", "(Lcom/vistoria/app/adapter/AmbienteAdapter;Lcom/vistoria/app/databinding/ItemAmbienteCardBinding;)V", "bind", "", "ambiente", "Lcom/vistoria/app/model/Ambiente;", "app_debug"})
    public final class AmbienteViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.vistoria.app.databinding.ItemAmbienteCardBinding binding = null;
        
        public AmbienteViewHolder(@org.jetbrains.annotations.NotNull
        com.vistoria.app.databinding.ItemAmbienteCardBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.vistoria.app.model.Ambiente ambiente) {
        }
    }
}