package dev.n3shemmy3.coffre.ui.fragment.settings;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.fragment.BaseFragment;


public abstract class BaseSettingsFragment extends BaseFragment {

    public Preference preference;

    public BaseSettingsFragment(@NonNull Preference preference) {
        this.preference = preference;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_preference;
    }


    @Override
    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onFragmentCreated(root, savedInstanceState);
        topToolBar.setTitle(preference.getTitle());
    }
}