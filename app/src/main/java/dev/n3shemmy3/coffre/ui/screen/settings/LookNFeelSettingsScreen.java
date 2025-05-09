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

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BasePreferenceFragment;
import dev.n3shemmy3.coffre.ui.base.BaseSettingsScreen;

public class LookNFeelSettingsScreen extends BaseSettingsScreen {
    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        topToolBar.setTitle(R.string.preference_look_and_feel);
        Glide.with(requireContext()).load(R.drawable.undraw_quiet_street).into(headerCover);
        setPreferenceFragment(new LookNFeelPreferencesScreen());
    }

    public static class LookNFeelPreferencesScreen extends BasePreferenceFragment{

        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
            setPreferencesFromResource(R.xml.settings_looknfeel, rootKey);
        }
    }
}
