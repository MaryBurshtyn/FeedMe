package com.example.maryb.feedmeapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.Calendar;
import java.util.Date;

public class FeedActivity extends AppCompatActivity {
    private Button mFeedButton;
    private ImageView mImageViewAnimals;
    private TextView mTextView;
    private MQTTClient client;
    private EditText mPortionEditText;
    private int portion;
    private Date currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //System.out.println("In feed activity on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        View v = getWindow().getDecorView();
        v.setBackgroundColor(Color.parseColor("#ebc9ff"));

        client = new MQTTClient(getBaseContext());
        client.connect();

        mPortionEditText = findViewById(R.id.portionEditText);

        mImageViewAnimals = findViewById(R.id.animalImageView);
        String animalId = getIntent().getStringExtra("ANIMAL");
        int imageId = Integer.parseInt(animalId,10);
        mImageViewAnimals.setImageResource(imageId);

        mTextView = findViewById(R.id.petsNameText);
        String petName = getIntent().getStringExtra("ANIMAL_NAME");
        mTextView.setText(petName);

        mFeedButton = findViewById(R.id.feedButton);
        mFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                portion = Integer.parseInt(mPortionEditText.getText().toString());
                if(portion >= 1){
                    Toast.makeText(getApplicationContext(), "Pet fed successfully.",
                            Toast.LENGTH_LONG).show();
                    String message = "feed(" + portion + ")";
                    client.publishMessage(message);
                    //currentTime = Calendar.getInstance().getTime();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter portion more than zero.",
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
}
