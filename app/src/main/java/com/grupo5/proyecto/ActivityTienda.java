package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActivityTienda extends AppCompatActivity {
    ImageView regresarMenu,bebidas,lacteos,carnes,areaBebes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);


//        Init();
//
//        regresarMenu.setOnClickListener(this::onClickSalirMenu);
//        bebidas.setOnClickListener(this::onClickBebidas);
//        lacteos.setOnClickListener(this::OnClickLacteos);
//        carnes.setOnClickListener(this::OnClickCarnes);
    }

    private void OnClickCarnes(View view) {
    }

    private void OnClickLacteos(View view) {
    }

    private void onClickBebidas(View view) {
        Intent bebidas = new Intent(getApplicationContext(), ActivityBebidas.class);
        startActivity(bebidas);
    }

    private void onClickSalirMenu(View view) {
        /*Intent tienda = new Intent(getApplicationContext(), A.class);
        startActivity(tienda);*/
    }




}