package com.grupo5.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageLogo;
    Animation animation1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        imageLogo.setAnimation(animation1);
        new Handler().postDelayed(this::run, 2000);
    }

    private void init(){
        imageLogo = findViewById(R.id.imageViewLogo);
        animation1 = AnimationUtils.loadAnimation(this, R.anim.scroll_up);
    }

    private void run() {
        Intent login = new Intent(getApplicationContext(), ActivityLogin.class);
        startActivity(login);
        finish();
    }
}