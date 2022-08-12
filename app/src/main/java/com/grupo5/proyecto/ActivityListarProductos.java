package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.grupo5.proyecto.Configurations.ApiConfigurations.ApiConfigurations;
import com.grupo5.proyecto.Objects.Products;
import com.grupo5.proyecto.Screen2.AdapterList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivityListarProductos extends AppCompatActivity {
ImageView imagenProductos;
ListView listaproduct;
EditText  idpro;
Products productos;
private final ArrayList<Products> Listaproductos;

    public ActivityListarProductos(ArrayList<Products> listaproductos) {
        Listaproductos = listaproductos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_productos);
        idpro=(EditText)findViewById(R.id.txtid) ;
        imagenProductos=(ImageView) findViewById(R.id.imgPorducts);
        listarProductos();
    }


    public static String ImageToBase64(String filePath) {
        Bitmap bmp = null;
        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        String encodeString = null;
        try {
            bmp = BitmapFactory.decodeFile(filePath);
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos);
            bt = bos.toByteArray();
            encodeString = Base64.encodeToString(bt, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeString;
    }







    private void listarProductos() {

        RequestQueue queue = Volley.newRequestQueue(this);
        HashMap<String, String> parametros = new HashMap<>();
        parametros.put("pid",productos.getPid());


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ApiConfigurations.getAllProductsEndpoint,
                new JSONObject(parametros), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray productoArray = response.getJSONArray("productos");

                    //listaUsuarios.clear();
                    for (int i = 0; i < productoArray.length(); i++) {
                        JSONObject RowProductos = productoArray.getJSONObject(i);
                        productos = new Products(
                                RowProductos.getString("pid"),
                                RowProductos.getString("nombre"),
                                RowProductos.getString("imagen_producto"));


                                Listaproductos.add(productos);
                    }
                    listaproduct.setAdapter(new AdapterList(this, productos));

                } catch (JSONException ex) {
                    //Toast.makeText(getApplicationContext(), "No tienes amigos en tu lista", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(jsonObjectRequest);
    }

/*public void init()
{

}
*/





}