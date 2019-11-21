package com.sirio.remindme.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SharedPreference {

    private static final String USER_NAME= "USERNAME";
    private static final String ID= "ID";

    private static SharedPreferences get(Context ctx)
    {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static boolean isLoggedIn(Context ctx){
        return !SharedPreference.getUsername(ctx).isEmpty();
    }

    public static void setUsername(Context ctx, String username)
    {
        Editor editor = get(ctx).edit();
        editor.putString(USER_NAME, username);
        editor.commit();
    }

    public static String getUsername(Context ctx)
    {
        return get(ctx).getString(USER_NAME, "");
    }

    public static void setId(Context ctx, String username)
    {
        Editor editor = get(ctx).edit();
        editor.putString(ID, username);
        editor.commit();
    }

    public static String getId(Context ctx)
    {
        return get(ctx).getString(ID, "");
    }

    public static void clear(Context ctx)
    {
        Editor editor = get(ctx).edit();
        editor.clear();
        editor.commit();
    }
}
