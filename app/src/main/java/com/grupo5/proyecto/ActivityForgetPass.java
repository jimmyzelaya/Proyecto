package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActivityForgetPass extends AppCompatActivity {

    ImageView regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        Init();
        regresar.setOnClickListener(this::onClickRegresar);
    }

    private void onClickRegresar(View view) {
        Intent logout = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(logout);
        finish();
    }

    private void Init() {
        regresar = findViewById(R.id.imgvSalir);
    }
}