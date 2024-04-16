package com.book_app.tictactoe.application.settings;

public interface OnAppSettingsChanged<T> {


    void settingsChanged(T data);


}
