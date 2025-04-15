package dev.n3shemmy3.coffre.ui;
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

import android.app.Application;

import com.google.android.material.color.DynamicColors;
import com.mikepenz.iconics.Iconics;

import dev.n3shemmy3.coffre.backend.objectbox.ObjectBox;
import dev.n3shemmy3.coffre.ui.utils.PrefUtil;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DynamicColors.applyToActivitiesIfAvailable(this);
        Iconics.init(this);
        ObjectBox.init(this);
        PrefUtil.Init(this);
    }
}
