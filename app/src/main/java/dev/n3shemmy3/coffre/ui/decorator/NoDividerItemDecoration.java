package dev.n3shemmy3.coffre.ui.decorator;

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoDividerItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        // No dividers are drawn
    }
}
