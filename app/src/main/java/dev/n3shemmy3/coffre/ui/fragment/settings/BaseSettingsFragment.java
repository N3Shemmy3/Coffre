package dev.n3shemmy3.coffre.ui.fragment.settings;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.fragment.BaseFragment;


public class BaseSettingsFragment extends BaseFragment {


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_preference;
    }

    @Override
    protected void onFragmentCreated(@NonNull View root, @Nullable Bundle savedInstanceState) {
        super.onFragmentCreated(root, savedInstanceState);
    }
}