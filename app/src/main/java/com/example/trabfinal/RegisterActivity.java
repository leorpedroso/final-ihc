package com.example.trabfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    private Button buttonConfirm;
    private TextView textLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonConfirm = (Button) findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { send(view); }
        });

        textLogin = (TextView) findViewById(R.id.already_created);
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { send(view); }
        });
    }

    private void send(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

}