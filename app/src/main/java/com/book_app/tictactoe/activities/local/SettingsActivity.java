package com.book_app.tictactoe.activities.local;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.book_app.tictactoe.R;
import com.book_app.tictactoe.application.settings.SettingsManager;

import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {

    private Switch theme_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        SettingsManager.setContext(this);

        theme_switch = findViewById(R.id.theme_switch);

        theme_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                    SettingsManager.setThemeChanged(isChecked);

            }
        });



    }
}