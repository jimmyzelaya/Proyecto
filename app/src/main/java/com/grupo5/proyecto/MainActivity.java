package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtLogo;
    ImageView imageLogo;
    Animation animation1, animation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        txtLogo.setAnimation(animation2);
        imageLogo.setAnimation(animation1);
        new Handler().postDelayed(this::run, 4000);
    }

    private void init(){
        txtLogo = findViewById(R.id.textViewGrupo);
        imageLogo = findViewById(R.id.imageViewLogo);
        animation1 = AnimationUtils.loadAnimation(this, R.anim.scroll_up);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.scroll_down);
    }

    private void run() {
        Intent login = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(login);
        finish();
    }
}