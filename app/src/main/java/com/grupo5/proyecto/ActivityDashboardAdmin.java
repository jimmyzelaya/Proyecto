package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActivityDashboardAdmin extends AppCompatActivity {

    ImageView salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        Init();
        salir.setOnClickListener(this::onClickSalir);
    }



    private void onClickSalir(View view) {
        Intent logout = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(logout);
        finish();
    }

    private void Init() {
        salir = findViewById(R.id.imgvSalir);
    }

}