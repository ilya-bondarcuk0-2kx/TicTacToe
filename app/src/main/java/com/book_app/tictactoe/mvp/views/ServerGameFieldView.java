package com.book_app.tictactoe.mvp.views;

import com.book_app.tictactoe.game.enums.CheckResult;
import com.book_app.tictactoe.game.sides.Side;

import java.util.ArrayList;


public interface ServerGameFieldView {
    void updateFieldOnUI(CheckResult checkResult, int index, Side side, ArrayList<Integer> winCombination);

    void showWarningOnUI(String warning);

}