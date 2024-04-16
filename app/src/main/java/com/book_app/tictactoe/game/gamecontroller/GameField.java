package com.book_app.tictactoe.game.gamecontroller;

import com.book_app.tictactoe.game.constants.GameWins;
import com.book_app.tictactoe.game.enums.CheckResult;
import com.book_app.tictactoe.game.sides.Cross;
import com.book_app.tictactoe.game.sides.Intermediate;
import com.book_app.tictactoe.game.sides.Side;
import com.book_app.tictactoe.game.sides.Zero;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;


public class GameField {

    private final ArrayList<Cell> cells = new ArrayList<>();

    public ArrayList<Integer> getWinCombination() {
        return winCombination;
    }

    ArrayList<Integer> winCombination = new ArrayList<>();

    private Side side;

    private int turn;

    private final AtomicBoolean isGameOver = new AtomicBoolean(false);

    public GameField() {
        turn = 1;
        side = new Cross();

        for(int i = 0; i < 9; ++i)
            cells.add(new Cell(new Intermediate()));
    }

    public CheckResult checkField(){
        ++turn;
        for (int i = 0; i < 8; ++i) {
            int count = 0;
            for (int j = 0; j < 3; ++j) {
                if(cells.get(GameWins.wins[i][j]).getSide().getClass() == side.getClass()){
                    winCombination.add(GameWins.wins[i][j]);
                    ++count; /*TODO: */
                }
            }

            if(count == 3){
                if(side.getClass() == Cross.class)
                    return CheckResult.CROSS_WIN;
                else
                    return CheckResult.ZERO_WIN;
            }
            winCombination.clear();
        }

        if(turn == 10){
            return CheckResult.DRAW;
        }
        changeSide();
        return CheckResult.GAME_CONTINUE;
    }

    public CheckResult checkField(Side side, int turn){

        for (int i = 0; i < 8; ++i) {
            int count = 0;
            for (int j = 0; j < 3; ++j) {
                if(cells.get(GameWins.wins[i][j]).getSide().getClass() == side.getClass()) {

                    winCombination.add(GameWins.wins[i][j]);
                    ++count;
                }
            }
            if(count == 3){
                isGameOver.set(true);
                if(side.getClass() == Cross.class)
                    return CheckResult.CROSS_WIN;
                else
                    return CheckResult.ZERO_WIN;
            }
            winCombination.clear();
        }
        if(turn == 10){
            isGameOver.set(true);
            return CheckResult.DRAW;
        }
        return CheckResult.GAME_CONTINUE;

    }

    private void changeSide(){

        if(side.getClass() == Cross.class)
        {
            side = new Zero();

        }
        else {
            side = new Cross();
        }

    }


    public ArrayList<Cell> getCells() {
        return cells;
    }

    public Cell getCell(int index){
        return cells.get(index);
    }

    public void updateCell(int index, Side side){
        cells.get(index).setSide(side);
    }

    public void updateCell(int index){
        cells.get(index).setSide(side);
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public AtomicBoolean getIsGameOver() {
        return isGameOver;
    }




}