package dev.n3shemmy3.coffre.ui.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AppUtils {
    public static void loadIcon(ImageView img, String icon) {
        Context context = img.getContext();
        if (icon.startsWith("http://") || icon.startsWith("https://")) {
            //load image from url
            Glide.with(context).load(icon).into(img);
        } else {
            // Load image from resources
            @SuppressLint("DiscouragedApi") int resId = context.getResources().getIdentifier(icon, "drawable", context.getPackageName());
            img.setImageResource(resId);
        }
    }
}
