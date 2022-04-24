package com.example.trabfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectTimeActivity extends AppCompatActivity {

    private TextView showdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        showdate = findViewById(R.id.selected_date);

        long date = getIntent().getLongExtra("date",0);
        Date selectedDate = new Date(date);

        DateFormat d = new SimpleDateFormat("dd:MM:yy");

        showdate.setText(d.format(selectedDate));

    }


}