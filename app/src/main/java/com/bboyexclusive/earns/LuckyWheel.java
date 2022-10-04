/*
 * Copyright (c) 2020. Created by GNASHER YT and GNCODES.TK
 * Get more Codes from gncodes.tk
 */

package com.bboyexclusive.earns;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import com.startapp.sdk.adsbase.AutoInterstitialPreferences;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import rubikstudio.library.LuckyWheelView;
import rubikstudio.library.model.LuckyItem;

public class LuckyWheel extends AppCompatActivity {

    private Calendar calendar;
    private int weekday;
    private String todayString;
    List<LuckyItem> data = new ArrayList<>();
    private int coin;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "203741003", true);
        final Handler handler = new Handler();
         Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ConnectivityManager manager = (ConnectivityManager) getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
                if (null != activeNetwork) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) { }
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) { }
                } else {

                    Intent intent = new Intent(LuckyWheel.this, NoInternetActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    StartAppAd.setAutoInterstitialPreferences(
                            new AutoInterstitialPreferences()
                                    .setSecondsBetweenAds(60)
                    );
                    finish();

                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 1000);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
        setContentView(R.layout.activity_lucky_wheel);


        MobileAds.initialize(this, getString(R.string.admob_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.admob_insterestitial));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {}
            @Override
            public void onAdFailedToLoad(int errorCode) {}
            @Override
            public void onAdOpened() {}
            @Override
            public void onAdClicked() {}
            @Override
            public void onAdLeftApplication() {}
            @Override
            public void onAdClosed() {}
        });

        ImageView imageView = findViewById(R.id.imageView11);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        final SharedPreferences coins = getSharedPreferences("Rewards", MODE_PRIVATE);
        final LuckyWheelView luckyWheelView = (LuckyWheelView) findViewById(R.id.luckyWheel);
        findViewById(R.id.play).setEnabled(true);
        findViewById(R.id.play).setAlpha(1f);
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        weekday = calendar.get(Calendar.DAY_OF_WEEK);
        todayString = year + "" + month + "" + day;
        final SharedPreferences spinChecks = getSharedPreferences("SPINCHECK", 0);
        final boolean currentDay = spinChecks.getBoolean(todayString, false);

        LuckyItem luckyItem1 = new LuckyItem();
        luckyItem1.text = "0";
        luckyItem1.color = Color.parseColor("#8574F1");
        data.add(luckyItem1);

        LuckyItem luckyItem2 = new LuckyItem();
        luckyItem2.text = "20";
        luckyItem2.color = Color.parseColor("#8E84FF");
        data.add(luckyItem2);

        LuckyItem luckyItem3 = new LuckyItem();
        luckyItem3.text = "40";
        luckyItem3.color = Color.parseColor("#752BEF");
        data.add(luckyItem3);

        LuckyItem luckyItem4 = new LuckyItem();
        luckyItem4.text = "60";
        luckyItem4.color = ContextCompat.getColor(getApplicationContext(), R.color.Spinwell140);
        data.add(luckyItem4);

        LuckyItem luckyItem5 = new LuckyItem();
        luckyItem5.text = "80";
        luckyItem5.color = Color.parseColor("#8574F1");
        data.add(luckyItem5);

        LuckyItem luckyItem6 = new LuckyItem();
        luckyItem6.text = "100";
        luckyItem6.color = Color.parseColor("#8E84FF");
        data.add(luckyItem6);

        LuckyItem luckyItem7 = new LuckyItem();
        luckyItem7.text = "120";
        luckyItem7.color = Color.parseColor("#752BEF");
        data.add(luckyItem7);

        LuckyItem luckyItem8 = new LuckyItem();
        luckyItem8.text = "140";
        luckyItem8.color = ContextCompat.getColor(getApplicationContext(), R.color.Spinwell140);
        data.add(luckyItem8);

        luckyWheelView.setData(data);
        luckyWheelView.setRound(getRandomRound());

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    int index = getRandomIndex();
                    luckyWheelView.startLuckyWheelWithTargetIndex(index);
                    SharedPreferences.Editor spins = spinChecks.edit();
                    spins.putBoolean(todayString, true);
                    spins.apply();
                    findViewById(R.id.play).setEnabled(false);
                    findViewById(R.id.play).setAlpha(.5f);
            }
        });
        luckyWheelView.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {
                if (index ==1 ){
                     coin = 0;
                } if (index ==2 ){
                    coin = 20;
                } if (index ==3 ){
                    coin = 40;
                } if (index ==4 ){
                    coin = 60;
                } if (index ==5){
                    coin = 80;
                } if (index ==6 ){
                    coin = 100;
                } if (index ==7 ){
                    coin = 120;
                } if (index ==8 ){
                    coin = 140; }

                Toast.makeText(getApplicationContext(), String.valueOf("+ " + coin +" Coins"), Toast.LENGTH_SHORT).show();
                int coinCount = Integer.parseInt(coins.getString("Coins", "0"));
                coinCount = coinCount + (coin);
                SharedPreferences.Editor coinsEdit = coins.edit();
                coinsEdit.putString("Coins", String.valueOf(coinCount));
                coinsEdit.apply();
                findViewById(R.id.play).setEnabled(true);
                findViewById(R.id.play).setAlpha(1f);

                    if (mInterstitialAd.isLoaded()) {
                         mInterstitialAd.show();
                        StartAppAd.showAd(getApplicationContext());

                } else {
                        StartAppAd.showAd(getApplicationContext());
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
               }
            }
        });
    }
    private int getRandomIndex() {
        int[] ind = new int[] {1,2,3,4,5,6,7,8};
        int rand = new Random().nextInt(ind.length);
        return ind[rand];
    }

    private int getRandomRound() {
        Random rand = new Random();
        return rand.nextInt(10) + 15;
    }

    @Override
    public void onBackPressed() {
       Intent intent = new Intent(this,ChoiceSelection.class);
        StartAppAd.onBackPressed(this);
       startActivity(intent);
    }


}
