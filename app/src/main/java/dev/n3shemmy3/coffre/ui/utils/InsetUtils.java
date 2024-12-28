package dev.n3shemmy3.coffre.ui.utils;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import dev.n3shemmy3.coffre.R;

public class InsetUtils {


    public static void applySystemBarsInsets(@NonNull View view, boolean left, boolean top, boolean right, boolean bottom) {
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

    public static void applySystemBarsMargin(@NonNull View view, boolean left, boolean top, boolean right, boolean bottom) {
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            mlp.leftMargin = left ? insets.left : mlp.leftMargin;
            mlp.topMargin = top ? insets.top : mlp.topMargin;
            mlp.rightMargin = right ? insets.right : mlp.rightMargin;
            mlp.bottomMargin = bottom ? insets.bottom : mlp.bottomMargin;
            v.setLayoutParams(mlp);
            // Return CONSUMED if you don't want want the window insets to keep passing
            // down to descendant views.
            return WindowInsetsCompat.CONSUMED;
        });
    }

    public static void applyDisplayCutoutInsets(@NonNull View view, boolean left, boolean top, boolean right, boolean bottom) {
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.displayCutout());
            v.setPadding(
                    left ? systemBars.left : view.getPaddingLeft(),
                    top ? systemBars.top : view.getPaddingTop(),
                    right ? systemBars.right : view.getPaddingRight(),
                    bottom ? v.getPaddingBottom() : view.getPaddingBottom()
            );
            return insets;
        });
    }

    public static void applyDisplayCutoutMargin(@NonNull View view, boolean left, boolean top, boolean right, boolean bottom) {
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            int hInsets = insets.left + insets.right;
            mlp.leftMargin = left ? hInsets : mlp.leftMargin;
            mlp.topMargin = top ? insets.top : mlp.topMargin;
            mlp.rightMargin = right ? hInsets : mlp.rightMargin;
            mlp.bottomMargin = bottom ? insets.bottom : mlp.bottomMargin;
            v.setLayoutParams(mlp);
            // Return CONSUMED if you don't want want the window insets to keep passing
            // down to descendant views.
            return WindowInsetsCompat.CONSUMED;
        });
    }

}
