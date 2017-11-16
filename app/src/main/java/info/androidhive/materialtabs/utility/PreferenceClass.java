package info.androidhive.materialtabs.utility;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceClass {
    public static Context appContext;
    private static String PICTORIOUS_PREFERENCE="pictorious_preference";

    public static void setStringPreference(Context context, String name, String value) {
        appContext = context;
        SharedPreferences settings = context.getSharedPreferences(PICTORIOUS_PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public static void setTimePreference(Context context, String name, long value) {
        appContext = context;
        SharedPreferences settings = context.getSharedPreferences(PICTORIOUS_PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(name, value);
        editor.commit();
    }

    public static void setIntegerPreference(Context context, String name, int value) {
        appContext = context;
        SharedPreferences settings = context.getSharedPreferences(PICTORIOUS_PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(name, value);
        editor.commit();
    }

    public static void setBooleanPreference(Context context, String name, boolean value) {
        appContext = context;
        SharedPreferences settings = context.getSharedPreferences(PICTORIOUS_PREFERENCE, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(name, value);
        editor.commit();

    }

    public static long getTimePreferences(Context context, String name) {
        SharedPreferences settings = context.getSharedPreferences(PICTORIOUS_PREFERENCE, 0);
        return settings.getLong(name, (long) 0.0);
    }


    public static String getStringPreferences(Context context, String name) {
        SharedPreferences settings = context.getSharedPreferences(PICTORIOUS_PREFERENCE, 0);
        return settings.getString(name, "");
    }

    public static int getIntegerPreferences(Context context, String name) {
        SharedPreferences settings = context.getSharedPreferences(PICTORIOUS_PREFERENCE, 0);
        return settings.getInt(name, 0);
    }

    public static boolean getBooleanPreferences(Context context, String name) {
        SharedPreferences settings = context.getSharedPreferences(PICTORIOUS_PREFERENCE, 0);
        return settings.getBoolean(name, false);
    }

    public static void clearPreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PICTORIOUS_PREFERENCE, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
}
