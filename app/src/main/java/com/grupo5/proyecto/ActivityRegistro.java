package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityRegistro extends AppCompatActivity {
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        init();
        login.setOnClickListener(this::onClickLog);
    }

    private void onClickLog(View view) {
        Intent login = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(login);
        finish();
    }

    private void init(){
        login = findViewById(R.id.txtRLogin);
    }
}