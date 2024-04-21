package com.book_app.tictactoe.activities.server;

import static com.book_app.tictactoe.activities.constants.BotMode.EASY;
import static com.book_app.tictactoe.activities.constants.BotMode.HARD;
import static com.book_app.tictactoe.activities.constants.BotMode.IMPOSSIBLE;
import static com.book_app.tictactoe.activities.constants.BotMode.MEDIUM;
import static com.book_app.tictactoe.activities.constants.GameMode.MODE_1x1;
import static com.book_app.tictactoe.activities.constants.GameMode.MODE_BOT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.book_app.tictactoe.R;
import com.book_app.tictactoe.activities.local.BotGameActivity;
import com.book_app.tictactoe.activities.local.GameActivity;
import com.book_app.tictactoe.adaptedviews.VariableAlertDialog;
import com.book_app.tictactoe.adaptedviews.VariableToast;
import com.book_app.tictactoe.client.connection.ConnectionHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    ImageButton playBtn, multiplayerBtn, exitBtn;
    FloatingActionButton settingsBtn;

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
                new String[]{"1vs1", "Против ИИ"},
                (dialog, which) -> {

            switch (which){
                case MODE_1x1:
                    startActivity(new Intent(this, GameActivity.class));

                    break;
                case MODE_BOT:

                    VariableAlertDialog.buildCustomDialog(
                            this,
                            "Выберите сложность ИИ" ,
                            new String[]{"Легкий", "Нормальный", "Сложный", "Невозможный"},
                            (dialogDif, whichDif) -> {
                                Intent intent = new Intent(this, BotGameActivity.class);
                                switch (whichDif){
                                    case EASY:
                                        intent.putExtra("dif", "es");
                                        startActivity(intent); //TODO: Когда все боты будут доделаны, нужно будет сместить запуск просто в самый низ в данном участке кода
                                        break;
                                    case MEDIUM:
                                        VariableToast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
//                                        intent.putExtra("dif", "md");
                                        dialogDif.cancel();
                                        break;
                                    case HARD:
                                        VariableToast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
                                        dialogDif.cancel();
//                                        intent.putExtra("dif", "hd");
                                        break;
                                    case IMPOSSIBLE:
                                        VariableToast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
                                        dialogDif.cancel();
//                                        intent.putExtra("dif", "im");
                                        break;

                                }
                            },
                            "Назад",
                            (dialogDif, whichDif) -> dialogDif.cancel(),
                            false).show();

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
            ConnectionHandler.ServerConnection.connectToServer("45.9.43.73", 3000); /*"5.35.83.210", 54000, "192.168.1.177", 3000,  tcp://5.tcp.eu.ngrok.io:18547, "45.9.43.73", 3000*/
        });
        exitBtn.setOnClickListener(l -> MainActivity.this.finish());

        settingsBtn.setOnClickListener(l -> {
            VariableToast.makeText(this, "В разработке", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(this, SettingsActivity.class));
        });
    }

}