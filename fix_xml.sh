#!/bin/bash
cat > /workspaces/vistoriappandroid-v2/app/src/main/res/layout/item_photo_thumbnail.xml <<INNER_EOF
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="100dp"
    android:layout_height="100dp"
    app:cardCornerRadius="8dp">
    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scaleType="centerCrop"
        android:src="@drawable/ic_image_placeholder" />
</androidx.cardview.widget.CardView>
INNER_EOF
