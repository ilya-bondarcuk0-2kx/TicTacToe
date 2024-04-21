package com.book_app.tictactoe.activities.local;

import static com.book_app.tictactoe.activities.constants.BotMode.EASY;
import static com.book_app.tictactoe.activities.constants.BotMode.HARD;
import static com.book_app.tictactoe.activities.constants.BotMode.IMPOSSIBLE;
import static com.book_app.tictactoe.activities.constants.BotMode.MEDIUM;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.book_app.tictactoe.R;
import com.book_app.tictactoe.adaptedviews.VariableAlertDialog;
import com.book_app.tictactoe.adaptedviews.VariableToast;
import com.book_app.tictactoe.application.settings.SettingsManager;
import com.book_app.tictactoe.game.enums.CheckResult;
import com.book_app.tictactoe.game.sides.Side;
import com.book_app.tictactoe.mvp.models.BotGameFieldModel;
import com.book_app.tictactoe.mvp.presenters.BotGameFieldPresenter;
import com.book_app.tictactoe.mvp.views.GameFieldView;

import java.util.ArrayList;
import java.util.Objects;

public class BotGameActivity extends AppCompatActivity implements GameFieldView {

    private BotGameFieldPresenter presenter;
    private final ArrayList<ImageButton> cellImageButtons = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_bot_game);


        Button restartBtn = findViewById(R.id.restart_game_btn);
        ImageView botImage = findViewById(R.id.bot_image); //TODO: Должны подгружаться разные изображения, в зависимости от сложности бота

        presenter = new BotGameFieldPresenter();
        presenter.setBot(Objects.requireNonNull(getIntent().getStringExtra("dif")));
        presenter.setModel(new BotGameFieldModel());

        cellImageButtons.add(findViewById(R.id.img1));
        cellImageButtons.add(findViewById(R.id.img2));
        cellImageButtons.add(findViewById(R.id.img3));
        cellImageButtons.add(findViewById(R.id.img4));
        cellImageButtons.add(findViewById(R.id.img5));
        cellImageButtons.add(findViewById(R.id.img6));
        cellImageButtons.add(findViewById(R.id.img7));
        cellImageButtons.add(findViewById(R.id.img8));
        cellImageButtons.add(findViewById(R.id.img9));


        for (int i = 0; i < cellImageButtons.size(); ++i) {
            int index = i;
            cellImageButtons.get(i).setOnClickListener(l -> presenter.updateCell(index));
        }
        botImage.setOnClickListener(l -> VariableAlertDialog.buildCustomDialog(
                this,
                "Выберите сложность ИИ" ,
                new String[]{"Легкий", "Нормальный", "Сложный", "Невозможный"},
                (dialogDif, whichDif) -> {
                    switch (whichDif){
                        case EASY:
                            getIntent().putExtra("dif", "es");
                            recreate(); //TODO: Когда все боты будут доделаны, нужно будет сместить запуск просто в самый низ в данном участке кода
                            break;
                        case MEDIUM:
                            VariableToast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
//                            getIntent().putExtra("dif", "md");
                            break;
                        case HARD:
                            VariableToast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
//                            getIntent().putExtra("dif", "hd");
                            break;
                        case IMPOSSIBLE:
                            VariableToast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
//                            getIntent().putExtra("dif", "im");
                            break;
                    }

                },
                "Назад",
                (dialogDif, whichDif) -> dialogDif.cancel(),
                false).show());
        restartBtn.setOnClickListener(l -> BotGameActivity.this.recreate());
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
        switch (checkResult) {
            case CROSS_WIN:

                showWinCombination(winCombination);
                setListenersToNull();
                return;
            case ZERO_WIN:

                setListenersToNull();
                showWinCombination(winCombination);
                return;
            case DRAW:

                setListenersToNull();
                return;
        }
    }

    private void setListenersToNull() {

        for (ImageView cellsView : cellImageButtons) {
            cellsView.setOnClickListener(null);
        }


    }



    @SuppressLint("ResourceAsColor")
    private void showWinCombination(ArrayList<Integer> winCombination) {

        for (Integer number : winCombination) {
            cellImageButtons.get(number).setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.green)));
        }
    }


}