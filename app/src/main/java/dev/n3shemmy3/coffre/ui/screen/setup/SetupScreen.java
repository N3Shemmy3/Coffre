package dev.n3shemmy3.coffre.ui.screen.setup;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.currency.CurrencyScreen;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public class SetupScreen extends BaseScreen {

    private CollapsingToolbarLayout toolbarLayout;
    private Button actionStart;

    @Override
    protected int getLayoutResId() {
        return R.layout.screen_setup;
    }


    @Override
    protected void onCreateScreen(View root, Bundle state) {
        actionStart = root.findViewById(R.id.actionStart);
        toolbarLayout = root.findViewById(R.id.toolbarLayout);

        actionStart.setOnClickListener(v -> Navigator.push(getScreenManager(), new CurrencyScreen()));
        applyInsets();
    }

    private void applyInsets() {
        InsetsUtils.applyAppbarInsets(topAppBar, (displayCutOutInsets, systemBarInsets) -> {
            int hInsets = displayCutOutInsets.left + displayCutOutInsets.right;

            //Toolbar
            int dp8 = (int) (Resources.getSystem().getDisplayMetrics().density * 8);
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) topToolBar.getLayoutParams();
            topToolBar.setPadding(hInsets + dp8, topToolBar.getPaddingTop(), hInsets + dp8, topToolBar.getPaddingBottom());
            toolbarLayout.setPadding(hInsets + dp8, toolbarLayout.getPaddingTop(), hInsets + dp8, toolbarLayout.getPaddingBottom());
            mlp.topMargin = systemBarInsets.top;
            topToolBar.setLayoutParams(mlp);

            //Fab
            mlp = (ViewGroup.MarginLayoutParams) actionStart.getLayoutParams();
            int dp16 = dp8 * 2;
            mlp.leftMargin = hInsets + dp16;
            mlp.rightMargin = hInsets + dp16;
            mlp.bottomMargin = dp16 + systemBarInsets.bottom;
            actionStart.setLayoutParams(mlp);
        });
        InsetsUtils.applyContentInsets(content);
    }
}
