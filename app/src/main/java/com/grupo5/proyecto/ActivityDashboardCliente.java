package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ActivityDashboardCliente extends AppCompatActivity {

    ImageView salir, tienda;
    LinearLayout  perfil, pedidos, ubicacion, ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_cliente);
        Init();
        salir.setOnClickListener(this::onClickSalir);
        pedidos.setOnClickListener(this::onClickPedidosActivos);
        tienda.setOnClickListener(this::onClickTienda);

    }

    private void onClickSalir(View view) {
        Intent logout = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(logout);
        finish();
    }
    private void onClickPedidosActivos(View view) {

    }

    private void onClickTienda(View view) {
        Intent Tienda = new Intent(getApplicationContext(), ActivityTienda.class);
        startActivity(Tienda);
        finish();
    }


    private void Init() {
        salir = findViewById(R.id.imgvSalir);
        tienda= findViewById(R.id.imgTienda);
    }


}