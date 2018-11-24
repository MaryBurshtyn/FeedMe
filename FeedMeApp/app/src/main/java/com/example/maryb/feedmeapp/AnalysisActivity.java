package com.example.maryb.feedmeapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class AnalysisActivity extends AppCompatActivity {
    LineGraphSeries<DataPoint> series;
    private ImageButton homeButton;
    private Button weekButton;
    private Button monthButton;
    private Button yearButton;
    private History history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        View v = getWindow().getDecorView();
        v.setBackgroundColor(Color.parseColor("#ebc9ff"));
        history = History.getInstance();
        weekButton = findViewById(R.id.weekButton);
        monthButton = findViewById(R.id.monthButton);
        yearButton = findViewById(R.id.yearButton);
        homeButton = findViewById(R.id.homeButtonAnalysis);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity = new Intent(AnalysisActivity.this, FeedActivity.class);
                startActivity(nextActivity);
            }
        });

        weekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<HistoryNote> week = history.getCurrentWeek();
                double y,x;
                x = 0;
                y = 0;
                GraphView graph = (GraphView) findViewById(R.id.graphView);
                series = new LineGraphSeries<>();
                for(int i = 0; i<140; i++) {
                    x = x + 0.1;
                    if (i < 20) y = week.get(0).getPortion();
                    if (i >= 20 && i < 40) y = week.get(1).getPortion();
                    if (i >= 40 && i < 60) y = week.get(2).getPortion();
                    if (i >= 60 && i < 80) y = week.get(3).getPortion();
                    if (i >= 80 && i < 100) y = week.get(4).getPortion();
                    if (i >= 100 && i < 120) y = week.get(5).getPortion();
                    if (i >= 120 && i < 140) y = week.get(6).getPortion();
                    series.appendData(new DataPoint(x, y), true, 140);
                }
                graph.addSeries(series);
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
