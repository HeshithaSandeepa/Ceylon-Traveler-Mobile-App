package com.travel.ceylontraveler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        getSupportActionBar().hide(); // up action bar hide command
        new Handler().postDelayed(new Runnable() {   //run splash screen to main screen
            @Override
            public void run() {
                Intent intent =new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(intent);
            }
        },5000);//  delay time
    }
}