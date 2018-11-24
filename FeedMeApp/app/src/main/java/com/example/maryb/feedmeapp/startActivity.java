package com.example.maryb.feedmeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import java.lang.Runnable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class startActivity extends AppCompatActivity {
    private static int TIME_OUT = 4000;
    private final String firtRun = "first_run";
    private SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        sPref = getSharedPreferences(firtRun,MODE_PRIVATE);
        String firstRun = sPref.getString(firtRun, "");
        if( firstRun.equals("false")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent nextActivity = new Intent(startActivity.this, FeedActivity.class);
                    startActivity(nextActivity);
                }
            }, TIME_OUT);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent nextActivity = new Intent(startActivity.this, signUpActivity.class);
                    startActivity(nextActivity);
                }
            }, TIME_OUT);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
