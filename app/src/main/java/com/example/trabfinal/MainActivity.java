package com.example.trabfinal;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.trabfinal.backend.Data;
import com.example.trabfinal.backend.Reservation;
import com.example.trabfinal.login.RegisterActivity;
import com.example.trabfinal.reservecourt.SelectCourtTypeActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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
        builder.setNegativeButton("Fechar", (dialog, id) -> dialog.dismiss());

        dialog = builder.create();
        dialog.setView(mapImage);

        Button buttonMap = findViewById(R.id.buttonMapa);
        buttonMap.setOnClickListener(this::show_dialog);

        Button buttonReservations = findViewById(R.id.buttonHistorico);
        buttonReservations.setOnClickListener(this::send_reservations);

        Button buttonCamera = findViewById(R.id.buttonCheckin);
        buttonCamera.setOnClickListener(view -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            intentIntegrator.initiateScan();
        });

        TextView signout = findViewById(R.id.textDeslogar);
        signout.setOnClickListener(this::send_signout);

        TextView reservations = findViewById(R.id.textNumAgendamentos);
        String append;
        if (Data.getAvailableReservations() != 1)
            append = " agendamentos disponíveis";
        else
            append = " agendamento disponível";
        reservations.setText(Data.getAvailableReservations() + append);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable android.content.Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        try {
            if (result != null && !result.getContents().isEmpty()) {
                // If result was successfull
                Toast.makeText(getApplicationContext(), "Checkin realizado com sucesso!", Toast.LENGTH_LONG).show();
                for (Reservation r : Data.getReservations()) {
                    if (!r.getCheckInStatus()) {
                        r.setCheckIn(true);
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Falha ao realizar check in!", Toast.LENGTH_LONG).show();
        }
    }

    private void send(View view) {
        Intent i = new Intent(this, SelectCourtTypeActivity.class);
        startActivity(i);
    }

    private void send_signout(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    private void send_reservations(View view) {
        Intent i = new Intent(this, ReservationsActivity.class);
        startActivity(i);
    }

    private void show_dialog(View view) {
        dialog.show();
    }
}