package com.book_app.tictactoe.activities.local;

import static com.book_app.tictactoe.activities.constants.GameMode.MODE_1x1;
import static com.book_app.tictactoe.activities.constants.GameMode.MODE_BOT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.book_app.tictactoe.R;
import com.book_app.tictactoe.activities.server.ServerRoomsActivity;
import com.book_app.tictactoe.adaptedviews.VariableAlertDialog;
import com.book_app.tictactoe.adaptedviews.VariableToast;
import com.book_app.tictactoe.client.connection.GameConnectionHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {


    ImageButton playBtn, multiplayerBtn, exitBtn;

    FloatingActionButton settingsBtn;

    String [] choices = {"1vs1", "Против ИИ"}; /*TODO: размер поля (сделать кастомный интерфейс)*/

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.play_btn);
        multiplayerBtn = findViewById(R.id.multiplayer_btn);
        exitBtn = findViewById(R.id.exit_btn);
        settingsBtn = findViewById(R.id.settings_button);




        playBtn.setOnClickListener(l -> VariableAlertDialog.buildCustomDialog(
                this,
                "Выберите режим",
                choices,
                (dialog, which) -> {

            switch (which){
                case MODE_1x1:
                    startActivity(new Intent(this, GameActivity.class));

                    break;
                case MODE_BOT:
                    startActivity(new Intent(this, BotGameActivity.class));
                    break;
                default:
                    VariableToast.makeText(this, "Данный функционал пока в разработке", Toast.LENGTH_LONG).show();

            }
            dialog.cancel();

        },
                "Назад",
                (dialog, which) -> dialog.cancel(),
                false).show());

        multiplayerBtn.setOnClickListener(l -> {
            startActivity(new Intent(this, ServerRoomsActivity.class));
            GameConnectionHandler.ServerConnection.connectToServer("192.168.1.177", 3000);
        });
        exitBtn.setOnClickListener(l -> MainActivity.this.finish());

        settingsBtn.setOnClickListener(l -> startActivity(new Intent(this, SettingsActivity.class)));





    }
}