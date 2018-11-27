package com.example.maryb.feedmeapp;

import java.util.ArrayList;

public class Settings {
    //TODO Add money
    //TODO add sounds
    //TODO add notifications
    //TODO save settings
    private static Settings instance;
    private double cost;
    private String petName;
    private Integer petImageID;
    private String kindOfAnimal;
    private boolean sound;
    private boolean notifications;
    private boolean autoMode;
    private Settings(){
        sound = true;
        notifications = true;
        autoMode = false;
        cost = 0;
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
        switch (id){
            case R.drawable.cat: kindOfAnimal = "Cat";
                break;
            case R.drawable.dog: kindOfAnimal = "Dog";
                break;
            case R.drawable.ferret: kindOfAnimal = "Ferret";
                break;
            case R.drawable.hamster: kindOfAnimal = "Hamster";
                break;
            case R.drawable.rabbit: kindOfAnimal = "Rabbit";
                break;
        }
    }
    public String getPetName(){
       return petName;
    }
    public double getCost(){
        return cost;
    }
    public void setCost(double cost){
        this.cost = cost;
    }
    public Integer getPetImageID(){
        return petImageID;
    }
    public String getKindOfAnimal(){
        return kindOfAnimal;
    }
    public boolean getSoundSetting(){
        return sound;
    }
    public boolean getNotificationSetting(){
        return notifications;
    }
    public boolean getAutoModeSetting(){
        return autoMode;
    }
    public void setSound(boolean sound){
        this.sound = sound;
    }
    public void setNotifications(boolean notifications){
        this.notifications = notifications;
    }
    public void setAutoMode(boolean AutoMode){
        this.autoMode = AutoMode;
    }
}
