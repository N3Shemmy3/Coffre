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

package dev.n3shemmy3.coffre.ui.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.entity.Currency;
import dev.n3shemmy3.coffre.backend.entity.Profile;
import dev.n3shemmy3.coffre.ui.screen.main.MainScreen;
import dev.n3shemmy3.coffre.ui.screen.setup.StartScreen;
import dev.n3shemmy3.coffre.ui.utils.PrefUtil;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment = new MainScreen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen.installSplashScreen(this);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Profile profile = new Gson().fromJson(PrefUtil.getString(Profile.key), Profile.class);
            Currency currency = new Gson().fromJson(PrefUtil.getString(Currency.key), Currency.class);
            if (profile == null || currency == null) fragment = new StartScreen();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}