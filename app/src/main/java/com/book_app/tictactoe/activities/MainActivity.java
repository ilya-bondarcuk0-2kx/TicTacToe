package com.book_app.tictactoe.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.book_app.tictactoe.R;

public class MainActivity extends AppCompatActivity {


    Button play_btn, multiplayer_btn, exit_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play_btn = findViewById(R.id.play_btn);
        multiplayer_btn = findViewById(R.id.multiplayer_btn);
        exit_btn = findViewById(R.id.exit_btn);

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getBaseContext(), GameActivity.class));
            }
        });

        multiplayer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), ChooseSideActivity.class));
            }
        });


        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });








    }
}