package com.book_app.tictactoe.mvp.views;

import com.book_app.tictactoe.game.enums.CheckResult;
import com.book_app.tictactoe.game.sides.Side;

import java.util.ArrayList;

public interface GameFieldView {


    void updateFieldOnUI(CheckResult checkResult, int index, Side side, ArrayList<Integer> winCombination);



}
