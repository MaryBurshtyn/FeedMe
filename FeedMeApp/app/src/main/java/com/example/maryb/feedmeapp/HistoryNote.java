package com.example.maryb.feedmeapp;

public class HistoryNote {
    private String data;
    private String time;
    public HistoryNote(String data, String time){
        this.data = data;
        this.time = time;
    }
    public void setData(String data){
        this.data = data;
    }
    public void setTime(String time){
        this.time = time;
    }
}
