package com.example.trabfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView calendar;
    private ImageView backwards;
    private ImageView forwards;
    private long currentDate, selectedDate;
    private static final long MAXDAYS_MS = 1296000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = (CalendarView) findViewById(R.id.calendarView);
        currentDate = System.currentTimeMillis();
        calendar.setMinDate(currentDate);
        calendar.setMaxDate(currentDate + MAXDAYS_MS);
        selectedDate = currentDate;

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, day);
                selectedDate = c.getTimeInMillis(); //this is what you want to use later
            }
        });

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

    }

    private void send_back(View view) {
        Intent i = new Intent(this, SelectCourtTypeActivity.class);
        startActivity(i);
    }
    private void send_forward(View view) {
        Intent i = new Intent(this, SelectTimeActivity.class);
        i.putExtra("date", selectedDate);
        startActivity(i);
    }

}