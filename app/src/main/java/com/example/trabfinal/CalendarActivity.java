package com.example.trabfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendar;
    private ImageView backwards;
    private ImageView forwards;
    private TextView selectedCourt;

    private long currentDate, selectedDate;
    private String courtType;
    private static final long MAXDAYS_MS = 1296000000; // 15 days in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = (CalendarView) findViewById(R.id.calendarView);
        currentDate = System.currentTimeMillis();
        calendar.setMinDate(currentDate);
        calendar.setMaxDate(currentDate + MAXDAYS_MS);

        selectedDate = getIntent().getLongExtra("date", currentDate);
        courtType = getIntent().getStringExtra("court_type");

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, day);
                selectedDate = c.getTimeInMillis();
            }
        });

        setCalendarInit(currentDate, selectedDate);

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

        selectedCourt = findViewById(R.id.textView5);
        selectedCourt.setText(courtType);
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