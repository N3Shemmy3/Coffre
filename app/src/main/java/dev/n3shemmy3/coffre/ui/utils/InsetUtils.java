package dev.n3shemmy3.coffre.ui.utils;

import android.view.View;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import dev.n3shemmy3.coffre.R;

public class InsetUtils {


    public static void applySystemBarsInsets(View view, boolean left, boolean top, boolean right, boolean bottom) {
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.topAppBar), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    left ? systemBars.left : view.getPaddingLeft(),
                    top ? systemBars.top : view.getPaddingTop(),
                    right ? systemBars.right : view.getPaddingRight(),
                    bottom ? v.getPaddingBottom() : view.getPaddingBottom()
            );
            return insets;
        });
    }
}
