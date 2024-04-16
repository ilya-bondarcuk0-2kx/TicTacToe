package com.book_app.tictactoe.game.sides;


public abstract class Side {

    public Side(int pictureId){
        this.pictureId = pictureId;
    }

    public abstract int getImage();

    protected int pictureId;


}
