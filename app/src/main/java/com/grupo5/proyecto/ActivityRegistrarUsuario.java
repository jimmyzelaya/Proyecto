package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.grupo5.proyecto.Configurations.ApiConfigurations.ApiConfigurations;
import com.grupo5.proyecto.Utilities.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class ActivityRegistrarUsuario extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    EditText txtUsuario, txtCorreo, txtClave, txtNombres, txtAppelidos, txtDNI, txtTelefono, txtDireccion;
    Spinner genero;
    Button btnRegistrar, btnTomarFoto, btnBuscarFoto;
    ImageView imgSalir, imgPerfil;
    RequestQueue queue;
    String generos[] = {"Seleccione su genero","Masculino", "Femenino"};
    Uri imageUri = null;
    Bitmap imageBitmap = null;
    String imgUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        init();
        btnBuscarFoto.setOnClickListener(this::onClickSearchPhoto);
        btnTomarFoto.setOnClickListener(this::onClickTakePhoto);
        btnRegistrar.setOnClickListener(this::onClickRegisterUser);
        imgSalir.setOnClickListener(this::onClickBack);
    }

    private void onClickRegisterUser(View view) {
        if (Utilities.emptyFields(txtUsuario) && Utilities.emptyFields(txtCorreo) && Utilities.emptyFields(txtClave) && Utilities.emptyFields(txtNombres) &&
                Utilities.emptyFields(txtAppelidos) && Utilities.emptyFields(txtDNI) && Utilities.emptyFields(txtTelefono) && genero.getSelectedItemId() != 0) {
            if (txtDNI.getText().length() == 13) {
                if (txtTelefono.getText().length() == 8) {
                    registerUser();
                } else Utilities.message("Numero de telefono invalido", getApplicationContext());
            } else Utilities.message("DNI invalido", getApplicationContext());

        } else Utilities.message("Debe llenar todos los campos", getApplicationContext());
    }

    private void registerUser() {
        convertBase64();
        try {
            queue = Volley.newRequestQueue(this);
            HashMap<String, String> parameters = new HashMap<>();
            parameters.put("usuario", txtUsuario.getText().toString());
            parameters.put("correo", txtCorreo.getText().toString());
            parameters.put("clave", txtClave.getText().toString());
            parameters.put("dni",txtDNI.getText().toString());
            parameters.put("nombres", txtNombres.getText().toString());
            parameters.put("apellidos", txtAppelidos.getText().toString());
            parameters.put("telefono", txtAppelidos.getText().toString());
            parameters.put("direccion", txtDireccion.getText().toString());
            parameters.put("genero", String.valueOf(genero.getSelectedItemId()));
            parameters.put("perfil", imgUser);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                    ApiConfigurations.createUsersEndpoint,
                    new JSONObject(parameters),
                    response -> {
                        if (response.length() > 0) {
                            try {
                                JSONArray resp = response.toJSONArray(response.names());
                                if (resp.getString(0).equals("Se a registrado correctamente.")) {
                                    Utilities.message(resp.getString(0), getApplicationContext());
                                    Intent newPassword = new Intent(getApplicationContext(), ActivityLogin.class);
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

    private void convertBase64(){
        if (imageUri != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                byte[] byteArray = outputStream.toByteArray();
                imgUser = Base64.encodeToString(byteArray, Base64.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (imageBitmap != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            byte[] byteArray = outputStream.toByteArray();
            imgUser = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }
    }

    private void onClickTakePhoto(View view) {
        if (imageUri != null) {
            dispatchTakePictureIntent();
            imageUri = null;
        } else {
            dispatchTakePictureIntent();
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void onClickBack(View view) {
        Intent login = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(login);
        finish();
    }

    private void onClickSearchPhoto(View view) {
        if (imageBitmap != null) {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_IMAGE);
            imageUri = null;
        } else {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imgPerfil.setImageURI(imageUri);
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imgPerfil.setImageBitmap(imageBitmap);
        }
    }

    private void init(){
        txtUsuario = findViewById(R.id.txtRUsuario);
        txtCorreo = findViewById(R.id.txtRCorreo);
        txtClave = findViewById(R.id.txtRClave);
        txtNombres = findViewById(R.id.txtRNombre);
        txtAppelidos = findViewById(R.id.txtRApellido);
        txtDNI = findViewById(R.id.txtRDNI);
        txtTelefono = findViewById(R.id.txtRTelefono);
        txtDireccion = findViewById(R.id.txtRDireccion);
        genero = findViewById(R.id.txtRGenero);
        btnRegistrar = findViewById(R.id.btnRRegistrar);
        btnTomarFoto = findViewById(R.id.btnRTomarFoto);
        btnBuscarFoto = findViewById(R.id.btnRAbrirGaleria);
        imgSalir = findViewById(R.id.imgvSalir);
        imgPerfil = findViewById(R.id.txtRPerfil);
        ArrayAdapter<String> genre = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, generos);
        genero.setAdapter(genre);
    }
}