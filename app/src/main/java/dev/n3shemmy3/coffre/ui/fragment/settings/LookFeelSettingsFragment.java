package dev.n3shemmy3.coffre.ui.fragment.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import dev.n3shemmy3.coffre.R;

public class LookFeelSettingsFragment extends BaseSettingsFragment {


    @Override
    public PreferenceFragmentCompat getPreferenceFragment() {
        topToolBar.setTitle(R.string.label_look_and_feel);
        return new LookFeelPreferencesFragment();
    }

    public static class LookFeelPreferencesFragment extends BasePreferenceFragment {
        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
            addPreferencesFromResource(R.xml.preferences_look_n_feel);
        }
    }
}
