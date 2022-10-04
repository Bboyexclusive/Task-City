/*
 * Copyright (c) 2020. Created by GNASHER YT and GNCODES.TK
 * Get more Codes from gncodes.tk
 */

package com.bboyexclusive.earns;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.startapp.sdk.adsbase.StartAppAd;

import java.util.Calendar;

public class DailyCheckins extends AppCompatActivity {

    private Calendar calendar;
    private int weekday;
    private SharedPreferences coins;
    private Button sun, mon, tue, wed, thu, fri, sat;
    private String todayString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_checkins);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        } else {

        }
        ImageView imageView = findViewById(R.id.imageView8);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        coins = getSharedPreferences("Rewards", MODE_PRIVATE);
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        weekday = calendar.get(Calendar.DAY_OF_WEEK);
        todayString = year + "" + month + "" + day;
        sun = (Button) findViewById(R.id.btSun);
        mon = (Button) findViewById(R.id.btMon);
        tue = (Button) findViewById(R.id.btTue);
        wed = (Button) findViewById(R.id.btWed);
        thu = (Button) findViewById(R.id.btThu);
        fri = (Button) findViewById(R.id.btFri);
        sat = (Button) findViewById(R.id.btSat);
        sun.setEnabled(false);
        mon.setEnabled(false);
        tue.setEnabled(false);
        wed.setEnabled(false);
        thu.setEnabled(false);
        fri.setEnabled(false);
        sat.setEnabled(false);
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (weekday==1){
            if (currentDay){
                sun.setBackground(getResources().getDrawable(R.drawable.back11));
            }else {
            sun.setEnabled(true);
            sun.setAlpha(0f);
            sun.setBackground(getResources().getDrawable(R.drawable.back1now));
            sun.setTextColor(Color.WHITE);
        }}
        else if (weekday==2){
            if (currentDay){
                mon.setBackground(getResources().getDrawable(R.drawable.back11));
            }else {
            mon.setEnabled(true);
            mon.setAlpha(1f);
            mon.setBackground(getResources().getDrawable(R.drawable.back1now));
            mon.setTextColor(Color.WHITE);
        }}
        else if (weekday==3){
            if (currentDay){
                tue.setBackground(getResources().getDrawable(R.drawable.back11));
            }else {
            tue.setEnabled(true);
            tue.setAlpha(1f);
            tue.setBackground(getResources().getDrawable(R.drawable.back1now));
            tue.setTextColor(Color.WHITE);
        }}
        else if (weekday==4){
            if (currentDay){
                wed.setBackground(getResources().getDrawable(R.drawable.back11));
            }else {
            wed.setEnabled(true);
            wed.setAlpha(1f);
            wed.setBackground(getResources().getDrawable(R.drawable.back1now));
            wed.setTextColor(Color.WHITE);
        }}
        else if (weekday==5){
            if (currentDay){
                thu.setBackground(getResources().getDrawable(R.drawable.back1));
            }else {
            thu.setEnabled(true);
            thu.setAlpha(1f);
            thu.setBackground(getResources().getDrawable(R.drawable.back1now));
            thu.setTextColor(Color.WHITE);
        }}
        else if (weekday==6){
            if (currentDay){
                fri.setBackground(getResources().getDrawable(R.drawable.back11));
            }else {
            fri.setEnabled(true);
            fri.setAlpha(1f);
            fri.setBackground(getResources().getDrawable(R.drawable.back1now));
            fri.setTextColor(Color.WHITE);
        }}
        else if (weekday==7){
            if (currentDay){
                sat.setBackground(getResources().getDrawable(R.drawable.back11));
            }else {
            sat.setEnabled(true);
            sat.setAlpha(1f);
            sat.setBackground(getResources().getDrawable(R.drawable.back1now));
            sat.setTextColor(Color.WHITE);
        }}
    }
    public void monCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){
            Toast.makeText(this, "10 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 10;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
        } else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }}
    public void tueCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){
            Toast.makeText(this, "10 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 10;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
          } else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }}
    public void wedCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){
            Toast.makeText(this, "20 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 20;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
           } else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }}

    public void thuCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){
            Toast.makeText(this, "20 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 20;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
        } else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }}

    public void friCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){
            Toast.makeText(this, "30 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 30;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
          } else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }}

    public void satCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){
            Toast.makeText(this, "30 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 30;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
         }else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }}

    public void sunCheck(View view) {
        SharedPreferences dailyChecks = getSharedPreferences("DAILYCHECKS", 0);
        boolean currentDay = dailyChecks.getBoolean(todayString, false);
        if (!currentDay){
            Toast.makeText(this, "50 Coins Recieved!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor daily = dailyChecks.edit();
            daily.putBoolean(todayString, true);
            daily.apply();
            int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
            coinCount = coinCount + 50;
            SharedPreferences.Editor coinsEdit = coins.edit();
            coinsEdit.putString("Coins", String.valueOf(coinCount));
            coinsEdit.apply();
        } else {
            Toast.makeText(this, "Reward already recieved", Toast.LENGTH_SHORT).show();
        }}

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,ChoiceSelection.class);
        StartAppAd.onBackPressed(this);
        startActivity(intent);
    }
}
