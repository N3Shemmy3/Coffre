package dev.n3shemmy3.coffre.ui.fragment.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import dev.n3shemmy3.coffre.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public boolean onPreferenceTreeClick(@NonNull Preference preference) {
        switch (preference.getKey()) {

        }
        return super.onPreferenceTreeClick(preference);
    }
}
