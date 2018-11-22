package com.example.maryb.feedmeapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    private Settings settings;
    private Switch soundSwitch;
    private Switch notificationSwitch;
    private Switch autoModeSwitch;
    private ImageButton mHomeButton;
    private TextView mPetName;
    private TextView mKindOfPet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        View v = getWindow().getDecorView();
        v.setBackgroundColor(Color.parseColor("#ebc9ff"));
        mHomeButton = findViewById(R.id.homeButton);
        mPetName = findViewById(R.id.penNameTextView);
        mKindOfPet = findViewById(R.id.kindTextView);
        soundSwitch = findViewById(R.id.soundSwitch);
        notificationSwitch = findViewById(R.id.Notificationswitch);
        autoModeSwitch = findViewById(R.id.autoModeSwitch);
        settings = Settings.getInstance();
        mPetName.setText(settings.getPetName());
        mKindOfPet.setText(settings.getKindOfAnimal());
        soundSwitch.setChecked(settings.getSoundSetting());
        notificationSwitch.setChecked(settings.getNotificationSetting());
        autoModeSwitch.setChecked(settings.getAutoModeSetting());
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(SettingsActivity.this, FeedActivity.class);
                startActivity(nextActivity);
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

    //@Override
    //public void onBackPressed() {}
}
