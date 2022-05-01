package com.example.trabfinal.reservecourt;

import static com.example.trabfinal.Utils.isSendableSpinner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.trabfinal.MainActivity;
import com.example.trabfinal.R;

public class SelectCourtTypeActivity extends AppCompatActivity {
    private Spinner spinnerCourtTypes;
    private ImageView backwards;
    private ImageView forwards;

    private String selectedCourtType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_court_type);

        selectedCourtType = getIntent().getStringExtra("court_type");

        spinnerCourtTypes = (Spinner) findViewById(R.id.spinnerCourtTypes);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.court_types, R.layout.spinner_text);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourtTypes.setPrompt("@string/spinner_court_types");
        spinnerCourtTypes.setAdapter(
            new NothingSelectedSpinnerAdapter(
                adapter,
                R.layout.contact_spinner_row_nothing_selected,
                // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                this));

        if (selectedCourtType != null && !selectedCourtType.isEmpty())
            spinnerCourtTypes.setSelection(adapter.getPosition(selectedCourtType)+1);

        backwards = (ImageView) findViewById(R.id.backwards);
        backwards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { send_back(view); }
        });

        forwards = (ImageView) findViewById(R.id.forwards);
        forwards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSendableSpinner(spinnerCourtTypes))
                    send_forward(view);
            }
        });
    }

    private void send_back(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

   private void send_forward(View view) {
        Intent i = new Intent(this, CalendarActivity.class);
        i.putExtra("court_type", (String) spinnerCourtTypes.getSelectedItem());
        startActivity(i);
    }

}