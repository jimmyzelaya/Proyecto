package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityListarProductos extends AppCompatActivity {
ImageView imgProduc;
ListView listaproduct;
EditText  idpro,nombrePro;
Products productos;
ClipData.Item item;
    //ArrayList<Products> contacts = new ArrayList<>();
    private final ArrayList<Products> Listaproductos = new ArrayList<>();

    /*public ActivityListarProductos(ArrayList<Products> listaproductos) {
        Listaproductos = listaproductos;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_productos);
        idpro=(EditText)findViewById(R.id.txtid) ;
       // imagenProductos=(ImageView) findViewById(R.id.imgPorducts);
        listaproduct=(ListView)findViewById(R.id.listaproductos);
        listarProductos();
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
                      Products  product = new Products(
                                RowProductos.getString("pid"),
                                RowProductos.getString("nombre"),
                              RowProductos.getString("imagen_producto"));

                        Listaproductos.add(product);
                    }
                    listaproduct.setAdapter((ListAdapter) productos);
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

    class AdaptadorProduct extends ArrayAdapter<Products> {

        AppCompatActivity appCompatActivity;

        AdaptadorProduct(AppCompatActivity context) {
            super(context,R.layout.productos,Listaproductos);
            appCompatActivity = context;
        }



        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = appCompatActivity.getLayoutInflater();
            View item = inflater.inflate(R.layout.productos, null);

            imgProduc = item.findViewById(R.id.imgProduc);
            mostrarFoto(Listaproductos.get(position).getImagenProducto(), imgProduc);

            nombrePro = item.findViewById(R.id.txtNombreProduc);
            String nombreproduct = Listaproductos.get(position).getNombre();
            nombrePro.setText(nombreproduct);

            return (item);
        }


    public void mostrarFoto(String foto, ImageView Foto) {
        try {
            String base64String = "data:image/png;base64,"+foto;
            String base64Image = base64String.split(",")[1];
            byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Foto.setImageBitmap(decodedByte);//setea la imagen al imageView
        }catch (Exception ex){
            ex.toString();
        }
    }






/*public void init()
{

}
*/





}}