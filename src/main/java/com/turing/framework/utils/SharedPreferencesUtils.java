package com.turing.framework.utils;

import android.content.Context;

/**
 * Created by kifile on 16/8/16.
 */
public class SharedPreferencesUtils {

    public static final String SP_NAME = "ysl";

    public static String getString(Context context, String key, String def) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(key, def);
    }

    public static void saveString(Context context, String key, String value) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putString(key, value)
                .apply();
    }

    public static int getInt(Context context, String key, int def) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getInt(key, def);
    }

    public static void saveInt(Context context, String key, int value) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putInt(key, value)
                .apply();
    }

    public static long getLong(Context context, String key, long def) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getLong(key, def);
    }

    public static void saveLong(Context context, String key, long value) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putLong(key, value)
                .apply();
    }

    public static boolean getBoolean(Context context, String key, boolean def) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getBoolean(key, def);
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putBoolean(key, value)
                .apply();
    }
}
