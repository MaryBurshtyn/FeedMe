package com.example.maryb.feedmeapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class AnalysisActivity extends AppCompatActivity {
    LineGraphSeries<DataPoint> series;
    private ImageButton homeButton;
    private int currButton;
    private Button weekButton;
    private Button monthButton;
    private Button yearButton;
    private Button foodButton;
    private Button moneyButton;
    private History history;
    private GraphView graph;
    private Settings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        View v = getWindow().getDecorView();
        v.setBackgroundColor(Color.parseColor("#ebc9ff"));
        currButton = 0;
        settings = Settings.getInstance();
        history = History.getInstance();
        foodButton = findViewById(R.id.foodButton);
        moneyButton = findViewById(R.id.moneyButton);
        weekButton = findViewById(R.id.weekButton);
        monthButton = findViewById(R.id.monthButton);
        yearButton = findViewById(R.id.yearButton);
        homeButton = findViewById(R.id.homeButtonAnalysis);
        graph = (GraphView) findViewById(R.id.graphView);
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
                showWeekGraph(0);
                currButton = 1;
            }
        });
        monthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMonthGraph(0);
                currButton = 2;
            }
        });
        yearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYearGraph(0);
                currButton = 3;
            }
        });
        moneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (currButton){
                    case 0: graph.removeAllSeries(); break;
                    case 1: showWeekGraph(1);break;
                    case 2: showMonthGraph(1);break;
                    case 3: showYearGraph(1);break;
                }

            }
        });
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (currButton){
                    case 0: graph.removeAllSeries(); break;
                    case 1: showWeekGraph(0);break;
                    case 2: showMonthGraph(0);break;
                    case 3: showYearGraph(0);break;
                }

            }
        });
    }
    public void showWeekGraph(int criterion){
        graph.removeAllSeries();
        graph.getViewport().setScalable(false);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(7);
        ArrayList<HistoryNote> week = history.getWeekHistory();
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Mon", "Tue","Wed","Thu","Fri","Sat","Sun"});
        //staticLabelsFormatter.setVerticalLabels(new String[] {"0","Portion"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        BarGraphSeries<DataPoint> series;
        if(criterion == 0){
             series = new BarGraphSeries<>(new DataPoint[] {
                    new DataPoint(1, week.get(0).getPortion()),
                    new DataPoint(2, week.get(1).getPortion()),
                    new DataPoint(3, week.get(2).getPortion()),
                    new DataPoint(4, week.get(3).getPortion()),
                    new DataPoint(5, week.get(4).getPortion()),
                    new DataPoint(6, week.get(5).getPortion()),
                    new DataPoint(7, week.get(6).getPortion())
            });
        }
        else{
             series = new BarGraphSeries<>(new DataPoint[] {
                    new DataPoint(1, week.get(0).getPortion()*0.02*settings.getCost()),
                    new DataPoint(2, week.get(1).getPortion()*0.02*settings.getCost()),
                    new DataPoint(3, week.get(2).getPortion()*0.02*settings.getCost()),
                    new DataPoint(4, week.get(3).getPortion()*0.02*settings.getCost()),
                    new DataPoint(5, week.get(4).getPortion()*0.02*settings.getCost()),
                    new DataPoint(6, week.get(5).getPortion()*0.02*settings.getCost()),
                    new DataPoint(7, week.get(6).getPortion()*0.02*settings.getCost())
            });
        }
        graph.addSeries(series);
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.BLACK);
    }
    public void showMonthGraph(int criterion){
        ArrayList<HistoryNote> week = history.getMonthHistory();
        graph.getViewport().setScalable(false);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(4);
        graph.removeAllSeries();
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"1st", "2nd","3rd","4th"});
        //staticLabelsFormatter.setVerticalLabels(new String[] {"0","Portion"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        BarGraphSeries<DataPoint> series;
        if(criterion == 0){
            series = new BarGraphSeries<>(new DataPoint[] {
                    new DataPoint(1, week.get(0).getPortion()),
                    new DataPoint(2, week.get(1).getPortion()),
                    new DataPoint(3, week.get(2).getPortion()),
                    new DataPoint(4, week.get(3).getPortion())
            });
        }else {
            series = new BarGraphSeries<>(new DataPoint[] {
                    new DataPoint(1, week.get(0).getPortion()*0.02*settings.getCost()),
                    new DataPoint(2, week.get(1).getPortion()*0.02*settings.getCost()),
                    new DataPoint(3, week.get(2).getPortion()*0.02*settings.getCost()),
                    new DataPoint(4, week.get(3).getPortion()*0.02*settings.getCost())
            });
        }

        graph.addSeries(series);
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.BLACK);
    }
    public void showYearGraph(int criterion){
        ArrayList<HistoryNote> year = history.getYearHistory();
        graph.removeAllSeries();
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(12);
        //graph.getViewport().setScalable(true);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Jan", "Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        BarGraphSeries<DataPoint> series;
        if(criterion == 0){
            series = new BarGraphSeries<>(new DataPoint[] {
                    new DataPoint(1, year.get(0).getPortion()),
                    new DataPoint(2, year.get(1).getPortion()),
                    new DataPoint(3, year.get(2).getPortion()),
                    new DataPoint(4, year.get(3).getPortion()),
                    new DataPoint(5, year.get(4).getPortion()),
                    new DataPoint(6, year.get(5).getPortion()),
                    new DataPoint(7, year.get(6).getPortion()),
                    new DataPoint(8, year.get(7).getPortion()),
                    new DataPoint(9, year.get(8).getPortion()),
                    new DataPoint(10, year.get(9).getPortion()),
                    new DataPoint(11, year.get(10).getPortion()),
                    new DataPoint(12, year.get(11).getPortion())
            });
        }else{
            series = new BarGraphSeries<>(new DataPoint[] {
                    new DataPoint(1, year.get(0).getPortion()*0.02*settings.getCost()),
                    new DataPoint(2, year.get(1).getPortion()*0.02*settings.getCost()),
                    new DataPoint(3, year.get(2).getPortion()*0.02*settings.getCost()),
                    new DataPoint(4, year.get(3).getPortion()*0.02*settings.getCost()),
                    new DataPoint(5, year.get(4).getPortion()*0.02*settings.getCost()),
                    new DataPoint(6, year.get(5).getPortion()*0.02*settings.getCost()),
                    new DataPoint(7, year.get(6).getPortion()*0.02*settings.getCost()),
                    new DataPoint(8, year.get(7).getPortion()*0.02*settings.getCost()),
                    new DataPoint(9, year.get(8).getPortion()*0.02*settings.getCost()),
                    new DataPoint(10, year.get(9).getPortion()*0.02*settings.getCost()),
                    new DataPoint(11, year.get(10).getPortion()*0.02*settings.getCost()),
                    new DataPoint(12, year.get(11).getPortion()*0.02*settings.getCost())
            });
        }

        graph.addSeries(series);
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.BLACK);
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
