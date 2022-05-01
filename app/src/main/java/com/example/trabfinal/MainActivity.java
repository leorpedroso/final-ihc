package com.example.trabfinal;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.trabfinal.login.RegisterActivity;
import com.example.trabfinal.reservecourt.SelectCourtTypeActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AlertDialog dialog;

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

        ImageView mapImage = new ImageView(this);
        mapImage.setImageResource(R.drawable.quadras);

        Button buttonAgendar = findViewById(R.id.buttonAgendar);
        buttonAgendar.setOnClickListener(this::send);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("Fechar", (dialog, id) -> {
            dialog.dismiss();
        });

        dialog = builder.create();
        dialog.setView(mapImage);

        Button buttonMap = findViewById(R.id.buttonMapa);
        buttonMap.setOnClickListener(this::show_dialog);

        TextView signout = findViewById(R.id.textDeslogar);
        signout.setOnClickListener(this::send_signout);
    }

    private void send(View view) {
        Intent i = new Intent(this, SelectCourtTypeActivity.class);
        startActivity(i);
    }

    private void send_signout(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    private void show_dialog(View view) {
        dialog.show();
    }
}