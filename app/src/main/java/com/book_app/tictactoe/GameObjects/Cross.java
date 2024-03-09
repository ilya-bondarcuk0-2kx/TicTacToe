package com.book_app.tictactoe.GameObjects;


import androidx.annotation.NonNull;
import com.book_app.tictactoe.R;

public class Cross extends Side {

    public Cross(){
        super(R.drawable.cross);
    }





    @Override
    int getImage() {
        return pictureId;
    }


    @NonNull
    @Override
    public String toString() {
        return "Cross";
    }
}
