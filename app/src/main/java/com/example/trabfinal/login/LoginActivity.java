package com.example.trabfinal.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trabfinal.MainActivity;
import com.example.trabfinal.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonConfirm = (Button) findViewById(R.id.confirm_button);
        buttonConfirm.setOnClickListener(this::send);

        TextView textRegister = (TextView) findViewById(R.id.register);
        textRegister.setOnClickListener(this::send_register);
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