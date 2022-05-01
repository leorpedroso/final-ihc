package com.example.trabfinal.reservecourt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trabfinal.R;
import com.example.trabfinal.Utils;

public class ConfirmActivity extends AppCompatActivity {

    private long selectedDate;
    private String selectedCourtType;
    private String selectedTime;
    private String selectedCourtNumber;
    private int selectedTimeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        ImageView backwards = (ImageView) findViewById(R.id.backwards);
        backwards.setOnClickListener(this::send_back);

        TextView courtType = findViewById(R.id.courtType);
        TextView date = findViewById(R.id.date);
        TextView time = findViewById(R.id.time);
        TextView courtNumber = findViewById(R.id.courtNumber);

        selectedCourtType = getIntent().getStringExtra("court_type");
        selectedDate = getIntent().getLongExtra("date", 0);
        selectedTime = getIntent().getStringExtra("time");
        selectedCourtNumber = getIntent().getStringExtra("court_number");
        selectedTimeId = getIntent().getIntExtra("time_id", 0);

        courtType.setText(selectedCourtType);
        date.setText(Utils.formatDate(selectedDate));
        time.setText(selectedTime);
        courtNumber.setText(selectedCourtNumber);
    }

    private void send_back(View view) {
        Intent i = new Intent(this, SelectCourtNumberActivity.class);
        i.putExtra("date", selectedDate);
        i.putExtra("court_type", selectedCourtType);
        i.putExtra("time_id", selectedTimeId);
        i.putExtra("time", selectedTime);
        i.putExtra("court_number", selectedCourtNumber);

        startActivity(i);
    }
}