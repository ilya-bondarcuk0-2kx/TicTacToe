package com.book_app.tictactoe.GameObjects;


import androidx.annotation.NonNull;
import com.book_app.tictactoe.R;

public class Intermediate extends Side{
    Intermediate() {
        super(R.drawable.void_pic);
    }

    @Override
    int getImage() {
        return pictureId;
    }

    @NonNull
    @Override
    public String toString() {
        return "Intermediate";
    }
}
