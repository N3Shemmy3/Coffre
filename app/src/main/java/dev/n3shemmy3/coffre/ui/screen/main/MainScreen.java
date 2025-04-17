package dev.n3shemmy3.coffre.ui.screen.main;
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

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.gson.Gson;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.item.Profile;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.record.RecordScreen;
import dev.n3shemmy3.coffre.ui.screen.search.SearchScreen;
import dev.n3shemmy3.coffre.ui.screen.settings.SettingsScreen;
import dev.n3shemmy3.coffre.ui.utils.FileUtils;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;
import dev.n3shemmy3.coffre.ui.utils.PrefUtil;

public class MainScreen extends BaseScreen {

    private FloatingActionButton fab;
    private ShapeableImageView toolBarAvatar;
    private RelativeLayout transactionsCard;

    @Override
    protected int getLayoutResId() {
        return R.layout.screen_main;
    }

    @Override
    protected void onCreateScreen(View root, Bundle savedInstanceState) {
        toolBarAvatar = root.findViewById(R.id.toolBarAvatar);
        fab = root.findViewById(R.id.fab);
        transactionsCard = root.findViewById(R.id.transactionsCard);
        //toolBarAvatar.setOnClickListener(view -> Navigator.push(getScreenManager(), new SettingsScreen()));
        topToolBar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.action_search) {
                Navigator.push(getScreenManager(), new SearchScreen());

                return true;
            }

            return true;
        });
        transactionsCard.setClipToOutline(true);
        fab.setOnClickListener(view -> Navigator.push(getScreenManager(), new RecordScreen()));
        applyInsets();
        setProfile();
    }

    private void setProfile() {
        Profile profile = new Gson().fromJson(PrefUtil.getString(Profile.key), Profile.class);
        if (profile == null) return;
        toolBarAvatar.setImageBitmap(FileUtils.retrieveImageFromPrivateStorage(requireContext(), profile.getAvatar()));
    }

    private void applyInsets() {
        InsetsUtils.applyAppbarInsets(topAppBar, (windowInsets) -> {
            Insets displayCutOutInsets = windowInsets.getInsets(WindowInsetsCompat.Type.displayCutout());
            Insets systemBarInsets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            Insets imeInsets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());

            int hInsets = displayCutOutInsets.left + displayCutOutInsets.right;

            //Toolbar
            int dp8 = (int) (Resources.getSystem().getDisplayMetrics().density * 8);
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) topToolBar.getLayoutParams();
            topToolBar.setPadding(hInsets + dp8, topToolBar.getPaddingTop(), hInsets + dp8, topToolBar.getPaddingBottom());
            mlp.topMargin = systemBarInsets.top;
            topToolBar.setLayoutParams(mlp);

            //Fab
            mlp = (ViewGroup.MarginLayoutParams) fab.getLayoutParams();
            int dp16 = dp8 * 2;
            mlp.leftMargin = hInsets + dp16;
            mlp.rightMargin = hInsets + dp16;
            mlp.bottomMargin = dp16 + systemBarInsets.bottom + imeInsets.bottom;
            fab.setLayoutParams(mlp);
        });
        InsetsUtils.applyContentInsets(content);
    }
}
