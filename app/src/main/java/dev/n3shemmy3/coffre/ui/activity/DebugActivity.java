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

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.HapticFeedbackConstantsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Locale;

import dev.n3shemmy3.coffre.BuildConfig;
import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.ui.utils.InsetsUtils;

public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_debug);

        AppBarLayout topAppBar = findViewById(R.id.topAppBar);
        MaterialToolbar topToolBar = findViewById(R.id.topToolBar);
        topToolBar.setNavigationOnClickListener(v -> {
            ViewCompat.performHapticFeedback(v, HapticFeedbackConstantsCompat.CONTEXT_CLICK);
            getOnBackPressedDispatcher().onBackPressed();
        });
        TextView textLogs = findViewById(R.id.textLogs);
        ExtendedFloatingActionButton actionShare = findViewById(R.id.actionShare);

        String message = getIntent().getStringExtra("message");
        String threadName = getIntent().getStringExtra("thread");

        String deviceBrand = Build.BRAND;
        String deviceModel = Build.MODEL;
        int deviceSdk = Build.VERSION.SDK_INT;
        long crashTime = System.currentTimeMillis();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd-MM-yyyy", Locale.getDefault());
        String dateTime = formatter.format(crashTime);
        String appVersion = BuildConfig.VERSION_NAME;
        String builder = "Coffre Version" +
                ':' +
                ' ' +
                appVersion +
                '\n' +
                '\n' +
                ':' +
                " " +
                '\n' +
                "Device Brand" +
                ':' +
                "        " +
                deviceBrand +
                '\n' +
                "Device Model" +
                ':' +
                "        " +
                deviceModel +
                '\n' +
                "SDK Level" +
                ':' +
                "    " +
                deviceSdk +
                '\n' +
                "Thread" +
                ':' +
                "       " +
                threadName +
                '\n' +
                '\n' +
                '\n' +
                "Time of crash" +
                ':' +
                "  " +
                dateTime +
                '\n' +
                "--------- beginning of crash" +
                '\n' +
                message;

        textLogs.setTypeface(Typeface.MONOSPACE);
        textLogs.setText(builder);
        InsetsUtils.onInsetsListener(topAppBar, (windowInsets) -> {
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
            mlp = (ViewGroup.MarginLayoutParams) actionShare.getLayoutParams();
            int dp16 = dp8 * 2;
            mlp.leftMargin = hInsets + dp16;
            mlp.rightMargin = hInsets + dp16;
            mlp.bottomMargin = dp16 + systemBarInsets.bottom + imeInsets.bottom;
            actionShare.setLayoutParams(mlp);
        });
        InsetsUtils.applyContentInsets(textLogs);

    }
}
