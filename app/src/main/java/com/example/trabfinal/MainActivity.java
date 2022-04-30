package com.example.trabfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button buttonAgendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Locale locale = new Locale("pt", "BR");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        buttonAgendar = (Button) findViewById(R.id.buttonAgendar);
        buttonAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { send(view); }
        });
    }

    private void send(View view) {
        Intent i = new Intent(this, SelectCourtTypeActivity.class);
        startActivity(i);
    }
}