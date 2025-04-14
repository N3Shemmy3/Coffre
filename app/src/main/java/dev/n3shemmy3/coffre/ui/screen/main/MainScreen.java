package dev.n3shemmy3.coffre.ui.screen.main;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.currency.CurrencyScreen;
import dev.n3shemmy3.coffre.ui.screen.record.RecordScreen;
import dev.n3shemmy3.coffre.ui.screen.search.SearchScreen;
import dev.n3shemmy3.coffre.ui.screen.settings.SettingsScreen;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public class MainScreen extends BaseScreen {

    private FloatingActionButton fab;
    private ShapeableImageView toolBarAvatar;

    @Override
    protected int getLayoutResId() {
        return R.layout.screen_main;
    }

    @Override
    protected void onCreateScreen(View root, Bundle savedInstanceState) {
        fab = root.findViewById(R.id.fab);
        toolBarAvatar = root.findViewById(R.id.toolBarAvatar);
        toolBarAvatar.setOnClickListener(view -> Navigator.push(getScreenManager(), new SettingsScreen()));
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
            int dp16 = dp8 * 2;
            mlp.leftMargin = hInsets + dp16;
            mlp.rightMargin = hInsets + dp16;
            mlp.bottomMargin = dp16 + systemBarInsets.bottom;
            fab.setLayoutParams(mlp);
        });
        InsetsUtils.applyContentInsets(content);
    }
}
