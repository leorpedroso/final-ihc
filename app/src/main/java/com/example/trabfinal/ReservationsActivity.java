package com.example.trabfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabfinal.backend.Data;
import com.example.trabfinal.backend.Reservation;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ReservationsActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private Reservation selectedReservation;
    private TextView selectedTextView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        ImageView backwards = findViewById(R.id.backwards);
        backwards.setOnClickListener(this::send_back);

        LinearLayout layout = (LinearLayout) findViewById(R.id.my_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(0, dpsToPixels(24), 0, 0);
        params.gravity = Gravity.CENTER;

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this, R.style.CustomMaterialDialog);
        builder.setTitle("Cancelar reserva");
        builder.setMessage("Deseja cancelar este agendamento?");
        builder.setPositiveButton("Sim", (dialogInterface, i) -> removeReservation());
        builder.setNegativeButton("Não", (dialog, id) -> dialog.dismiss());

        dialog = builder.create();
        dialog.setMessage("Deseja cancelar este agendamento?");

        for (int i=0; i <= Data.getReservations().size() - 1; i++) {
            TextView txt = new TextView(this);
            Reservation r = Data.getReservations().get(i);
            txt.setLayoutParams(params);
            txt.setText(Utils.getReservationString(r));
            initText(txt);
            txt.setGravity(Gravity.CENTER);
            txt.setBackgroundResource(R.drawable.rectangle);
            layout.addView(txt);
            txt.setOnClickListener(view -> {
                selectedReservation = r;
                selectedTextView = txt;
                dialog.show();
            });
        }
    }

    private void removeReservation() {
        Data.removeReservation(selectedReservation);
        selectedTextView.setVisibility(View.GONE);
    }

    private void send_back(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void initText (TextView text) {
        text.setWidth(dpsToPixels(360));
        text.setHeight(dpsToPixels(74));
    }

    private int dpsToPixels (int dps) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps, getResources().getDisplayMetrics());
    }
}