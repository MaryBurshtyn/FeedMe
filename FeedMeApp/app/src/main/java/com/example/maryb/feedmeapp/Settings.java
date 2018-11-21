package com.example.maryb.feedmeapp;

import java.util.ArrayList;

public class Settings {
    private static Settings instance;
    private String petName;
    private Integer petImageID;
    private Settings(){

    }
    public static Settings getInstance(){
        if(instance == null){
            instance = new Settings();
        }
        return instance;
    }
    public void setPetName(String name){
        petName = name;
    }
    public void setPetImageID(Integer id){
        petImageID = id;
    }
    public String getPetName(){
       return petName;
    }
    public Integer getPetImageID(){
        return petImageID;
    }
}
