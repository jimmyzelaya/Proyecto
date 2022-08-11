package com.grupo5.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.grupo5.proyecto.Configurations.ApiConfigurations.ApiConfigurations;
import com.grupo5.proyecto.Configurations.SQLiteConnection.SQLiteConnections;
import com.grupo5.proyecto.Utilities.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ActivityRegistrarUsuario extends AppCompatActivity {

    private EditText dni;
    private EditText names;
    private EditText surnames;
    private EditText genre;
    private EditText phone;
    private EditText address;
    private ImageView profile;

    Button btnRegistrarUsuarioRegistrar;

    SQLiteConnections connections;
    RequestQueue queue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        dni = (EditText) findViewById(R.id.txtdni);
        names = (EditText) findViewById(R.id.txtnombre);
        surnames = (EditText) findViewById(R.id.txtapeliido);
        genre = (EditText) findViewById(R.id.txtgenero);
        phone = (EditText) findViewById(R.id.txttelefono);
        address = (EditText) findViewById(R.id.txtdireccion);
        profile = (ImageView) findViewById(R.id.idFotoPerfilAdmin);
        ImageView imgvSalir = (ImageView) findViewById(R.id.imgvSalir);
        btnRegistrarUsuarioRegistrar = (findViewById(R.id.btnRegistrarse));

        imgvSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
            }
        });
        btnRegistrarUsuarioRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validarDatos();

            }
        });

    }

    private void validarDatos() {

        if (dni.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Debe de escribir un DNI", Toast.LENGTH_LONG).show();
        } else if (names.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Debe de escribir un nombre", Toast.LENGTH_LONG).show();
        } else if (surnames.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Debe escribir un apellido", Toast.LENGTH_LONG).show();
        } else if (genre.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Debe de escribir un genero", Toast.LENGTH_LONG).show();
        } else if (phone.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Debe de escribir un telefono", Toast.LENGTH_LONG).show();
        } else if (address.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Debe de escribir una dirección", Toast.LENGTH_LONG).show();
        } else {
            //RegistrarUsuario();
           createUser();
        }
    }


public void createUser()
{
    try{
        queue = Volley.newRequestQueue(getApplicationContext());
    HashMap<String, String> parameter = new HashMap<>();
    parameter.put("dni", dni.getText().toString());
    parameter.put("nombre", names.getText().toString());
    parameter.put("apellidos", surnames.getText().toString());
    parameter.put("genero", genre.getText().toString());
    parameter.put("telefono", phone.getText().toString());
    parameter.put("direccion", address.getText().toString());

    JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
            ApiConfigurations.createUsersEndpoint,
            new JSONObject(parameter),
            response -> {
                if (response.length() > 0) {
                    try {
                        JSONArray resp = response.toJSONArray(response.names());
                        Utilities.message("Se a registrado correctamente.", getApplicationContext());
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
/*
    private void RegistrarUsuario() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        HashMap<String, String> parametros = new HashMap<>();

        //String fotoString = GetStringImage(imagen);

        parametros.put("dni", dni.getText().toString());
        parametros.put("nombre", names.getText().toString());
        parametros.put("apellidos", surnames.getText().toString());
        parametros.put("genero", genre.getText().toString());
        parametros.put("telefono", phone.getText().toString());
        parametros.put("direccion", address.getText().toString());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ApiConfigurations.createUsersEndpoint,
                new JSONObject(parametros), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Toast.makeText(getApplicationContext(), " " + response.getString("mensaje").toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),ActivityLogin.class);
                    startActivity(intent);
                    //finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
        limpiar();
    }
*/
    private void limpiar(){
        Utilities.clearFields(dni);
        Utilities.clearFields(names);
        Utilities.clearFields(surnames);
        Utilities.clearFields(genre);
        Utilities.clearFields(phone);
        Utilities.clearFields(address);
    }


}