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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;

import com.bumptech.glide.Glide;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.viewmodel.MainViewModel;
import dev.n3shemmy3.coffre.ui.base.BasePreferenceFragment;
import dev.n3shemmy3.coffre.ui.base.BaseSettingsScreen;

public class BackupNRestoreSettingsScreen extends BaseSettingsScreen {
    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        topToolBar.setTitle(R.string.preference_backup_n_restore);
        Glide.with(requireContext()).load(R.drawable.undraw_my_files).into(headerCover);
        setPreferenceFragment(new BackupNRestoreSettingsScreen.BackupNRestorePreferenceScreen());
    }

    public static class BackupNRestorePreferenceScreen extends BasePreferenceFragment {

        private MainViewModel viewModel;

        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
            setPreferencesFromResource(R.xml.settings_backup_n_restore, rootKey);
            viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
            Preference backupPreference = findPreference("backup");
            backupPreference.setOnPreferenceClickListener(preference -> {
                Toast.makeText(getContext(), "Creating backup", Toast.LENGTH_SHORT).show();
                viewModel.backup();
                return true;
            });
        }
    }
}
