package dev.n3shemmy3.coffre.ui.utils;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import dev.n3shemmy3.coffre.ui.interfaces.InsetsListener;

public class InsetsUtils {

    public static void applySystemBarsInsets(@NonNull View view, boolean left, boolean top, boolean right, boolean bottom) {
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(left ? systemBars.left : view.getPaddingLeft(), top ? systemBars.top : view.getPaddingTop(), right ? systemBars.right : view.getPaddingRight(), bottom ? v.getPaddingBottom() : view.getPaddingBottom());
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
            return windowInsets;
        });
    }

    public static void applyDisplayCutoutInsets(@NonNull View view, boolean left, boolean top, boolean right, boolean bottom) {
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.displayCutout());
            v.setPadding(left ? systemBars.left : view.getPaddingLeft(), top ? systemBars.top : view.getPaddingTop(), right ? systemBars.right : view.getPaddingRight(), bottom ? v.getPaddingBottom() : view.getPaddingBottom());
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
            return windowInsets;
        });
    }

    public static void applyAppbarInsets(@NonNull AppBarLayout appbar, @Nullable View toolbar, @Nullable CollapsingToolbarLayout collToolbar) {
        ViewCompat.setOnApplyWindowInsetsListener(appbar, (v, windowInsets) -> {
            Insets displayCutOutInsets = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
            Insets systemBarInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            int hInsets = displayCutOutInsets.left + displayCutOutInsets.right;

            if (toolbar != null) {
                //Toolbar
                int dp8 = (int) (Resources.getSystem().getDisplayMetrics().density * 8);
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
                toolbar.setPadding(hInsets + dp8, toolbar.getPaddingTop(), hInsets + dp8, toolbar.getPaddingBottom());
                mlp.topMargin = systemBarInsets.top;
                toolbar.setLayoutParams(mlp);
            }

            if (collToolbar != null) {
                int initialTitleMargin = (int) (Resources.getSystem().getDisplayMetrics().density * 24);
                collToolbar.setExpandedTitleMarginStart(displayCutOutInsets.left + initialTitleMargin);
                collToolbar.setExpandedTitleMarginEnd(displayCutOutInsets.right + initialTitleMargin);
            }
            return windowInsets;
        });
    }


    public static void applyAppbarInsets(@NonNull AppBarLayout appbar, @NonNull InsetsListener insetsListener) {
        ViewCompat.setOnApplyWindowInsetsListener(appbar, (v, windowInsets) -> {
            insetsListener.onInsetsChanged(windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout()), windowInsets.getInsets(WindowInsetsCompat.Type.systemBars()));
            return windowInsets;
        });
    }

    public static void applyAppbarInsets(@NonNull AppBarLayout appbar, @Nullable View toolbar, @Nullable InsetsListener insetsListener) {
        ViewCompat.setOnApplyWindowInsetsListener(appbar, (v, windowInsets) -> {
            Insets displayCutOutInsets = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
            Insets systemBarInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            int hInsets = displayCutOutInsets.left + displayCutOutInsets.right;
            int leftDisplayCutoutInsets = displayCutOutInsets.left <= 0 ? hInsets : displayCutOutInsets.left;
            int rightDisplayCutoutInsets = displayCutOutInsets.right <= 0 ? hInsets : displayCutOutInsets.right;
            if (toolbar != null) {
                toolbar.setPadding(leftDisplayCutoutInsets, toolbar.getPaddingTop(), rightDisplayCutoutInsets + (int) (Resources.getSystem().getDisplayMetrics().density * 8), toolbar.getPaddingBottom());
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
                mlp.topMargin = systemBarInsets.top;
                toolbar.setLayoutParams(mlp);
            }
            if (insetsListener != null) {
                insetsListener.onInsetsChanged(displayCutOutInsets, systemBarInsets);
            }
            return windowInsets;
        });
    }

    public static void applyContentInsets(@NonNull View view) {
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets displayCutOutInsets = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
            Insets systemBarInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            int hInsets = displayCutOutInsets.left + displayCutOutInsets.right;
            int leftDisplayCutoutInsets = displayCutOutInsets.left <= 0 ? hInsets : displayCutOutInsets.left;
            int rightDisplayCutoutInsets = displayCutOutInsets.right <= 0 ? hInsets : displayCutOutInsets.right;
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            mlp.leftMargin = leftDisplayCutoutInsets;
            mlp.rightMargin = rightDisplayCutoutInsets;
            v.setPadding(
                    v.getPaddingLeft(),
                    v.getPaddingTop(),
                    v.getPaddingRight(),
                    systemBarInsets.bottom
            );
            // mlp.bottomMargin = systemBarInsets.bottom;
            v.setLayoutParams(mlp);
            return windowInsets;
        });
    }

    public static void applyImeInsets(@NonNull Window window, @NonNull View rootView) {
        ViewCompat.setOnApplyWindowInsetsListener(window.getDecorView().getRootView(), (v, windowInsets) -> {
            Insets imeInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            //check keyboard
            boolean isKeyboardVisible = windowInsets.isVisible(WindowInsetsCompat.Type.ime());
            //navigationBar height
            int navigationBarHeight = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
            //Keyboard height
            int keyboardHeight = windowInsets.getInsets(WindowInsetsCompat.Type.ime()).bottom;
            // apply Bottom margin to the rootView
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) rootView.getLayoutParams();
            mlp.bottomMargin = isKeyboardVisible ? keyboardHeight : 0;
            rootView.setLayoutParams(mlp);
            return windowInsets;
        });
    }

}
