/*
 *
 *  * Copyright (C) 2025 Shemmy
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, version 3 of the License.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package dev.n3shemmy3.coffre.ui.screen.settings;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;

import com.bumptech.glide.Glide;

import dev.n3shemmy3.coffre.BuildConfig;
import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BasePreferenceFragment;
import dev.n3shemmy3.coffre.ui.base.BaseSettingsScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.utils.AppUtils;

public class AboutSettingsScreen extends BaseSettingsScreen {

    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        topToolBar.setTitle(R.string.preference_about);
        Glide.with(requireContext()).load(R.drawable.ic_launcher_foreground).into(headerIcon);
        headerTitle.setText(R.string.app_name);
        headerSubtitle.setText(R.string.app_tagline);
        headerIcon.setBackgroundResource(R.drawable.filled_flower_94);
        setPreferenceFragment(new AboutPreferenceScreen());
    }

    public static class AboutPreferenceScreen extends BasePreferenceFragment {
        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
            setPreferencesFromResource(R.xml.settings_about, rootKey);
            Preference version = findPreference("version");
            version.setSummary(BuildConfig.VERSION_NAME);
        }

        @Override
        public boolean onPreferenceTreeClick(@NonNull Preference preference) {
            switch (preference.getKey()) {
                case "github": {
                    AppUtils.openLink(requireContext(), "https://github.com/N3Shemmy3/Coffre");
                }
                break;
                case "latest": {
                    AppUtils.openLink(requireContext(), "https://github.com/N3Shemmy3/Coffre/releases");
                }
                break;
                case "telegram": {
                    AppUtils.openLink(requireContext(), "https://t.me/PixellateProjects");
                }
                break;
                case "licenses": {
                    Navigator.push(requireActivity().getSupportFragmentManager(), new LicensesSettingsScreen());
                }
                break;
                case "version": {
                    AppUtils.copyText(requireContext(), getString(R.string.preference_version), getString(R.string.preference_version) + ": " + BuildConfig.VERSION_NAME);
                }
                break;
            }
            return super.onPreferenceTreeClick(preference);
        }
    }
}
