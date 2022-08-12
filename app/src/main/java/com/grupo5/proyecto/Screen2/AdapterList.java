package com.grupo5.proyecto.Screen2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.grupo5.proyecto.Objects.Products;
import com.grupo5.proyecto.R;
//import com.aplicacion.pm2e2grupo5.R;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class AdapterList extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context context;
    ArrayList<Products> listaDatos;
    TextView id, nombreProducts;
    ImageView imgproduct;

    public AdapterList(Response.Listener<JSONObject> jsonObjectListener, Products productos) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        @SuppressLint("InflateParams") final View vista = inflater.inflate(R.layout.item_list_screenproduct, null);

        return null;
    }



    private void init(View vista){

        imgproduct = vista.findViewById(R.id.imgPorducts);
    }




}



