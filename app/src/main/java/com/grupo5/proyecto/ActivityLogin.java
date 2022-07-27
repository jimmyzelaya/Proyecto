package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLogin extends AppCompatActivity {
    EditText correo, clave;
    TextView registro, forgetpass;
    Button iniciarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        registro.setOnClickListener(this::onClickRegis);
        iniciarSesion.setOnClickListener(this::onClickLogin);
        forgetpass.setOnClickListener(this::onClickForget);
    }

    private void onClickForget(View view) {
        Intent forgetpassword = new Intent(getApplicationContext(),ActivityForgetPass.class);
        startActivity(forgetpassword);
        finish();
    }

    private void onClickLogin(View view) {
        Intent login = new Intent(getApplicationContext(), ActivityDashboardCliente.class);
        startActivity(login);
        finish();
    }

    private void onClickRegis(View view) {
        Intent regis = new Intent(getApplicationContext(), ActivityRegistro.class);
        startActivity(regis);
        finish();
    }

    private void init(){
        correo = findViewById(R.id.txtLCorreo);
        clave = findViewById(R.id.txtLClave);
        registro = findViewById(R.id.txtLRegistro);
        forgetpass = findViewById(R.id.txtforget);
        iniciarSesion = findViewById(R.id.btnLIniciar);
    }
}