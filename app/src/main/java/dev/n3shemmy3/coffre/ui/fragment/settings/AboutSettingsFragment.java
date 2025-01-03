package dev.n3shemmy3.coffre.ui.fragment.settings;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import dev.n3shemmy3.coffre.R;

public class AboutSettingsFragment extends BaseSettingsFragment {

    @Override
    public PreferenceFragmentCompat getPreferenceFragment() {
        topToolBar.setTitle(R.string.label_about);
        return new SettingsFragment.PreferencesFragment();
    }
}
