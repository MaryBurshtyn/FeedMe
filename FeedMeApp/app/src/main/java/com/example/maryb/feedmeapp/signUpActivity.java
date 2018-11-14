package com.example.maryb.feedmeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signUpActivity extends AppCompatActivity {

    private String username;
    private String password;

    private Button mSignUpButton;
    private EditText mEditTextName;
    private EditText mEditTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        View v = getWindow().getDecorView();
        v.setBackgroundColor(Color.parseColor("#ebc9ff"));


        mEditTextName = findViewById(R.id.userText);
        mEditTextPassword = findViewById(R.id.passText);

        mSignUpButton = findViewById(R.id.signUp);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                username = mEditTextName.getText().toString();
                password = mEditTextPassword.getText().toString();
                if(username.equals("Mary") && password.equals("qwerty")){
                    Intent nextActivity = new Intent(signUpActivity.this, petActivity.class);
                    startActivity(nextActivity);
                }
                else {
                    mEditTextName.clearComposingText();
                    mEditTextPassword.clearComposingText();
                    Toast.makeText(getApplicationContext(), "Try one more time.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
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
    @Override
    public void onBackPressed() {}
}
