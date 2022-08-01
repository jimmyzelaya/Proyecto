package com.grupo5.proyecto.Utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class Utilities {
    public static void alert(String title, Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(title)
                .setPositiveButton("Ok", (dialog, id) -> {});
        builder.create();
    }

    public static void message(String msg, Context context){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean emptyFields(EditText field){
        return field.getText().toString().length() > 1;
    }
    // Snackbar
}
