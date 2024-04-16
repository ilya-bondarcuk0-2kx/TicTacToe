package com.book_app.tictactoe.application.settings;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager {

    private static SharedPreferences preferences;

    private static OnAppSettingsChanged changed;


    private static final String PREF_NAME = "TicTacToeSettings";


    public static void setContext(Context context){
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    public static void setTheme(){
    }



}
