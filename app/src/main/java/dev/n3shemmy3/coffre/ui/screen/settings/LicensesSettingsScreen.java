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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

import dev.n3shemmy3.coffre.BuildConfig;
import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.entity.Library;
import dev.n3shemmy3.coffre.ui.adapter.LibrariesAdapter;
import dev.n3shemmy3.coffre.ui.base.BaseFragment;
import dev.n3shemmy3.coffre.ui.base.BaseSettingsScreen;
import dev.n3shemmy3.coffre.ui.item.decorator.VerticalSpaceItemDecoration;
import dev.n3shemmy3.coffre.ui.utils.AssetsUtils;

public class LicensesSettingsScreen extends BaseSettingsScreen {

    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        topToolBar.setTitle(R.string.preference_licenses);
        Glide.with(requireContext()).load(R.drawable.undraw_open_source).into(headerCover);
        setPreferenceFragment(new LibraryPreferencesScreen());
    }

    public static class LibraryPreferencesScreen extends BaseFragment {

        @Override
        protected int getLayoutResId() {
            return R.layout.fragment_recycler;
        }

        private RecyclerView recycler;

        @Override
        public void onCreateFragment(@NonNull View root, @Nullable Bundle savedInstanceState) {
            super.onCreateFragment(root, savedInstanceState);
            recycler = root.findViewById(R.id.recycler);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            LibrariesAdapter adapter = new LibrariesAdapter();
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recycler.setAdapter(adapter);
            recycler.setItemAnimator(new DefaultItemAnimator());
            recycler.setLayoutManager(layoutManager);
            recycler.addItemDecoration(new VerticalSpaceItemDecoration(4));

            String data = AssetsUtils.getAssetJsonData(requireContext(), "libraries.json");
            Type type = new TypeToken<List<Library>>() {
            }.getType();
            List<Library> libraries = new Gson().fromJson(data, type);
            if (libraries == null) return;
            libraries.sort(Comparator.comparing(Library::getName));
            libraries.add(0, new Library(
                    "Shemmy",
                    "https://n3shemy3.dev",
                    getString(R.string.app_name),
                    requireContext().getPackageName(),
                    "Personal finance manager",
                    BuildConfig.VERSION_NAME.replaceAll("[^\\d.]", ""),
                    "https://github.com/FoedusProgramme/Coffre",
                    "General Public License v3.0"
            ));
            adapter.submitList(libraries);
        }

    }

}
