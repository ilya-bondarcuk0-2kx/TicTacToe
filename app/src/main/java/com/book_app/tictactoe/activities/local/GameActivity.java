package com.book_app.tictactoe.activities.local;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.book_app.tictactoe.R;
import com.book_app.tictactoe.application.settings.SettingsManager;
import com.book_app.tictactoe.game.enums.CheckResult;
import com.book_app.tictactoe.game.sides.Cross;
import com.book_app.tictactoe.game.sides.Side;
import com.book_app.tictactoe.mvp.models.GameFieldModel;
import com.book_app.tictactoe.mvp.presenters.GameFieldPresenter;
import com.book_app.tictactoe.mvp.views.GameFieldView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements GameFieldView {

    private GameFieldPresenter presenter;
    private TextView state;
    private final ArrayList<ImageButton> cellImageButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_game);
        Button restart_btn = findViewById(R.id.restart_game_btn);
        state = findViewById(R.id.state);

        presenter = new GameFieldPresenter();
        presenter.setModel(new GameFieldModel());

        cellImageButtons.add(findViewById(R.id.img1));
        cellImageButtons.add(findViewById(R.id.img2));
        cellImageButtons.add(findViewById(R.id.img3));
        cellImageButtons.add(findViewById(R.id.img4));
        cellImageButtons.add(findViewById(R.id.img5));
        cellImageButtons.add(findViewById(R.id.img6));
        cellImageButtons.add(findViewById(R.id.img7));
        cellImageButtons.add(findViewById(R.id.img8));
        cellImageButtons.add(findViewById(R.id.img9));


        for(int i = 0; i < cellImageButtons.size() ; ++i){
            int index = i;
            cellImageButtons.get(i).setOnClickListener( l -> presenter.updateCell(index));
        }
        restart_btn.setOnClickListener(l -> GameActivity.this.recreate());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unbindView();
    }

    @Override
    public void updateFieldOnUI(CheckResult checkResult, int index, Side side, ArrayList<Integer> winCombination) {

        cellImageButtons.get(index).setImageResource(side.getImage());
        cellImageButtons.get(index).setOnClickListener(null);
        switch (checkResult){
            case CROSS_WIN:
                state.setText("Крестики выиграли");
                showWinCombination(winCombination);
                setListenersToNull();
                return;
            case ZERO_WIN:
                state.setText("Нолики выиграли");
                setListenersToNull();
                showWinCombination(winCombination);
                return;
            case DRAW:
                state.setText("Ничья");
                setListenersToNull();
                return;
        }
        changeState(side);
    }

    private void setListenersToNull(){

        for(ImageView cellsView : cellImageButtons){
            cellsView.setOnClickListener(null);
        }


    }

    @SuppressLint("ResourceAsColor")
    private void showWinCombination(ArrayList<Integer> winCombination){

        for(Integer number : winCombination) {
            cellImageButtons.get(number).setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green)));
        }
    }

    private void changeState(Side side){
        if(side.getClass() == Cross.class)
            state.setText("Нолики ходят");
        else
            state.setText("Крестики ходят");
    }
}