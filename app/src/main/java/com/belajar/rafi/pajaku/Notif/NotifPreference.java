package com.belajar.rafi.pajaku.Notif;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rafi on 12/22/17.
 */

public class NotifPreference {
    private final String PREF_NAME = "NotifPreference";
    private final String KEY_DATE = "keyDate";
    private final String KEY_TIME = "keyTime";
    private final String KEY_MESSAGE = "KeyMessage";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public NotifPreference(Context context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setKeyDate(String date){
        editor.putString(KEY_DATE, date);
        editor.commit();
    }

    public String getKeyDate(){
        return sharedPreferences.getString(KEY_DATE, null);
    }

    public void setKeyTime(String time){
        editor.putString(KEY_TIME, time);
        editor.commit();
    }

    public  String getKeyTime(){
        return sharedPreferences.getString(KEY_TIME, null);
    }

    public void setKeyMessage(String message){
        editor.putString(KEY_MESSAGE, message);
        editor.commit();
    }

    public String getKeyMessage(){
        return sharedPreferences.getString(KEY_MESSAGE, null);
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }
}
