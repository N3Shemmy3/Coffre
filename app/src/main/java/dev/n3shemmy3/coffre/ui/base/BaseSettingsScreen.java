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

package dev.n3shemmy3.coffre.ui.base;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import dev.n3shemmy3.coffre.R;

public class BaseSettingsScreen extends BaseScreen {

    public FrameLayout headerFrame;
    public ConstraintLayout header;
    public ImageView headerCover;
    public ImageView headerIcon;
    public TextView headerTitle;
    public TextView headerSubtitle;
    private Fragment fragment;

    @Override
    protected int getLayoutResId() {
        return R.layout.screen_preference;
    }

    public void setPreferenceFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        headerFrame = root.findViewById(R.id.headerFrame);
        header = root.findViewById(R.id.header);
        headerCover = root.findViewById(R.id.headerCover);
        headerIcon = root.findViewById(R.id.headerIcon);
        headerTitle = root.findViewById(R.id.headerTitle);
        headerSubtitle = root.findViewById(R.id.headerSubtitle);
        headerFrame.setClipToOutline(true);
        headerFrame.setClipChildren(true);
    }

    @Override
    protected void onScreenCreated(View root, Bundle state) {
        super.onScreenCreated(root, state);
        if (state != null) return;
        getChildScreenManager()
                .beginTransaction()
                .add(R.id.settingsContainer, fragment)
                .commit();
    }
}
