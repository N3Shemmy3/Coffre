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
import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;
import dev.n3shemmy3.coffre.ui.screen.setup.SetupScreen;
import dev.n3shemmy3.coffre.ui.service.BackupService;

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
        ShapeableImageView itemStartIcon = root.findViewById(R.id.itemStartIcon);
        TextView itemTitle = root.findViewById(R.id.itemTitle);
        Profile profile = new Gson().fromJson(PrefUtil.getString(Profile.key), Profile.class);
        if (profile != null) {
            itemStartIcon.setImageBitmap(FileUtils.retrieveImageFromPrivateStorage(requireContext(), profile.getAvatar()));
            itemTitle.setText(profile.getName());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @Override
    public void onResume() {
        super.onResume();
       // ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.POST_NOTIFICATIONS, android.Manifest.permission.FOREGROUND_SERVICE, android.Manifest.permission.FOREGROUND_SERVICE_DATA_SYNC}, 1);

        Intent intent = new Intent(requireContext(), BackupService.class);
        intent.setAction(BackupService.BACKUP);
        //requireActivity().startService(intent);
    }
}
