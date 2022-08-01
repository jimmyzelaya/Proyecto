package com.grupo5.proyecto.Utilities;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class Utilities {
    public static void message(String msg, Context context){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean emptyFields(EditText field){
        return field.getText().toString().length() > 1;
    }

    public static void clearFields(EditText field){
        field.setText("");
    }
}
