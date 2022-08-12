package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.grupo5.proyecto.Configurations.ApiConfigurations.ApiConfigurations;
import com.grupo5.proyecto.Utilities.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ActivityNewPassword extends AppCompatActivity {
    EditText txtCodigo, txtClave, txtClave2;
    Button btnCambiar;
    String email;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        init();
        btnCambiar.setOnClickListener(this::onClickResetPasswordButton);
    }

    private void onClickResetPasswordButton(View view) {
        if (Utilities.emptyFields(txtCodigo)) {
            if (Utilities.emptyFields(txtClave) && Utilities.emptyFields(txtClave2)) {
                if (Utilities.validatePassword(txtClave.getText().toString(), txtClave2.getText().toString())) {
                    newPassword();
                } else Utilities.message("Las contraseñas no coinciden", getApplicationContext());
            } else Utilities.message("Debe ingresar la contraseña y confirmarla.", getApplicationContext());
        } else Utilities.message("Debe ingresar el codigo. El codigo se le envio a su correo electronico -> " + email, getApplicationContext());
    }

    private void newPassword() {
        try {
            queue = Volley.newRequestQueue(this);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("email", email);
            parameters.put("resetCode", txtCodigo.getText().toString());
            parameters.put("newPassword", txtClave.getText().toString());

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                    ApiConfigurations.newPasswordEndpoint,
                    new JSONObject(parameters),
                    response -> {
                        if (response.length() > 0) {
                            try {
                                JSONArray resp = response.toJSONArray(response.names());
                                Utilities.message(resp.getString(0), getApplicationContext());
                                Intent login = new Intent(getApplicationContext(), ActivityLogin.class);
                                startActivity(login);
                                finish();
                            } catch (JSONException e) {
                                Utilities.message(e.getMessage(), getApplicationContext());
                            }
                        }
                    }, error -> Utilities.message(error.getMessage(), getApplicationContext()));
            queue.add(jsonRequest);
        } catch (Exception ex) {
            Utilities.message(ex.getMessage(), getApplicationContext());
        }
    }

    private void init(){
        txtCodigo = findViewById(R.id.txtNCode);
        txtClave = findViewById(R.id.txtNPassword);
        txtClave2 = findViewById(R.id.txtNCPassword);
        btnCambiar = findViewById(R.id.btnResetPassword);
        email = getIntent().getExtras().getString("email");
    }
}