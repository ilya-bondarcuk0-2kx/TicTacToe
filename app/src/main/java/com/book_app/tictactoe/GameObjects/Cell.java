package com.book_app.tictactoe.GameObjects;

import android.widget.ImageView;

public class Cell {

    Cell(ImageView imageView, Side side){
        
        this.imageView = imageView;
        this.side = side;
    }


    private ImageView imageView;

    private Side side;

    private int imageId;

    public int getImage(){
        return imageId;
    }

    public void setImage(int imageId) {
        this.imageId = imageId;
        this.imageView.setImageResource(imageId);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getImageId() {
        return imageId;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }
}
