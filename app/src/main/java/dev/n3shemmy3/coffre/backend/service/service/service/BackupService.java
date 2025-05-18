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

package dev.n3shemmy3.coffre.backend.service.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import dev.n3shemmy3.coffre.R;

public class BackupService extends Service {

    public static final String STOP = "stop";
    public static final String BACKUP = "backup";
    public static final String RESTORE = "restore";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getAction() == null) return super.onStartCommand(intent, flags, startId);
        switch (intent.getAction()) {
            case BACKUP: {
                createNotification();
            }
            break;
            case RESTORE: {
                createNotification();
            }
            break;
            case STOP: {
                stopSelf();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "backup");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Backup");
        builder.setContentText("Backup in progress");
        builder.setProgress(100, 25, false);
        builder.setOngoing(true);
        startForeground(1, builder.build());
    }
}
