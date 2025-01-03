package dev.n3shemmy3.coffre.ui.decorator;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int dimenId;


    public SpacesItemDecoration(Context context, @DimenRes int dimenId) {
        this.dimenId = dimenId;
    }

    @Override
    public void getItemOffsets(Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {
        int space = view.getResources().getDimensionPixelSize(dimenId);
        //outRect.left = space;
        //outRect.right = space;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0) {
        //    outRect.top = space;
        }
    }
}