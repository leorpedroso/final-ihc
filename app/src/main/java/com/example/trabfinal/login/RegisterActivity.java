package com.example.trabfinal.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.trabfinal.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(this::send);

        TextView textLogin = (TextView) findViewById(R.id.already_created);
        textLogin.setOnClickListener(this::send);
    }

    private void send(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

}