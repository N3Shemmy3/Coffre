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

package dev.n3shemmy3.coffre.ui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {
    private static final String APP_SPECIFIC_IMAGE_DIR = "images"; // Directory for images

    // Saves an image (from a URI) to the app's private storage. Returns the saved file's URI, or null on failure.
    public static Uri saveImageToPrivateStorage(Context context, Uri imageUri, String filename) {
        File directory = new File(context.getFilesDir(), APP_SPECIFIC_IMAGE_DIR);
        if (!directory.exists() && !directory.mkdirs()) {
            return null; // Failed to create directory
        }

        File imageFile = new File(directory, filename);
        try (InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
             FileOutputStream outputStream = new FileOutputStream(imageFile)) {
            if (inputStream == null) return null;

            Bitmap bitmap = BitmapFactory.decodeStream(inputStream); // Decode the input stream to bitmap
            if (bitmap == null) return null;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream); // Save the bitmap as JPEG
            return Uri.fromFile(imageFile); // Return the Uri of the saved image
        } catch (IOException e) {
            e.printStackTrace(); // Or log the error appropriately
            return null;
        }
    }

    // Retrieves an image from the app's private storage. Returns a Bitmap, or null if retrieval fails.
    public static Bitmap retrieveImageFromPrivateStorage(Context context, String filename) {
        File directory = new File(context.getFilesDir(), APP_SPECIFIC_IMAGE_DIR);
        File imageFile = new File(directory, filename);

        if (!imageFile.exists()) {
            return null; // File not found
        }

        try (FileInputStream inputStream = new FileInputStream(imageFile)) {
            return BitmapFactory.decodeStream(inputStream); // Decode the input stream to bitmap
        } catch (IOException e) {
            e.printStackTrace(); // Or log the error
            return null;
        }
    }
}