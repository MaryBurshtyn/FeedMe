package com.example.maryb.feedmeapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class notificationReceiver extends BroadcastReceiver {
    public notificationReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, intentService.class);
        context.startService(intent1);
    }
}

