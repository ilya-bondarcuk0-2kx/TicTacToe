package com.book_app.tictactoe.activities.server;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.book_app.tictactoe.R;
import com.book_app.tictactoe.adaptedviews.VariableToast;
import com.book_app.tictactoe.client.connection.Client;
import com.book_app.tictactoe.client.javarxadapted.DisposableManager;
import com.book_app.tictactoe.game.enums.CheckResult;
import com.book_app.tictactoe.game.sides.Cross;
import com.book_app.tictactoe.game.sides.Side;
import com.book_app.tictactoe.game.sides.Zero;
import com.book_app.tictactoe.mvp.models.ServerGameFieldModel;
import com.book_app.tictactoe.mvp.presenters.ServerGameFieldPresenter;
import com.book_app.tictactoe.mvp.views.ServerGameFieldView;

import java.util.ArrayList;

public class ServerGameActivity extends AppCompatActivity implements ServerGameFieldView {


    ServerGameFieldPresenter presenter;

    Button crossBtn, zeroBtn;

    TextView state;

    ArrayList<ImageButton> cellImageButtons = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_game);
        crossBtn = findViewById(R.id.cross_btn);
        zeroBtn = findViewById(R.id.zero_btn);
        state = findViewById(R.id.state);

        presenter = new ServerGameFieldPresenter();
        presenter.setModel(new ServerGameFieldModel()); //TODO: Поменять так как модель составляет слой бизнес логики
        presenter.setUpReceiveProcess();

        cellImageButtons.add(findViewById(R.id.img1));
        cellImageButtons.add(findViewById(R.id.img2));
        cellImageButtons.add(findViewById(R.id.img3));
        cellImageButtons.add(findViewById(R.id.img4));
        cellImageButtons.add(findViewById(R.id.img5));
        cellImageButtons.add(findViewById(R.id.img6));
        cellImageButtons.add(findViewById(R.id.img7));
        cellImageButtons.add(findViewById(R.id.img8));
        cellImageButtons.add(findViewById(R.id.img9));

        crossBtn.setOnClickListener(l -> {
            Client.setSide(new Cross());
            crossBtn.setEnabled(false);
            zeroBtn.setEnabled(false);
            presenter.sendSide();

        });
        zeroBtn.setOnClickListener(l -> {
            Client.setSide(new Zero());
            crossBtn.setEnabled(false);
            zeroBtn.setEnabled(false);
            presenter.sendSide();
        });

       /* String text = "ожидание. .. ...
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new RelativeSizeSpan(0.5f), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(0.5f), text.length() - 3, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);*/
        state.setText("Ожидание...");

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
        DisposableManager.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.quitFromRoomAndClearResources();
    }


    private void setClickListeners(){
        for(int i = 0; i < cellImageButtons.size(); ++i) {
            int index = i;
            cellImageButtons.get(i).setOnClickListener(l -> {
                presenter.updateCell(index);
            });
        }
    }
    private void setListenersToNull(){

        for(ImageView cellsView : cellImageButtons){
            cellsView.setOnClickListener(null);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void showWinCombination(ArrayList<Integer> winCombination){

        for(Integer number : winCombination){
            cellImageButtons.get(number).setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green)));
        }
    }

    @Override
    public void updateFieldOnUI(CheckResult checkResult, int index, Side side, ArrayList<Integer> winCombination) {

        cellImageButtons.get(index).setImageResource(side.getImage());
        cellImageButtons.get(index).setOnClickListener(null);
        switch (checkResult) {
            case GAME_CONTINUE:
                if (side.getClass() != Client.getSide().getClass()) state.setText("Ваш ход");
                else state.setText("Противник ходит...");
                break;
            case CROSS_WIN:
            case ZERO_WIN:

                if (side.getClass() == Client.getSide().getClass()) state.setText("Вы выиграли!");
                else state.setText("Вы проиграли!");

                showWinCombination(winCombination);
                setListenersToNull();
                break;
            case DRAW:
                state.setText("Ничья");
                setListenersToNull();
                break;
        }
    }

    @Override
    public void showWarningOnUI(String warning) {

        switch (warning){
            case "NullPacket":
                VariableToast.makeText(this, "Сервер не отвечает", Toast.LENGTH_SHORT).show();
                break;
            case "op-conn":
                VariableToast.makeText(this, "Противник присоединился", Toast.LENGTH_SHORT).show();
                break;
            case "side-pick-ok":
                if(Client.getSide().getClass() == Cross.class) state.setText("Ваш ход");
                else                                           state.setText("Противник ходит...");
                crossBtn.setVisibility(View.INVISIBLE);
                zeroBtn.setVisibility(View.INVISIBLE);
                setClickListeners();
                break;
            case "side-pick-error":
                VariableToast.makeText(this, "Эту сторону уже выбрали", Toast.LENGTH_SHORT).show();
                crossBtn.setEnabled(true);
                zeroBtn.setEnabled(true);
                break;
            case "side-room-is-not-full":
                VariableToast.makeText(this, "Комната не заполнена", Toast.LENGTH_SHORT).show();
                crossBtn.setEnabled(true);
                zeroBtn.setEnabled(true);
                break;
            case "no-ur-turn":
                VariableToast.makeText(this, "Не ваш ход", Toast.LENGTH_SHORT).show();
                break;
            case "wait":
                VariableToast.makeText(this, "Ожидайте соперника", Toast.LENGTH_SHORT).show();
                break;
            case "op-disc":
                VariableToast.makeText(this, "Соперник вышел", Toast.LENGTH_SHORT).show();
                finish();
                break;


        }
    }
}

