package com.book_app.tictactoe.GameObjects;


import androidx.annotation.NonNull;
import com.book_app.tictactoe.R;

public class Zero extends Side {


    public Zero() {
        super(R.drawable.zero);
    }

    @Override
    int getImage() {
        return pictureId;
    }


    @NonNull
    @Override
    public String toString() {
        return "Zero";
    }
}
