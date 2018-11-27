package com.example.maryb.feedmeapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class SettingsActivity extends AppCompatActivity {
    private Settings settings;
    private Switch soundSwitch;
    private Switch notificationSwitch;
    private Switch autoModeSwitch;
    private ImageButton mHomeButton;
    private TextView mPetName;
    private TextView mMoney;
    private TextView mKindOfPet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        View v = getWindow().getDecorView();
        v.setBackgroundColor(Color.parseColor("#ebc9ff"));
        mMoney = findViewById(R.id.moneyTextView);
        mHomeButton = findViewById(R.id.homeButton);
        mPetName = findViewById(R.id.petNameTextView);
        mKindOfPet = findViewById(R.id.kindTextView);
        soundSwitch = findViewById(R.id.soundSwitch);
        notificationSwitch = findViewById(R.id.Notificationswitch);
        autoModeSwitch = findViewById(R.id.autoModeSwitch);
        settings = Settings.getInstance();
        mMoney.setText(Double.toString(settings.getCost()));
        mPetName.setText(settings.getPetName());
        mKindOfPet.setText(settings.getKindOfAnimal());
        soundSwitch.setChecked(settings.getSoundSetting());
        notificationSwitch.setChecked(settings.getNotificationSetting());
        autoModeSwitch.setChecked(settings.getAutoModeSetting());
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(soundSwitch.isChecked()){
                    settings.setSound(true);
                } else {
                    settings.setSound(false);
                }
            }
        });
        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(notificationSwitch.isChecked()){
                    settings.setNotifications(true);
                } else {
                    settings.setNotifications(false);
                }
            }
        });
        autoModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(autoModeSwitch.isChecked()){
                    settings.setAutoMode(true);
                } else {
                    settings.setAutoMode(false);
                }
            }
        });
        mPetName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.pet_name_dialog, null);
                final EditText nameEditText = (EditText) mView.findViewById(R.id.nameDialogEditText);

                Button changeName = mView.findViewById(R.id.changeNamebutton);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                changeName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!nameEditText.getText().toString().isEmpty()) {
                            settings.setPetName(nameEditText.getText().toString());
                            mPetName.setText(settings.getPetName());
                            Toast.makeText(SettingsActivity.this,
                                    "Name change successfully.",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(SettingsActivity.this,
                                    "Enter some name",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.cost_dialog, null);
                final EditText moneyEditText = (EditText) mView.findViewById(R.id.costEditText);

                Button changeCost = mView.findViewById(R.id.setCostbutton);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                changeCost.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!moneyEditText.getText().toString().isEmpty()) {
                            settings.setCost(Double.parseDouble(moneyEditText.getText().toString()));
                            mMoney.setText(Double.toString(settings.getCost()));
                            Toast.makeText(SettingsActivity.this,
                                    "Cost set successfully.",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(SettingsActivity.this,
                                    "Enter some price",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
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
