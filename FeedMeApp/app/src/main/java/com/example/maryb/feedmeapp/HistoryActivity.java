package com.example.maryb.feedmeapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
//TODO table scroll for day
public class HistoryActivity extends AppCompatActivity {
    private TableLayout tableLayout;
    private History history;
    private TextView column1;
    private TextView column2;
    private Button mHomeButton;
    private Button mDayButton;
    private Button mWeekButton;
    private Button mMonthButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        View v = getWindow().getDecorView();
        v.setBackgroundColor(Color.parseColor("#ebc9ff"));
        mHomeButton = findViewById(R.id.HomeButton);
        mDayButton = findViewById(R.id.DayButton);
        mWeekButton = findViewById(R.id.WeekButton);
        mMonthButton = findViewById(R.id.MonthButton);
        history = History.getInstance();
        tableLayout = findViewById(R.id.table_Layout);

        column1 = findViewById(R.id.r1c1);
        column1.setTextSize(18);
        column2 = findViewById(R.id.r1c2);
        column2.setTextSize(18);

        showDayHistory();
        mDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDayHistory();
            }
        });
        mWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeekHistory();
            }
        });
        mMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMonthHistory();
            }
        });
        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(HistoryActivity.this, FeedActivity.class);
                startActivity(nextActivity);
            }
        });
    }

    private void showDayHistory(){
        int count = tableLayout.getChildCount();
        for (int i = 1; i < count; i++) {
            View child = tableLayout.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }
        column1.setText("Time");
        column2.setText("Portion");

        //ArrayList<HistoryNote> day = history.getHistory();
        ArrayList<HistoryNote> day = history.getCurrentDay();
        for(int i = 0; i < day.size(); i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView label_date = new TextView(this);
            label_date.setText(day.get(i).getTime());
            //label_date.setText("11:20");
            label_date.setPadding(5, 5, 5, 5);
            row.addView(label_date);// add the column to the table row here

            TextView portion = new TextView(this);
            portion.setText(Integer.toString(day.get(i).getPortion()));
            //portion.setText("3");
            portion.setPadding(5, 5, 5, 5);
            row.addView(portion);
            tableLayout.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
    private void showWeekHistory(){
        int count = tableLayout.getChildCount();
        for (int i = 1; i < count; i++) {
            View child = tableLayout.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }
        column1.setText("Day");
        column2.setText("Portion");
        ArrayList<HistoryNote> week = history.getWeekHistory();

        for(int i = 0; i < week.size(); i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView label_date = new TextView(this);
            label_date.setText(week.get(i).getDayOfWeek());
            //label_date.setText("11:20");
            label_date.setPadding(5, 5, 5, 5);
            row.addView(label_date);// add the column to the table row here

            TextView portion = new TextView(this);
            portion.setText(Integer.toString(week.get(i).getPortion()));
            //portion.setText("3");
            portion.setPadding(5, 5, 5, 5);
            row.addView(portion);
            tableLayout.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
    private void showMonthHistory(){
        int count = tableLayout.getChildCount();
        for (int i = 1; i < count; i++) {
            View child = tableLayout.getChildAt(i);
            if (child instanceof TableRow) ((ViewGroup) child).removeAllViews();
        }
        column1.setText("Week");
        column2.setText("Portion");
        ArrayList<HistoryNote> month = history.getMonthHistory();

        for(int i = 0; i < month.size(); i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView label_date = new TextView(this);
            label_date.setText(month.get(i).getDayOfWeek());
            //label_date.setText("11:20");
            label_date.setPadding(5, 5, 5, 5);
            row.addView(label_date);// add the column to the table row here

            TextView portion = new TextView(this);
            portion.setText(Integer.toString(month.get(i).getPortion()));
            //portion.setText("3");
            portion.setPadding(5, 5, 5, 5);
            row.addView(portion);
            tableLayout.addView(row, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }
}
