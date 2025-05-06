package dev.n3shemmy3.coffre.ui.utils;
/*
 * Copyright (C) 2025 Shemmy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;

public class InsetsUtils {

    public static void applyInsets(@NonNull AppBarLayout appbar, @Nullable MaterialToolbar toolbar, @Nullable CollapsingToolbarLayout topToolBarLayout, @Nullable View fab, @Nullable View content) {
        ViewCompat.setOnApplyWindowInsetsListener(appbar, (v, windowInsets) -> {
            Insets displayCutOutInsets = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
            Insets systemBarInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets imeInsets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());
            int hInsets = displayCutOutInsets.left + displayCutOutInsets.right;

            if (toolbar != null) {
                int horizontalPadding = (int) (Resources.getSystem().getDisplayMetrics().density * (toolbar.getNavigationIcon() == null ? 16 : 8));
                //Toolbar
                toolbar.setPadding(hInsets + horizontalPadding, toolbar.getPaddingTop(), hInsets + horizontalPadding, toolbar.getPaddingBottom());
                appbar.setPadding(appbar.getPaddingLeft(), systemBarInsets.top | displayCutOutInsets.top, appbar.getPaddingRight(), appbar.getPaddingBottom());
            }

            if (topToolBarLayout != null) {
                int initialTitleMargin = (int) (Resources.getSystem().getDisplayMetrics().density * 24);
                topToolBarLayout.setExpandedTitleMarginStart(hInsets + initialTitleMargin);
                topToolBarLayout.setExpandedTitleMarginEnd(hInsets + initialTitleMargin);
            }
            if (fab != null) {
                //Fab
                int initialFabMargin = (int) (Resources.getSystem().getDisplayMetrics().density * 16);
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) fab.getLayoutParams();
                mlp.leftMargin = hInsets + initialFabMargin;
                mlp.rightMargin = hInsets + initialFabMargin;
                mlp.bottomMargin = initialFabMargin + systemBarInsets.bottom + imeInsets.bottom;
                fab.setLayoutParams(mlp);
            }
            if (content != null) {
                if (content instanceof RecyclerView)
                    applyRecyclerInsets((RecyclerView) content);
                else
                    applyContentInsets(content);
            }
            return windowInsets;
        });
    }


    public static void applyRecyclerInsets(@NonNull RecyclerView view) {
        ViewCompat.setOnApplyWindowInsetsListener(view, (v, windowInsets) -> {
            Insets displayCutOutInsets = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
            Insets systemBarInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            int hInsets = displayCutOutInsets.left + displayCutOutInsets.right;
            int leftDisplayCutoutInsets = displayCutOutInsets.left <= 0 ? hInsets : displayCutOutInsets.left;
            int rightDisplayCutoutInsets = displayCutOutInsets.right <= 0 ? hInsets : displayCutOutInsets.right;
            int margin = (int) (Resources.getSystem().getDisplayMetrics().density * 20);
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            mlp.leftMargin = leftDisplayCutoutInsets + margin;
            mlp.rightMargin = rightDisplayCutoutInsets + margin;
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
