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
import com.google.gson.Gson;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.entity.Currency;
import dev.n3shemmy3.coffre.ui.base.BasePreferenceFragment;
import dev.n3shemmy3.coffre.ui.base.BaseSettingsScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.currency.CurrencyScreen;
import dev.n3shemmy3.coffre.ui.utils.CurrencyUtils;
import dev.n3shemmy3.coffre.ui.utils.PrefUtil;

public class FormatSettingsScreen extends BaseSettingsScreen {

    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        topToolBar.setTitle(R.string.preference_format);
        Glide.with(requireContext()).load(R.drawable.undraw_setup).into(headerCover);
        setPreferenceFragment(new FormatPreferenceScreen());
    }

    public static class FormatPreferenceScreen extends BasePreferenceFragment {

        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
            setPreferencesFromResource(R.xml.settings_format, rootKey);
            Currency currency = CurrencyUtils.getCurrency();

            Preference currencyPreference = findPreference("currency");
            currencyPreference.setSummary(currency.getName());
        }

        @Override
        public boolean onPreferenceTreeClick(@NonNull Preference preference) {
            switch (preference.getKey()) {
                case "currency": {
                    Navigator.push(requireActivity().getSupportFragmentManager(), new CurrencyScreen());
                }
                break;
            }
            return super.onPreferenceTreeClick(preference);

        }
    }
}
