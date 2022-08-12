package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.grupo5.proyecto.Configurations.ApiConfigurations.ApiConfigurations;
import com.grupo5.proyecto.Configurations.SQLiteConnection.SQLiteConnections;
import com.grupo5.proyecto.Configurations.SQLiteConnection.Transactions;
import com.grupo5.proyecto.Objects.Users;
import com.grupo5.proyecto.Utilities.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityLogin extends AppCompatActivity {
    EditText correo, clave;
    TextView registro, forgetpass;
    Button iniciarSesion;
    SQLiteConnections connections;
    RequestQueue queue;
    ArrayList<Users> datoDelUusario = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        iniciarSesion.setOnClickListener(this::onClickLogin);
        registro.setOnClickListener(this::onClickRegis);
        forgetpass.setOnClickListener(this::onClickForget);
    }

    private void onClickForget(View view) {
        Intent forgotPassword = new Intent(getApplicationContext(),ActivityForgetPass.class);
        startActivity(forgotPassword);
    }

    private void onClickLogin(View view) {
        if (Utilities.emptyFields(correo)){
            if (Utilities.emptyFields(clave)){
                login();
            } else Utilities.message("Debe ingresar su contraseña", getApplicationContext());
        } else Utilities.message("Debe ingresar su correo", getApplicationContext());
    }

    private void login() {
        try {
            queue = Volley.newRequestQueue(this);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("email", correo.getText().toString());
            parameters.put("password", clave.getText().toString());

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                    ApiConfigurations.loginEndpoint,
                    new JSONObject(parameters),
                    response -> {
                        if (response.length() > 0) {
                            try {
                                JSONArray resp = response.toJSONArray(response.names());
                                if (resp.length() > 1){
                                    if (saveNewCredentials(resp.getInt(1), resp.getString(2))){
                                        Utilities.message("Inicio de sesión exitoso", getApplicationContext());
                                        openActivity(resp.getInt(1));
                                    }
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

    private void onClickRegis(View view) {
        Intent regis = new Intent(getApplicationContext(), ActivityRegistrarUsuario.class);
        startActivity(regis);
    }

    private void init(){
        correo = findViewById(R.id.txtLCorreo);
        clave = findViewById(R.id.txtLClave);
        registro = findViewById(R.id.txtLRegistro);
        forgetpass = findViewById(R.id.txtforget);
        iniciarSesion = findViewById(R.id.btnLIniciar);

        if (checkIfExistsTokens() != "no"){
            loginWithToken(checkIfExistsTokens());
        }
    }

    private void loginWithToken(String credentials){
        try {
            String data[] = credentials.split("-");
            queue = Volley.newRequestQueue(this);
            Map<String, String> parameters = new HashMap<>();
            parameters.put("uid",data[0]);
            parameters.put("token", data[1]);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                    ApiConfigurations.loginWithTokenEndpoint,
                    new JSONObject(parameters),
                    response -> {
                        if (response.length() > 0) {
                            try {
                                JSONArray resp = response.toJSONArray(response.names());
                                if (resp.length() > 1) {
                                    if (ActivityLogin.this.saveNewCredentials(resp.getInt(1), resp.getString(2))) {
                                        Utilities.message("Inicio de sesión exitoso", ActivityLogin.this.getApplicationContext());
                                        openActivity(resp.getInt(1));
                                    }
                                } else
                                    Utilities.message(resp.getString(0), ActivityLogin.this.getApplicationContext());
                            } catch (JSONException e) {
                                Utilities.message(e.getMessage(), ActivityLogin.this.getApplicationContext());
                            }
                        }
                    },
                    error -> Utilities.message(error.getMessage(), getApplicationContext()));
            queue.add(jsonRequest);
        } catch (Exception ex) {
            Utilities.message(ex.getMessage(), getApplicationContext());
        }
    }

    private void openActivity(int uid){
        queue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET,
                ApiConfigurations.searchUsersEndpoint + "?uid=" + uid,
                null,
                response -> {
                    if (response.length() > 0) {
                        try {
                            JSONArray resp = response.toJSONArray(response.names());
                            Users datos = new Users(resp.getString(0), resp.getString(1), resp.getString(2), resp.getString(3), resp.getString(4),
                                    resp.getString(5), resp.getString(6), resp.getString(7), resp.getString(8), resp.getString(9), resp.getString(10),
                                    resp.getString(11), resp.getString(12));
                            datoDelUusario.add(datos);
                        } catch (JSONException e) {
                            Utilities.message(e.getMessage(), ActivityLogin.this.getApplicationContext());
                        }
                    }
                },
                error -> Utilities.message(error.getMessage(), getApplicationContext()));
        queue.add(objectRequest);

        if (datoDelUusario.size() > 0) {
            if (datoDelUusario.get(0).getRol().equals("Administrador")) {
                Intent dashboard = new Intent(getApplicationContext(), ActivityDashboardAdmin.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Objeto", datoDelUusario);
                dashboard.putExtras(bundle);
                startActivity(dashboard);
                finish();
            } else if (datoDelUusario.get(0).getRol().equals("Cliente")) {
                Intent dashboard = new Intent(getApplicationContext(), DashCliente.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Objeto", datoDelUusario);
                dashboard.putExtras(bundle);
                startActivity(dashboard);
                finish();
            } else {
                Intent dashboard = new Intent(getApplicationContext(), ActivityRepartidor.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Objeto", datoDelUusario);
                dashboard.putExtras(bundle);
                startActivity(dashboard);
                finish();
            }
        }
    }

    private boolean saveNewCredentials(int uid, String newToken){
        try {
            connections = new SQLiteConnections(getApplicationContext(), Transactions.NameDatabase, null, 2);
            SQLiteDatabase db = connections.getWritableDatabase();
            ContentValues value = new ContentValues();
            Cursor cursor = db.rawQuery(Transactions.consultCredentials, null);
            if (cursor.getCount() > 0){
                value.put("token", newToken);
                db.execSQL("UPDATE " + Transactions.tableCredentials + " SET token = '" + newToken + "' WHERE " + Transactions.uid + " = '" + uid + "'");
                return true;
            } else {
                value.put("uid", uid);
                value.put("token", newToken);
                Long result = db.insert(Transactions.tableCredentials, Transactions.uid, value);
                return result > 0;
            }
        } catch (Exception ex) {
            Utilities.message(ex.getMessage(), getApplicationContext());
        }
        return false;
    }

    private String checkIfExistsTokens(){
        String response = "no";
        try {
            connections = new SQLiteConnections(getApplicationContext(), Transactions.NameDatabase, null, 2);
            SQLiteDatabase db = connections.getWritableDatabase();
            Cursor cursor = db.rawQuery(Transactions.consultCredentials, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    response = cursor.getInt(0) + "-" + cursor.getString(1);
                }
            }
        }catch (Exception ex) {
            Utilities.message(ex.getMessage(), getApplicationContext());
        }
        return response;
    }
}