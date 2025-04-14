package dev.n3shemmy3.coffre.ui;

import android.app.Application;

import com.google.android.material.color.DynamicColors;
import com.mikepenz.iconics.Iconics;

import dev.n3shemmy3.coffre.backend.objectbox.ObjectBox;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
        Iconics.init(getApplicationContext());
    }
}
