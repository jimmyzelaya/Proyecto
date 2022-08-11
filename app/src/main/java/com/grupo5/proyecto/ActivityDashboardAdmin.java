package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActivityDashboardAdmin extends AppCompatActivity {

    ImageView salir, tienda, perfil, pedidos, info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        Init();

        salir.setOnClickListener(this::onClickSalir);
        perfil.setOnClickListener(this::onClickPerfil);
        info.setOnClickListener(this::OnClickInfo);
        tienda.setOnClickListener(this::OnClickTienda);
    }

    private void OnClickTienda(View view) {
        Intent tienda = new Intent(getApplicationContext(), ActivityTienda.class);
        startActivity(tienda);
    }

    private void onClickPerfil(View view) {
        Intent Perfil = new Intent(getApplicationContext(), ActivityPerfilAdmin.class);
        startActivity(Perfil);
    }

    private void OnClickInfo(View view) {
        Intent info = new Intent(getApplicationContext(), ActivityInfo.class);
        startActivity(info);
    }

    private void onClickSalir(View view) {
        Intent logout = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(logout);
        finish();
    }

    private void Init()
    {
        tienda = findViewById(R.id.btnTienda);
        perfil = findViewById(R.id.btnPerfil);
        pedidos = findViewById(R.id.btnPedidos);
        info = findViewById(R.id.btnInfo);
        salir = findViewById(R.id.imgvSalir);
    }

}