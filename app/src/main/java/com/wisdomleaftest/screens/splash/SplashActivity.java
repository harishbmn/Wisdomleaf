package com.wisdomleaftest.screens.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.wisdomleaftest.R;
import com.wisdomleaftest.screens.main.ListActivity;

public class SplashActivity extends AppCompatActivity {
    private final static int time_delay = 200;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, ListActivity.class);
                startActivity(i);
                finish();
            }
        }, 5000);
    }

}