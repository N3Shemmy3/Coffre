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
import androidx.work.ForegroundInfo;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import dev.n3shemmy3.coffre.R;

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


            showNotification((50));


        return Result.success();
    }

    private void showNotification(int progress) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "Backup")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Backup Service")
                .setContentText("It is running")
                .setProgress(100, progress, false)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("beep boop"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setOngoing(true);
        notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

    }

    @NonNull
    @Override
    public ForegroundInfo getForegroundInfo() {
        return new ForegroundInfo(1, notification);
    }
}
