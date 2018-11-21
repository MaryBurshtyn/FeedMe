package com.example.maryb.feedmeapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FeedActivity extends AppCompatActivity {
    private static final String FILE_NAME = "history.txt";
    private String saveNote;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button mFeedButton;
    private ImageView mImageViewAnimals;
    private TextView mTextView;
    private MQTTClient client;
    private EditText mPortionEditText;
    private int portion;
    private String currentDate;
    public History history;
    private Settings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //System.out.println("In feed activity on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        //getActionBar().setTitle("Home");

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.history :
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent nextActivity = new Intent(FeedActivity.this,HistoryActivity.class);
                        startActivity(nextActivity);
                        return true;
                    case R.id.Analysis :
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.settings :
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                }

                return false;
            }
        });

        View v = getWindow().getDecorView();
        v.setBackgroundColor(Color.parseColor("#ebc9ff"));

        client = new MQTTClient(getBaseContext());
        client.connect();

        mPortionEditText = findViewById(R.id.portionEditText);

        settings = Settings.getInstance();
        mImageViewAnimals = findViewById(R.id.animalImageView);
        //String animalId = getIntent().getStringExtra("ANIMAL");
        //int imageId = Integer.parseInt(animalId,10);
        mImageViewAnimals.setImageResource(settings.getPetImageID());

        mTextView = findViewById(R.id.petsNameText);
        //String petName = getIntent().getStringExtra("ANIMAL_NAME");
        mTextView.setText(settings.getPetName());

        history = History.getInstance();
        load();

        mFeedButton = findViewById(R.id.feedButton);
        mFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO if null string
                portion = Integer.parseInt(mPortionEditText.getText().toString());
                if(portion >= 1){
                    Toast.makeText(getApplicationContext(), "Pet fed successfully.",
                            Toast.LENGTH_LONG).show();
                    String message = "feed(" + portion + ")";
                    client.publishMessage(message);
                    DateFormat df = new SimpleDateFormat("EE,dd.MM.yyyy,HH:mm:ss ");
                    String currentDate = df.format(Calendar.getInstance().getTime());
                    String date = currentDate.substring(3,13);
                    String time = currentDate.substring(14,19);
                    String dayOfWeek = currentDate.substring(0,2);
                    HistoryNote note = new HistoryNote(dayOfWeek,date, time, portion);
                    history.addNote(note);
                    saveNote = dayOfWeek + " " + date + " " + time + " " + Integer.toString(portion)+"\n";
                    save(saveNote);



                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter portion more than zero.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void save(String note) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME,MODE_APPEND| MODE_PRIVATE);
            fos.write(note.getBytes());
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
    public void load() {
        FileInputStream fis = null;
        ArrayList<HistoryNote> list = new ArrayList<HistoryNote>();
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;

            while ((text = br.readLine()) != null) {
                list.add(new HistoryNote(text.substring(0,2),text.substring(3,13),text.substring(14,19),Integer.parseInt(text.substring(20,21))));
            }
            history.setHistoryList(list);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
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
