package com.example.trabfinal.reservecourt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trabfinal.R;
import com.example.trabfinal.Utils;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendar;

    private long selectedDate;
    private String courtType;
    private static final long MAX_DAYS_MS = 1296000000; // 15 days in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = (CalendarView) findViewById(R.id.calendarView);
        long currentDate = System.currentTimeMillis();
        calendar.setMinDate(currentDate);
        calendar.setMaxDate(currentDate + MAX_DAYS_MS);

        selectedDate = getIntent().getLongExtra("date", currentDate);
        courtType = getIntent().getStringExtra("court_type");

        calendar.setOnDateChangeListener((view, year, month, day) -> {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            selectedDate = c.getTimeInMillis();
        });

        setCalendarInit(currentDate, selectedDate);

        ImageView backwards = (ImageView) findViewById(R.id.backwards);
        backwards.setOnClickListener(this::send_back);

        ImageView forwards = (ImageView) findViewById(R.id.forwards);
        forwards.setOnClickListener(this::send_forward);

        TextView selectedCourt = findViewById(R.id.textView5);
        selectedCourt.setText(courtType);

        TextView dataInfo = findViewById(R.id.textViewDataInfo);
        dataInfo.setText("Selecione uma data entre " + Utils.formatDateNoYear(currentDate) + " e " + Utils.formatDateNoYear(currentDate + MAX_DAYS_MS));
    }

    private void send_back(View view) {
        Intent i = new Intent(this, SelectCourtTypeActivity.class);
        i.putExtra("court_type", courtType);
        startActivity(i);
    }

    private void send_forward(View view) {
        Intent i = new Intent(this, SelectTimeActivity.class);
        i.putExtra("court_type", courtType);
        i.putExtra("date", selectedDate);
        startActivity(i);
    }

    private void setCalendarInit(long current, long dateInMillis) {
        if (dateInMillis != 0)
            selectedDate = dateInMillis;
        else
            selectedDate = current;

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(selectedDate);
        calendar.setDate(selectedDate);
    }

}