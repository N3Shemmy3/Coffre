package dev.n3shemmy3.coffre.ui.interfaces;

import android.view.View;

import androidx.annotation.NonNull;

public abstract interface ItemListener<T> {
    void onItemClicked(@NonNull View itemView, T item, int position);

    void onItemLongClicked(@NonNull View itemView, T item, int position);
}
