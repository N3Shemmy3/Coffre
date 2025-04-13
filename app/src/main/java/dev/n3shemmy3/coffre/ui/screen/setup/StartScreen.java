package dev.n3shemmy3.coffre.ui.screen.setup;

import android.os.Bundle;
import android.view.View;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;

public class StartScreen extends BaseScreen {


    @Override
    protected int getLayoutResId() {
        return R.layout.screen_start;
    }

    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        root.findViewById(R.id.actionStart).setOnClickListener(v-> Navigator.push(getScreenManager(), new SetupScreen()));
    }
}
