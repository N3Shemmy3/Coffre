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

package dev.n3shemmy3.coffre.backend.worker;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.work.ForegroundInfo;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import dev.n3shemmy3.coffre.R;
import dev.n3shemmy3.coffre.backend.entity.Transaction;
import dev.n3shemmy3.coffre.backend.repository.TransactionsRepository;

public class BackupWorker extends Worker {
    private final Context context;

    public BackupWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }

    public static final String TAG = BackupWorker.class.getSimpleName();
    private Notification notification;

    @NonNull
    @Override
    public Result doWork() {
        NotificationChannel channel = new NotificationChannel("Backup", "Backup Service", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
        Context context = getApplicationContext();
        TransactionsRepository repository = new TransactionsRepository();
        List<Transaction> transactions = repository.transactionBox.getAll();
        if (transactions == null) {
            showNotification(0);
            return Result.failure();
        }
        showNotification(1);
        int total = transactions.size();
        String filename = "app.coffre_" + Calendar.YEAR + "-" + Calendar.MONTH + "-" + Calendar.DAY_OF_MONTH + "_" + Calendar.HOUR_OF_DAY + "-" + Calendar.MINUTE + ".backup";
//        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_TITLE, filename);
        String backup = new Gson().toJson(transactions);
        saveFileToExternalPrivateStorage(context, filename, backup);
        showNotification(2);
        return Result.success();
    }

    private void showNotification(int state) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Backup")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        switch (state) {
            case 0: {
                builder.setContentTitle("Backup Failed");
                builder.setContentText("Backup was not created try again");
                builder.setOngoing(false);
            }
            break;
            case 1: {
                builder.setContentTitle("Creating Backup");
                builder.setProgress(100, 50, true);
                builder.setOngoing(true);

            }
            break;
            case 2: {
                builder.setContentTitle("Backup Created");
                builder.setContentText("Backup successfully created");
                builder.setOngoing(false);
            }
            break;
        }
        notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

    }

    @NonNull
    @Override
    public ForegroundInfo getForegroundInfo() {
        return new ForegroundInfo(1, notification);
    }

    public static void saveFileToExternalPrivateStorage(Context context, String filename, String data) {
        File file = new File(context.getExternalFilesDir(null), filename);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
