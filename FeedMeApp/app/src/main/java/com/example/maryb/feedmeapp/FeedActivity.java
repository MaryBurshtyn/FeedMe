package com.example.maryb.feedmeapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FeedActivity extends AppCompatActivity {
    private static boolean userPressedBackAgain = false;
    private static final String HISTORY_FILE = "history.txt";
    private static final String SETTINGS_FILE = "settings.txt";
    private String saveNote;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button mFeedButton;
    private Button mAddButton;
    private ImageView mImageViewAnimals;
    private TextView mTextView;
    private TextView mLastFoodTimeTextView;
    private TextView mLastFoodDateTextView;
    private MQTTClient client;
    private EditText mPortionEditText;
    private EditText mTimeEditText;
    private int portion;
    public History history;
    private Settings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        //getActionBar().setTitle("Home");

        mLastFoodTimeTextView = findViewById(R.id.LastFoodTime);
        mLastFoodDateTextView = findViewById(R.id.LastFoodDate);
        mAddButton = findViewById(R.id.AddButton);
        mTimeEditText = findViewById(R.id.TimeEditText);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settings.getSoundSetting()){
                    mp.start();
                }
                String time =  mTimeEditText.getText().toString();
                Intent notifyIntent = new Intent(FeedActivity.this,notificationReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast
                        (FeedActivity.this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) FeedActivity.this.getSystemService(Context.ALARM_SERVICE);

                Calendar now = Calendar.getInstance();
                Calendar calendarStart = Calendar.getInstance();
                now.setTimeInMillis(System.currentTimeMillis());
                calendarStart.setTimeInMillis(now.getTimeInMillis());

                calendarStart.set(Calendar.HOUR_OF_DAY,Integer.parseInt(time.substring(0,2)));
                calendarStart.set(Calendar.MINUTE, Integer.parseInt(time.substring(3,4)));
                calendarStart.set(Calendar.SECOND, 0);
                calendarStart.set(Calendar.MILLISECOND, 0);

                if(settings.getNotificationSetting()){
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendarStart.getTimeInMillis(), pendingIntent);
                }

                mTimeEditText.setText("");
               /* Date dat = new Date();
                Calendar cal_alarm = Calendar.getInstance();
                Calendar cal_now = Calendar.getInstance();
                cal_now.setTime(dat);
                cal_alarm.setTime(dat);
                cal_alarm.set(Calendar.HOUR_OF_DAY,Integer.parseInt(time.substring(0,2)));
                cal_alarm.set(Calendar.MINUTE,Integer.parseInt(time.substring(3,4)));
                cal_alarm.set(Calendar.SECOND,0);*/
                //alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            }
        });


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
                        Intent nextActivity2 = new Intent(FeedActivity.this,AnalysisActivity.class);
                        startActivity(nextActivity2);
                        return true;
                    case R.id.settings :
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        Intent nextActivity1 = new Intent(FeedActivity.this,SettingsActivity.class);
                        startActivity(nextActivity1);
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
        loadSettings();
        mImageViewAnimals = findViewById(R.id.animalImageView);
        //String animalId = getIntent().getStringExtra("ANIMAL");
        //int imageId = Integer.parseInt(animalId,10);
        mImageViewAnimals.setImageResource(settings.getPetImageID());

        mTextView = findViewById(R.id.petsNameText);
        //String petName = getIntent().getStringExtra("ANIMAL_NAME");
        mTextView.setText(settings.getPetName());

        history = History.getInstance();
        load();

        mLastFoodTimeTextView.setText(history.getHistory().get(history.getHistory().size()-1).getTime());
        mLastFoodDateTextView.setText(history.getHistory().get(history.getHistory().size()-1).getData());
        mFeedButton = findViewById(R.id.feedButton);
        mFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settings.getSoundSetting()){
                    mp.start();
                }
                if(mPortionEditText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Enter some portion of food.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(isConnected()){
                    portion = Integer.parseInt(mPortionEditText.getText().toString());
                    if(portion >= 6){
                        Toast.makeText(getApplicationContext(), "Large portion.",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(portion >= 1){
                        Toast.makeText(getApplicationContext(), "Pet fed successfully.",
                                Toast.LENGTH_LONG).show();

                        String message = Integer.toString(portion);
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
                else {
                    Toast.makeText(getApplicationContext(), "Can't feed pet. No Internet access.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void save(String note) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(HISTORY_FILE,MODE_APPEND| MODE_PRIVATE);
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
        ArrayList<HistoryNote> list = new ArrayList<>();
        try {
            fis = openFileInput(HISTORY_FILE);
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
    public boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }
    public void loadSettings() {
        FileInputStream fis = null;
        try {
            fis = openFileInput(SETTINGS_FILE);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String text;
            text = br.readLine();
            String[] parts = text.split(" ");
            double cost = Double.parseDouble(parts[2]);
            settings.setCost(cost);
            String petName = parts[0];
            settings.setPetName(petName);
            Integer petImageID = Integer.parseInt(parts[1]);
            settings.setPetImageID(petImageID);
            boolean sound = Boolean.parseBoolean(parts[3]);
            settings.setSound(sound);
            boolean notifications = Boolean.parseBoolean(parts[3]);
            settings.setNotifications(notifications);

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

    public void onBackPressed() {
        if (!userPressedBackAgain) {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            userPressedBackAgain = true;
        } else {
            super.onBackPressed();
        }

        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                userPressedBackAgain = false;
            }
        }.start();

    }
    /*@Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }*/
}
