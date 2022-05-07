package com.example.trabfinal.reservecourt;

import static com.example.trabfinal.Utils.isSendableSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trabfinal.R;
import com.example.trabfinal.Utils;
import com.example.trabfinal.backend.Data;

public class SelectCourtNumberActivity extends AppCompatActivity {
    private Spinner spinnerCourtNumbers;

    private String selectedCourtType;
    private String selectedTime;
    private int selectedTimeId;
    private long selectedDate;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_court_number);

        String selectedCourtNumber = getIntent().getStringExtra("court_number");
        selectedCourtType = getIntent().getStringExtra("court_type");
        selectedDate = getIntent().getLongExtra("date", 0);
        selectedTime = getIntent().getStringExtra("time");
        selectedTimeId = getIntent().getIntExtra("time_id", 0);

        ImageView backwards = findViewById(R.id.backwards);
        backwards.setOnClickListener(this::send_back);

        ImageView forwards = findViewById(R.id.forwards);
        forwards.setOnClickListener(view -> {
            if (isSendableSpinner(spinnerCourtNumbers))
                send_forward();
        });

        ImageView mapImage = new ImageView(this);
        mapImage.setImageResource(R.drawable.quadras);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("Fechar", (dialog, id) -> {
            dialog.dismiss();
        });

        dialog = builder.create();
        dialog.setView(mapImage);

        Button buttonMap = findViewById(R.id.buttonMap);
        buttonMap.setOnClickListener(view -> dialog.show());

        spinnerCourtNumbers = findViewById(R.id.spinnerCourtNumbers);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.court_numbers, R.layout.spinner_text);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.spinner_text,
                Utils.filterUnavailableCourts(selectedCourtType, selectedCourtType + Utils.formatDate(selectedDate) + selectedTime));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourtNumbers.setPrompt("@string/spinner_court_numbers");
        spinnerCourtNumbers.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected_2,
                        this));

        if (selectedCourtNumber != null && !selectedCourtNumber.isEmpty()) {
            spinnerCourtNumbers.setSelection(adapter.getPosition(selectedCourtNumber)+1);
        }
    }

    private void send_back(View view) {
        Intent i = new Intent(this, SelectTimeActivity.class);
        i.putExtra("date", selectedDate);
        i.putExtra("court_type", selectedCourtType);
        i.putExtra("time_id", selectedTimeId);
        i.putExtra("time", selectedTime);
        startActivity(i);
    }

    private void send_forward() {

        String info = selectedCourtType + Utils.formatDate(selectedDate) + selectedTime + (String) spinnerCourtNumbers.getSelectedItem();
        if (Data.getReservationsInfo().contains(info)) {
            Toast.makeText(getApplicationContext(), "Quadra indisponível!", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(this, ConfirmActivity.class);
            i.putExtra("date", selectedDate);
            i.putExtra("court_type", selectedCourtType);
            i.putExtra("time", selectedTime);
            i.putExtra("time_id", selectedTimeId);
            i.putExtra("court_number", (String) spinnerCourtNumbers.getSelectedItem());
            startActivity(i);
        }
    }
}