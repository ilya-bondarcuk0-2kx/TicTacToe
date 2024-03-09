package com.book_app.tictactoe.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.book_app.tictactoe.GameObjects.Cross;
import com.book_app.tictactoe.GameObjects.GameField;
import com.book_app.tictactoe.GameObjects.Zero;
import com.book_app.tictactoe.R;
import com.book_app.tictactoe.client.Client;
import java.util.Objects;

public class MultiPlayerGameActivity extends AppCompatActivity {


    Client client;
    GameField gameField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player_game);


        if(Objects.equals(getIntent().getStringExtra("side"), "Cross"))
            gameField = new GameField(this, new Cross(),client = new Client(new Cross()));
        else
            gameField = new GameField(this, new Zero(),client = new Client(new Zero()));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        gameField.stopGame();
        new Thread(new Runnable() {

            @Override
            public void run() {
                client.disconnectFromServer();
            }
        }).start();

    }
}