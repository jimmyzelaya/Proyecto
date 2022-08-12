package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ActivityDashboardCliente extends AppCompatActivity {

    ImageView salir;
    LinearLayout tienda, perfil, pedidos, ubicacion, ajustes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_cliente);
        Init();
        salir.setOnClickListener(this::onClickSalir);
    }

    private void onClickSalir(View view) {
        Intent logout = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(logout);
        //finish();
    }

    private void Init() {
        salir = findViewById(R.id.imgvSalir);
    }


}