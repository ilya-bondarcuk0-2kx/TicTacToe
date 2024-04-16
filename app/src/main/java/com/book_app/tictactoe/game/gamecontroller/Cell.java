package com.book_app.tictactoe.game.gamecontroller;

import com.book_app.tictactoe.game.sides.Side;

public class Cell {

    private Side side;

    Cell(Side side){
        this.side = side;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }
}
