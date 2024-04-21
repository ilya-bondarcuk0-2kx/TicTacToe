package com.book_app.tictactoe.application.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {

    private static   SharedPreferences preferences;

    //private static Map preferences = new HashMap();

    private static final String PREF_NAME = "TicTacToeSettings";
    private static final String THEME_CHANGED = "THEME";


    public static void setContext(Context context){
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void setThemeChanged(boolean changed){
        preferences.edit().putBoolean(THEME_CHANGED, changed).apply();
    }

    public static boolean getThemeChanged(){
        return preferences.getBoolean(THEME_CHANGED, false);
    }



}
