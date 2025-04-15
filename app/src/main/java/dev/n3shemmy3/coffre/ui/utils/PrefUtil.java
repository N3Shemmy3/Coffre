package dev.n3shemmy3.coffre.ui.utils;
/**
 * Created by omary on 3/24/2018. https://github.com/omaryazji/PrefUtil
 *
 */

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;


public class PrefUtil {
    private static final PrefUtil prefUtil = new PrefUtil();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    static int PRIVATE_MODE = 0;

    static PrefUtil Init() {
        return prefUtil;
    }

    private PrefUtil() {}

    public static PrefUtil Init(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), PRIVATE_MODE);
            editor = sharedPreferences.edit();
        }
        return prefUtil;
    }

    /*ToDo: Save values to a shared preference*/

    public static void save(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static void save(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public  static void save(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static void save(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public static void save(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    /*ToDo: Get values from a shared preference */

    public static String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public static boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public static int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public static Float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0);
    }

    public static Long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }

    /*ToDo: Remove value from a shared preference*/

    public static void removeValue(String key) {
        editor.remove(key);
        editor.commit();
    }

    /*ToDo: Remove all values from a shared preference*/
    public static  void clearALl() {
        editor.clear();
        editor.commit();
    }

    public static Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }


}