package com.book_app.tictactoe.GameObjects;


public abstract class Side {

    Side(int pictureId){
        this.pictureId = pictureId;
    }

    abstract int getImage();

    protected int pictureId;


}
