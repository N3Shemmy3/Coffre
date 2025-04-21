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

package dev.n3shemmy3.coffre;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.material.color.DynamicColors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mikepenz.iconics.Iconics;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import dev.n3shemmy3.coffre.backend.item.Currency;
import dev.n3shemmy3.coffre.backend.objectbox.ObjectBox;
import dev.n3shemmy3.coffre.ui.activity.DebugActivity;
import dev.n3shemmy3.coffre.ui.service.BackupService;
import dev.n3shemmy3.coffre.ui.utils.AssetsUtils;
import dev.n3shemmy3.coffre.ui.utils.PrefUtil;

public class App extends Application implements Thread.UncaughtExceptionHandler {
    @Override
    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(this);
        DynamicColors.applyToActivitiesIfAvailable(this);
        Iconics.init(this);
        ObjectBox.init(this);
        PrefUtil.Init(this);
        updateCurrency();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel("channeled", "channelService", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
    }

    public void updateCurrency() {
        Currency currency = new Gson().fromJson(PrefUtil.getString("currency"), Currency.class);
        if (currency == null) return;
        String data = AssetsUtils.getAssetJsonData(this, "currencies.json");
        Type type = new TypeToken<List<Currency>>() {
        }.getType();
        List<Currency> currencies = new Gson().fromJson(data, type);
        if (currencies == null) return;
        List<Currency> filteredCurrencies = currencies.stream()
                .filter(item -> item.getName().equalsIgnoreCase(currency.getName()))
                .collect(Collectors.toList());
        PrefUtil.save("currency", new Gson().toJson(filteredCurrencies.get(0)));

    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        String message = Log.getStackTraceString(throwable);
        String threadName = Thread.currentThread().getName();
        Log.e("AppClass", "Uncaught exception in thread " + threadName + ":\n " + message);
        Intent intent = new Intent(this, DebugActivity.class);
        intent.putExtra("message", message);
        intent.putExtra("thread", threadName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
