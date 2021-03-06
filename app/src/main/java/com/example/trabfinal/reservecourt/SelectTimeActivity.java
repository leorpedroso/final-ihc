package com.example.trabfinal.reservecourt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trabfinal.R;
import com.example.trabfinal.Utils;
import com.example.trabfinal.backend.Data;

public class SelectTimeActivity extends AppCompatActivity{

    private int colored_button = 0;
    private long selectedDate;
    private String selectedCourtType;

    private final Button[] buttons = new Button[13];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        // Get Intents
        selectedDate      = getIntent().getLongExtra("date",0);
        selectedCourtType = getIntent().getStringExtra("court_type");
        colored_button    = getIntent().getIntExtra("time_id",0);

        buttons[0] = (Button) findViewById(R.id.button8h);
        buttons[1] = (Button) findViewById(R.id.button9h);
        buttons[2] = (Button) findViewById(R.id.button10h);
        buttons[3] = (Button) findViewById(R.id.button11h);
        buttons[4] = (Button) findViewById(R.id.button12h);
        buttons[5] = (Button) findViewById(R.id.button13h);
        buttons[6] = (Button) findViewById(R.id.button14h);
        buttons[7] = (Button) findViewById(R.id.button15h);
        buttons[8] = (Button) findViewById(R.id.button16h);
        buttons[9] = (Button) findViewById(R.id.button17h);
        buttons[10] = (Button) findViewById(R.id.button18h);
        buttons[11] = (Button) findViewById(R.id.button19h);
        buttons[12] = (Button) findViewById(R.id.button20h);

        for (Button button: buttons) {
            String[] s = Utils.filterUnavailableCourts(selectedCourtType, selectedCourtType + Utils.formatDate(selectedDate) + button.getText());
            if (s != null && s.length > 0) {
                button.setOnClickListener(view -> {
                    if (colored_button != 0)
                        setButtonDefaultColors(findViewById(colored_button));

                    setButtonClickedColors(button);
                    colored_button = button.getId();
                });
            }
            else {
                setButtonBlockedColors(button);
            }
        }

        ImageView backwards = (ImageView) findViewById(R.id.backwards);
        backwards.setOnClickListener(this::send_back);

        ImageView forwards = (ImageView) findViewById(R.id.forwards);
        forwards.setOnClickListener(view -> {
            if (Utils.isSendableTime(colored_button))
                send_forward();
        });

        if (colored_button != 0)
            setButtonClickedColors(findViewById(colored_button));

        TextView showdate = findViewById(R.id.selected_date);
        showdate.setText(Utils.formatDate(selectedDate));
//        showdate.setText(selectedCourtType + ' ' + formattedDate);
    }

    private void send_back(View view) {
        Intent i = new Intent(this, CalendarActivity.class);
        i.putExtra("date", selectedDate);
        i.putExtra("court_type", selectedCourtType);
        startActivity(i);
    }

    private void send_forward() {
        Intent i = new Intent(this, SelectCourtNumberActivity.class);
        i.putExtra("date", selectedDate);
        i.putExtra("court_type", selectedCourtType);
        i.putExtra("time_id", colored_button);
        i.putExtra("time", ((Button) findViewById(colored_button)).getText() );
        startActivity(i);
    }

    private void setButtonDefaultColors(Button button) {
        button.setBackgroundColor(getResources().getColor(R.color.gray));
        button.setTextColor(getResources().getColor(R.color.black));
    }

    private void setButtonClickedColors(Button button) {
        button.setBackgroundColor(getResources().getColor(R.color.teal_200));
        button.setTextColor(getResources().getColor(R.color.white));
    }

    private void setButtonBlockedColors(Button button) {
        button.setBackgroundColor(getResources().getColor(R.color.light_gray));
        button.setTextColor(getResources().getColor(R.color.gray));
    }

}