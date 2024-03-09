package com.book_app.tictactoe.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.book_app.tictactoe.R;

public class ChooseSideActivity extends AppCompatActivity {


    Button crossBtn, zeroBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_side);


        crossBtn = findViewById(R.id.cross_btn);
        zeroBtn = findViewById(R.id.zero_btn);


        crossBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MultiPlayerGameActivity.class);
                intent.putExtra("side", "Cross");
                startActivity(intent);
            }
        });


        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MultiPlayerGameActivity.class);
                intent.putExtra("side", "Zero");
                startActivity(intent);
            }
        });


    }
}