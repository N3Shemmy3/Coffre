package dev.n3shemmy3.coffre.ui.screen.settings;
/*
 * Copyright (C) 2025 Shemmy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.entity.Profile;
import dev.n3shemmy3.coffre.ui.adapter.PreferencesAdapter;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.item.decorator.VerticalSpaceItemDecoration;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.setup.SetupScreen;
import dev.n3shemmy3.coffre.ui.utils.FileUtils;
import dev.n3shemmy3.coffre.ui.utils.PrefUtil;

public class SettingsScreen extends BaseScreen {
    @Override
    protected int getLayoutResId() {
        return R.layout.screen_settings;
    }

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        root.findViewById(R.id.settingsContainer).setClipToOutline(true);
        root.findViewById(R.id.item).setOnClickListener(view -> Navigator.push(getScreenManager(), new SetupScreen()));
        ImageView itemStartIcon = root.findViewById(R.id.itemStartIcon);
        TextView itemTitle = root.findViewById(R.id.itemTitle);
        Profile profile = new Gson().fromJson(PrefUtil.getString(Profile.key), Profile.class);
        if (profile != null) {
            itemStartIcon.setImageBitmap(FileUtils.retrieveImageFromPrivateStorage(requireActivity(), profile.getAvatar()));
            itemTitle.setText(profile.getName());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @Override
    public void onResume() {
        super.onResume();

    }

    public static class PreferencesScreen extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
            setPreferencesFromResource(R.xml.settings, rootKey);

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
                case "looknfeel": {
                    Navigator.push(requireActivity().getSupportFragmentManager(), new LookNFeelSettingsScreen());
                }
                break;
                case "formats": {
                    Navigator.push(requireActivity().getSupportFragmentManager(), new FormatSettingsScreen());
                }
                break;
                case "about": {
                    Navigator.push(requireActivity().getSupportFragmentManager(), new AboutSettingsScreen());
                }
                break;
            }
            return super.onPreferenceTreeClick(preference);
        }
    }
}
