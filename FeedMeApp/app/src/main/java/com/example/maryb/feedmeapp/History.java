package com.example.maryb.feedmeapp;

import java.util.ArrayList;

public class History {
    private static History instance;
    private ArrayList<HistoryNote> historyList;
    private History(){
        historyList = new ArrayList<HistoryNote>();
    }
    public static History getInstance(){
        if(instance == null){
            instance = new History();
        }
        return instance;
    }
    public void addNote(HistoryNote note){
        historyList.add(note);
    }
    public ArrayList<HistoryNote> getHistory(){
        return this.historyList;
    }
}
