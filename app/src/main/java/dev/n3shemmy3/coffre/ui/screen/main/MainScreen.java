package dev.n3shemmy3.coffre.ui.screen.main;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.record.RecordScreen;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public class MainScreen extends BaseScreen {

    private FloatingActionButton fab;

    @Override
    protected int getLayoutResId() {
        return R.layout.screen_main;
    }

    @Override
    protected void onCreateScreen(View root, Bundle savedInstanceState) {
        fab = root.findViewById(R.id.fab);
        InsetsUtils.applyAppbarInsets(topAppBar, topToolBar, (
                displayCutOutInsets, systemBarInsets) -> {
            int hInsets = displayCutOutInsets.left + displayCutOutInsets.right;

            //add insets to avatar (yes i want horizontal symmetry)
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) topToolBar.getLayoutParams();
            if (hInsets > 0) {
                mlp.leftMargin = (int) (Resources.getSystem().getDisplayMetrics().density * 16);
                mlp.rightMargin = (int) (Resources.getSystem().getDisplayMetrics().density * 16);
                topToolBar.setLayoutParams(mlp);
            }
            //Fab
            mlp = (ViewGroup.MarginLayoutParams) fab.getLayoutParams();
            mlp.leftMargin = hInsets + mlp.leftMargin;
            mlp.rightMargin = hInsets + mlp.rightMargin;
            mlp.bottomMargin = mlp.bottomMargin + systemBarInsets.bottom;
            fab.setLayoutParams(mlp);
        });
        fab.setOnClickListener(view -> Navigator.push(getNavigator(), new RecordScreen()));
        InsetsUtils.applyContentInsets(content);
    }
}
