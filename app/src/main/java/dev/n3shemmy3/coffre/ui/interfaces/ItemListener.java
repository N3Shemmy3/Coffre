package dev.n3shemmy3.coffre.ui.interfaces;

import android.view.View;

import androidx.annotation.NonNull;

public interface ItemListener {
    void onItemClicked(@NonNull View itemView, Object object, int position);

    void onItemLongClicked(@NonNull View itemView, Object object, int position);
}
