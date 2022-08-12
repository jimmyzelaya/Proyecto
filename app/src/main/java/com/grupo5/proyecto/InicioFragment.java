package com.grupo5.proyecto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.grupo5.proyecto.Objects.Products;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.grupo5.proyecto.Configurations.ApiConfigurations.ApiConfigurations;
import com.android.volley.toolbox.JsonObjectRequest;
import com.grupo5.proyecto.databinding.ActivityDashClienteBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {

    ImageView imgProductos;
    ListView listaProductos;

    private final ArrayList<Products> Listaproductos;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InicioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment(listaproductos);
        listarProductos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    ActivityDashClienteBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DashCliente.inflate(getLayoutInflater());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);



    }



    private void listarProductos(String codprod) {

        RequestQueue queue = Volley.newRequestQueue(this);
        HashMap<String, String> parametros = new HashMap<>();
        parametros.put("pid",codprod);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ApiConfigurations.getAllProductsEndpoint,
                new JSONObject(parametros), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray productoArray = response.getJSONArray("productos");

                    //listaUsuarios.clear();
                    for (int i = 0; i < productoArray.length(); i++) {
                        JSONObject RowProductos = productoArray.getJSONObject(i);
                        producto = new Products(RowProductos.getInt("pid"),
                                RowProductos.getString("nombre"),
                                RowProductos.getString("imagen_producto"),


                                listaproductos.add(producto);
                    }
                    listViewCustomAdapter.setAdapter(adaptador);

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


    public void init()
    {

    }






}