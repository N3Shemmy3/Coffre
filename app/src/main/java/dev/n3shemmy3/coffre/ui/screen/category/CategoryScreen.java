package dev.n3shemmy3.coffre.ui.screen.category;

import android.os.Bundle;
import android.view.View;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.iconpicker.IconPickerScreen;

public class CategoryScreen extends BaseScreen {


    @Override
    protected int getLayoutResId() {
        return R.layout.screen_category;
    }

    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        topToolBar.setNavigationOnClickListener(view -> Navigator.push(getNavigator(), new IconPickerScreen()));
    }
}
