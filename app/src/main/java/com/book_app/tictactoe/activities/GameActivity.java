package com.book_app.tictactoe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.book_app.tictactoe.GameObjects.GameField;
import com.book_app.tictactoe.R;

public class GameActivity extends AppCompatActivity {

    GameField gameField;

    Button restart_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        restart_btn = findViewById(R.id.restart_game_btn);
        gameField = new GameField(this);

        restart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.this.recreate();

            }
        });
    }


}