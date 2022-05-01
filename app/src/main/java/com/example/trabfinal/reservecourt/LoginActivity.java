package com.example.trabfinal.reservecourt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trabfinal.MainActivity;
import com.example.trabfinal.R;

public class LoginActivity extends AppCompatActivity {

    private Button buttonConfirm;
    private TextView textRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonConfirm = (Button) findViewById(R.id.confirm_button);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { send(view); }
        });

        textRegister = (TextView) findViewById(R.id.register);
        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { send_register(view); }
        });
    }

    private void send_register(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    private void send(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}