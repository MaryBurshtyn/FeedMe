package com.example.maryb.feedmeapp;

public class HistoryNote {
    private String dayOfWeek;
    private String data;
    private String time;
    private int portion;
    public HistoryNote(String dayOfWeek,String data, String time,int portion){
        this.dayOfWeek = dayOfWeek;
        this.data = data;
        this.time = time;
        this.portion = portion;
    }
    public HistoryNote(){
        this.portion = 0;
    }
    public void setData(String data){
        this.data = data;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setDayOfWeek(String dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }
    public void setPortion(int portion){
        this.portion = portion;
    }
    public void setNote(String dayOfWeek,String data, String time,int portion){
        this.setData(data);
        this.setTime(time);
        this.setDayOfWeek(dayOfWeek);
        this.setPortion(portion);
    }
    public String getData(){
        return this.data;
    }
    public String getTime(){
        return this.time;
    }
    public String getDayOfWeek(){
        return this.dayOfWeek;
    }
    public int getPortion(){
        return this.portion;
    }
}
