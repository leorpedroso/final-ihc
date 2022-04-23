package com.example.trabfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SelectCourtTypeActivity extends AppCompatActivity {
    private Spinner spinnerCourtTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_court_type);

        spinnerCourtTypes = (Spinner) findViewById(R.id.spinnerCourtTypes);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.court_types, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.court_types, R.layout.spinner_text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourtTypes.setPrompt("@string/spinner_court_types");
//        spinnerCourtTypes.setAdapter(adapter);
        spinnerCourtTypes.setAdapter(
            new NothingSelectedSpinnerAdapter(
                adapter,
                R.layout.contact_spinner_row_nothing_selected,
                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                this));
    }

}