package com.book_app.tictactoe.game.sides;


import androidx.annotation.NonNull;

import com.book_app.tictactoe.R;

public class Intermediate extends Side {
    public Intermediate() {
        super(R.drawable.void_pic);
    }

    @Override
    public int getImage() {
        return pictureId;
    }

    @NonNull
    @Override
    public String toString() {
        return "Intermediate";
    }
}
