package com.example.maryb.feedmeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class petActivity extends AppCompatActivity {
    private final String firstRun = "first_run";
    private SharedPreferences sPref;
    private ArrayList<Integer> iconsList = new ArrayList<>();
    private int currentAnimal;
    private ImageView mImageViewAnimals;
    private ImageButton mNextAnimalButton;
    private ImageButton mPreviousAnimalButton;
    private Button mNextButton;
    private EditText mEditTextName;
    private String petName;
    private Settings settings;
    private static final String SETTINGS_FILE = "settings.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        View v = getWindow().getDecorView();
        v.setBackgroundColor(Color.parseColor("#ebc9ff"));
        settings = Settings.getInstance();
        iconsList.add(R.drawable.cat);
        iconsList.add(R.drawable.dog);
        iconsList.add(R.drawable.rabbit);
        iconsList.add(R.drawable.hamster);
        iconsList.add(R.drawable.ferret);
        currentAnimal = 0;
        mImageViewAnimals = findViewById(R.id.listImage);
        mImageViewAnimals.setImageResource(iconsList.get(currentAnimal));
        mEditTextName = findViewById(R.id.petNameEditText);
        mNextAnimalButton = findViewById(R.id.nextAnimalButton);
        mNextAnimalButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if((iconsList.size()-1) == currentAnimal){
                    currentAnimal = 0;
                }
                else currentAnimal++;
                mImageViewAnimals.setImageResource(iconsList.get(currentAnimal));
            }
        });
        mPreviousAnimalButton = findViewById(R.id.prevAnimalButton);
        mPreviousAnimalButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(currentAnimal == 0){
                    currentAnimal = iconsList.size()-1;
                }
                else currentAnimal--;
                mImageViewAnimals.setImageResource(iconsList.get(currentAnimal));
            }
        });
        mNextButton = findViewById(R.id.nextButton);
        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sPref = getSharedPreferences(firstRun, MODE_PRIVATE);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putString(firstRun, "false");
                ed.apply();

                petName = mEditTextName.getText().toString();
                Intent nextActivity = new Intent(petActivity.this, FeedActivity.class);
                settings.setPetImageID(iconsList.get(currentAnimal));
                settings.setPetName(petName);
                saveSettings(settings);
                //nextActivity.putExtra("ANIMAL", iconsList.get(currentAnimal).toString());
                //nextActivity.putExtra("ANIMAL_NAME", petName);
                startActivity(nextActivity);
            }
        });
    }
    public void saveSettings(Settings settings) {
        FileOutputStream fos = null;
        String config = settings.getPetName()+" "+settings.getPetImageID()+ " " +settings.getCost()+
                " "+settings.getSoundSetting() + " " + settings.getNotificationSetting();
        try {
            fos = openFileOutput(SETTINGS_FILE, MODE_PRIVATE);
            fos.write(config.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
    @Override
    public void onBackPressed() {}
}
