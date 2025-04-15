package dev.n3shemmy3.coffre.ui.screen.setup;
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

import android.os.Bundle;
import android.view.View;

import androidx.core.view.HapticFeedbackConstantsCompat;
import androidx.core.view.ViewCompat;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.base.BaseScreen;
import dev.n3shemmy3.coffre.ui.navigator.Navigator;

public class StartScreen extends BaseScreen {


    @Override
    protected int getLayoutResId() {
        return R.layout.screen_start;
    }

    @Override
    protected void onCreateScreen(View root, Bundle state) {
        super.onCreateScreen(root, state);
        root.findViewById(R.id.actionStart).setOnClickListener(v -> {
            ViewCompat.performHapticFeedback(v, HapticFeedbackConstantsCompat.CONTEXT_CLICK);
            Navigator.push(getScreenManager(), new SetupScreen());
        });
    }
}
