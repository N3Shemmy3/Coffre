package dev.n3shemmy3.coffre.ui.screen.record;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public class RecordScreen extends BaseScreen {


    @Override
    protected int getLayoutResId() {
        return R.layout.screen_record;
    }

    @Override
    protected void onScreenCreated(View root, Bundle savedInstanceState) {
        super.onScreenCreated(root, savedInstanceState);

    }
}
