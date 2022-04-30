package com.example.trabfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class SelectCourtNumberActivity extends AppCompatActivity {
    private Spinner spinnerCourtNumbers;
    private ImageView backwards;
    private ImageView forwards;

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
            public void onClick(View view) { send_forward(view); }
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
                        R.layout.contact_spinner_row_nothing_selected,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));
    }

    private void send_back(View view) {
        Intent i = new Intent(this, SelectTimeActivity.class);
        startActivity(i);
    }
    private void send_forward(View view) {
        Intent i = new Intent(this, ConfirmActivity.class);
        startActivity(i);
    }

}