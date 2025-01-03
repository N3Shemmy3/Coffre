package dev.n3shemmy3.coffre.ui.fragment.settings;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentContainerView;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.fragment.BaseFragment;
import dev.n3shemmy3.coffre.ui.utils.InsetUtils;
import dev.n3shemmy3.coffre.ui.utils.Navigator;


public abstract class BaseSettingsFragment extends BaseFragment {

    public abstract PreferenceFragmentCompat getPreferenceFragment();


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_preference;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Navigator.add(R.id.SettingsContainer, getChildFragmentManager(), getPreferenceFragment());
    }
}