package dev.n3shemmy3.coffre.ui.screen.main;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.record.RecordScreen;
import dev.n3shemmy3.coffre.ui.screen.search.SearchScreen;
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
        topToolBar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.action_search) {
                Navigator.push(getScreenManager(), new SearchScreen());
                return true;
            }
            return true;
        });
        fab.setOnClickListener(view -> Navigator.push(getScreenManager(), new RecordScreen()));
        applyInsets();
    }

    private void applyInsets() {
        InsetsUtils.applyAppbarInsets(topAppBar, (displayCutOutInsets, systemBarInsets) -> {
            int hInsets = displayCutOutInsets.left + displayCutOutInsets.right;

            //Toolbar
            int dp8 = (int) (Resources.getSystem().getDisplayMetrics().density * 8);
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) topToolBar.getLayoutParams();
            topToolBar.setPadding(hInsets + dp8, topToolBar.getPaddingTop(), hInsets + dp8, topToolBar.getPaddingBottom());
            mlp.topMargin = systemBarInsets.top;
            topToolBar.setLayoutParams(mlp);

            //Fab
            mlp = (ViewGroup.MarginLayoutParams) fab.getLayoutParams();
            mlp.leftMargin = hInsets + mlp.leftMargin;
            mlp.rightMargin = hInsets + mlp.rightMargin;
            mlp.bottomMargin = mlp.bottomMargin + systemBarInsets.bottom;
            fab.setLayoutParams(mlp);
        });
        InsetsUtils.applyContentInsets(content);
    }
}
