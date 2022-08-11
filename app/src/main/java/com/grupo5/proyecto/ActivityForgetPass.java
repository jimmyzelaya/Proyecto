package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

public class ActivityForgetPass extends AppCompatActivity {
    Button btnEnvidar;
    EditText txtCorreo;
    ImageView regresar;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        Init();
        regresar.setOnClickListener(this::onClickRegresar);
        btnEnvidar.setOnClickListener(this::onClickSend);
    }

    private void onClickSend(View view) {
        if (Utilities.emptyFields(txtCorreo)) {
            if (Utilities.validateEmail(txtCorreo.getText().toString())) {
                send();
            } else Utilities.message("El correo electronico no es valido.", getApplicationContext());
        } else Utilities.message("Debe ingresar el correo electronico.", getApplicationContext());
    }

    private void send() {
        try {
            queue = Volley.newRequestQueue(this);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("email", txtCorreo.getText().toString());

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                    ApiConfigurations.resetPasswordEndpoint,
                    new JSONObject(parameters),
                    response -> {
                        if (response.length() > 0) {
                            try {
                                JSONArray resp = response.toJSONArray(response.names());
                                if (resp.length() > 1){
                                    Utilities.message("Correo enviado existosamente", getApplicationContext());
                                    Intent newPassword = new Intent(getApplicationContext(), ActivityNewPassword.class);
                                    newPassword.putExtra("email", txtCorreo.getText().toString());
                                    startActivity(newPassword);
                                    finish();
                                } else Utilities.message(resp.getString(0), getApplicationContext());
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

    private void onClickRegresar(View view) {
        Intent logout = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(logout);
        finish();
    }

    private void Init() {
        regresar = findViewById(R.id.imgvSalir);
        btnEnvidar = findViewById(R.id.btnEnviar);
        txtCorreo = findViewById(R.id.txtcorreo);
    }
}