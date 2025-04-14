package dev.n3shemmy3.coffre.backend.objectbox;

import android.content.Context;

import dev.n3shemmy3.coffre.backend.item.MyObjectBox;
import io.objectbox.BoxStore;

public class ObjectBox {

    private static BoxStore boxStore;

    public static void init(Context context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();
    }

    public static BoxStore get() {
        return boxStore;
    }
}
