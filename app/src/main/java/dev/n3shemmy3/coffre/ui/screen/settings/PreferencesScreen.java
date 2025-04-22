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

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.RecyclerView;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.adapter.PreferencesAdapter;
import dev.n3shemmy3.coffre.ui.item.decorator.VerticalSpaceItemDecoration;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;

public class PreferencesScreen extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.screen_settings, rootKey);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDivider(new ColorDrawable(Color.TRANSPARENT));
        setDividerHeight(0);
        RecyclerView recyclerView = getListView();
        if (recyclerView != null) {
            recyclerView.setClipToPadding(false);
            recyclerView.setClipToOutline(true);
            recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
            recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(4));
        }
    }

    @NonNull
    @Override
    protected RecyclerView.Adapter<PreferenceViewHolder> onCreateAdapter(@NonNull PreferenceScreen preferenceScreen) {
        PreferencesAdapter adapter = new PreferencesAdapter(preferenceScreen);
        adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        return adapter;
    }

    @Override
    public boolean onPreferenceTreeClick(@NonNull Preference preference) {
        switch (preference.getKey()) {
            case "about": {
                Navigator.push(requireActivity().getSupportFragmentManager(), new AboutSettingsScreen());
            }
            break;
        }
        return super.onPreferenceTreeClick(preference);
    }
}
