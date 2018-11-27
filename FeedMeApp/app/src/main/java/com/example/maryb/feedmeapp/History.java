package com.example.maryb.feedmeapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class History {
    private static History instance;
    private ArrayList<HistoryNote> historyList;
    private History(){
        historyList = new ArrayList<>();
    }
    public static History getInstance(){
        if(instance == null){
            instance = new History();
        }
        return instance;
    }
    //TODO история за неделю не работает правильно
    public void setHistoryList(ArrayList<HistoryNote> list) {
        historyList = list;
    }
    public void addNote(HistoryNote note){
        historyList.add(note);
    }
    public ArrayList<HistoryNote> getHistory(){
        return this.historyList;
    }
    public ArrayList<HistoryNote> getCurrentDay(){
        DateFormat df = new SimpleDateFormat("EE,dd.MM.yyyy,HH:mm:ss");
        String currentDate = df.format(Calendar.getInstance().getTime());
        String date = currentDate.substring(3,13);
        ArrayList<HistoryNote> day = new ArrayList<>();
        for (HistoryNote aHistoryList : historyList) {
            if (aHistoryList.getData().equals(date)) {
                day.add(aHistoryList);
            }
        }
        return day;
    }
    public ArrayList<HistoryNote> getCurrentWeek(){
        Calendar c1 = Calendar.getInstance();
        //first day of week
        String[] week = new String[7];
        for (int i = 2; i < 8; i ++){
            c1.set(Calendar.DAY_OF_WEEK,i);
            int year1 = c1.get(Calendar.YEAR);
            int month1 = c1.get(Calendar.MONTH)+1;
            int day1 = c1.get(Calendar.DAY_OF_MONTH);
            week[i-2] = Integer.toString(day1)+"."+Integer.toString(month1)+"."+Integer.toString(year1);
        }
        //last day of week
        c1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        int year7 = c1.get(Calendar.YEAR);
        int month7 = c1.get(Calendar.MONTH)+1;
        int day7 = c1.get(Calendar.DAY_OF_MONTH);
        week[6] = Integer.toString(day7)+"."+Integer.toString(month7)+"."+Integer.toString(year7);
        ArrayList<HistoryNote> weekHist = new ArrayList<>();
        for (int i = 0; i < historyList.size(); i++){
            if (historyList.get(i).getData().equals(week[0])||historyList.get(i).getData().equals(week[1])
            ||historyList.get(i).getData().equals(week[2])||historyList.get(i).getData().equals(week[3])
            ||historyList.get(i).getData().equals(week[4])||historyList.get(i).getData().equals(week[5])||
                    historyList.get(i).getData().equals(week[6]))
            weekHist.add(historyList.get(i));
        }
        return weekHist;
    }
    public Integer findDateIndex(String date){
        for (int j = 0; j < 7; j++){

            for (int i = 0; i < historyList.size(); i++){
                if(historyList.get(i).getData() == date) return i;
            }
        }
        return -1;
    }
    public ArrayList<HistoryNote> getWeekHistory(){
        ArrayList<HistoryNote> week = getCurrentWeek();
        ArrayList<HistoryNote> weekHistory = new ArrayList<>();
        int portion[] = new int[7];

        for (HistoryNote aWeek : week) {
            switch (aWeek.getDayOfWeek()) {
                case "пн":
                    portion[0] += aWeek.getPortion();
                    break;
                case "вт":
                    portion[1] += aWeek.getPortion();
                    break;
                case "ср":
                    portion[2] += aWeek.getPortion();
                    break;
                case "чт":
                    portion[3] += aWeek.getPortion();
                    break;
                case "пт":
                    portion[4] += aWeek.getPortion();
                    break;
                case "сб":
                    portion[5] += aWeek.getPortion();
                    break;
                case "вс":
                    portion[6] += aWeek.getPortion();
                    break;
            }
        }
        weekHistory.add(new HistoryNote("Mon","","",0));
        weekHistory.add(new HistoryNote("Tue","","",0));
        weekHistory.add(new HistoryNote("Wed","","",0));
        weekHistory.add(new HistoryNote("Thu","","",0));
        weekHistory.add(new HistoryNote("Fri","","",0));
        weekHistory.add(new HistoryNote("Sat","","",0));
        weekHistory.add(new HistoryNote("Sun","","",0));
        for (int i = 0; i < weekHistory.size(); i++){
            weekHistory.get(i).setPortion(portion[i]);
        }
        return weekHistory;
    }
    public ArrayList<HistoryNote> getCurrentMonth(){
        Calendar c1 = Calendar.getInstance();
        int month1 = c1.get(Calendar.MONTH)+1;
        String currentMonth;
        ArrayList<HistoryNote> month = new ArrayList<>();
        if(month1 < 10){
            currentMonth = "0"+Integer.toString(month1);
        }
        else currentMonth = Integer.toString(month1);

        for (HistoryNote aHistoryList : historyList) {
            if (aHistoryList.getData().substring(3, 5).equals(currentMonth))
                month.add(aHistoryList);
        }
        return month;
    }
    public ArrayList<HistoryNote> getMonthHistory(){
        int portion[] = new int[4];
        ArrayList<HistoryNote> month = getCurrentMonth();
        ArrayList<HistoryNote> monthHistory = new ArrayList<>();
        for (HistoryNote aMonth : month) {
            Integer day = Integer.parseInt(aMonth.getData().substring(0, 2));
            if (day <= 7) portion[0] += aMonth.getPortion();
            if (day > 7 && day <= 14) portion[1] += aMonth.getPortion();
            if (day > 14 && day <= 21) portion[2] += aMonth.getPortion();
            if (day > 21 && day <= 31) portion[3] += aMonth.getPortion();
        }
        monthHistory.add(new HistoryNote("The first","","",0));
        monthHistory.add(new HistoryNote("The second","","",0));
        monthHistory.add(new HistoryNote("The third","","",0));
        monthHistory.add(new HistoryNote("The forth","","",0));
        for (int i = 0; i < 4; i++){
            monthHistory.get(i).setPortion(portion[i]);
        }
        return  monthHistory;
    }
    public ArrayList<HistoryNote> getCurrentYear(){
        Calendar c1 = Calendar.getInstance();
        int year = c1.get(Calendar.YEAR);
        String currentYear = Integer.toString(year);
        ArrayList<HistoryNote> yearHistory = new ArrayList<>();
        for (int i = 0; i < historyList.size(); i ++) {
            if (historyList.get(i).getData().substring(6).equals(currentYear))
                yearHistory.add(historyList.get(i));
        }
        return yearHistory;

    }
    public ArrayList<HistoryNote> getYearHistory() {
        int portion[] = new int[12];
        ArrayList<HistoryNote> month = getCurrentYear();
        ArrayList<HistoryNote> yearHistory = new ArrayList<>();
        for (HistoryNote aMonth : month) {
            Integer mon = Integer.parseInt(aMonth.getData().substring(3, 5));
            portion[mon - 1] += aMonth.getPortion();
        }

        yearHistory.add(new HistoryNote("Jan", "", "", 0));
        yearHistory.add(new HistoryNote("Feb", "", "", 0));
        yearHistory.add(new HistoryNote("March", "", "", 0));
        yearHistory.add(new HistoryNote("Apr", "", "", 0));
        yearHistory.add(new HistoryNote("May", "", "", 0));
        yearHistory.add(new HistoryNote("Jun", "", "", 0));
        yearHistory.add(new HistoryNote("Jul", "", "", 0));
        yearHistory.add(new HistoryNote("Aug", "", "", 0));
        yearHistory.add(new HistoryNote("Sept", "", "", 0));
        yearHistory.add(new HistoryNote("Oct", "", "", 0));
        yearHistory.add(new HistoryNote("Nov", "", "", 0));
        yearHistory.add(new HistoryNote("Dec", "", "", 0));
        for (int i = 0; i < 12; i++) {
            yearHistory.get(i).setPortion(portion[i]);
        }
        return yearHistory;
    }
}
