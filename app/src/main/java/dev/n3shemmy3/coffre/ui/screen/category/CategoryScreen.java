package dev.n3shemmy3.coffre.ui.screen.category;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.iconpicker.IconPickerScreen;

public class CategoryScreen extends BaseScreen {


    private ShapeableImageView iconSelector;

    @Override
    protected int getLayoutResId() {
        return R.layout.screen_record_new;
    }

    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        createIconPicker();
    }

    void createIconPicker() {
        iconSelector = root.findViewById(R.id.iconSelector);

       // iconSelector.setOnClickListener(view -> Navigator.push(getNavigator(), new IconPickerScreen()));
    }
}
