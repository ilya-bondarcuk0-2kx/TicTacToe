package com.book_app.tictactoe.game.bot;

import com.book_app.tictactoe.game.gamecontroller.Cell;
import com.book_app.tictactoe.game.gamecontroller.GameField;
import com.book_app.tictactoe.game.sides.Intermediate;

import java.util.Random;

public class EasyBot extends Bot{
    @Override
    public int analyzeGameField(GameField gameField) {

        Random random = new Random();
        int choose = random.nextInt(9);
        while(gameField.getCells().get(choose).getSide().getClass() != Intermediate.class) {
            choose = random.nextInt(9);
        }
        return choose;
    }
}
