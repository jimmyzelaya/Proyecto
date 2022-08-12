package com.grupo5.proyecto.Utilities;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {
    public static void message(String msg, Context context){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static boolean emptyFields(EditText field){
        return field.getText().toString().length() > 0;
    }

    public static boolean validateEmail(String email){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }

    public static boolean validatePassword(String password1, String password2){
        return password1.equals(password2);
    }

    public static void clearFields(EditText field){
        field.setText("");
    }
}