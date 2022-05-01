package com.example.trabfinal.reservecourt;

import static com.example.trabfinal.Utils.isSendableSpinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.trabfinal.R;

public class SelectCourtNumberActivity extends AppCompatActivity {
    private Spinner spinnerCourtNumbers;
    private ImageView backwards;
    private ImageView forwards;

    private String selectedCourtType;
    private String selectedCourtNumber;
    private String selectedTime;
    private int selectedTimeId;
    private long selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_court_number);

        backwards = (ImageView) findViewById(R.id.backwards);
        backwards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { send_back(view); }
        });

        forwards = (ImageView) findViewById(R.id.forwards);
        forwards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSendableSpinner(spinnerCourtNumbers))
                    send_forward(view);
            }
        });

        spinnerCourtNumbers = (Spinner) findViewById(R.id.spinnerCourtNumbers);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.court_types, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.court_numbers, R.layout.spinner_text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourtNumbers.setPrompt("@string/spinner_court_numbers");
        spinnerCourtNumbers.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected_2,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        selectedCourtNumber = getIntent().getStringExtra("court_number");
        selectedCourtType = getIntent().getStringExtra("court_type");
        selectedDate = getIntent().getLongExtra("date", 0);
        selectedTime = getIntent().getStringExtra("time");
        selectedTimeId = getIntent().getIntExtra("time_id", 0);

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

    private void send_forward(View view) {
        Intent i = new Intent(this, ConfirmActivity.class);
        i.putExtra("date", selectedDate);
        i.putExtra("court_type", selectedCourtType);
        i.putExtra("time", selectedTime);
        i.putExtra("time_id", selectedTimeId);
        i.putExtra("court_number", (String) spinnerCourtNumbers.getSelectedItem());
        startActivity(i);
    }

}