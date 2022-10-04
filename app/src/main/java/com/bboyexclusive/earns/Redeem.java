/*
 * Copyright (c) 2020. Created by GNASHER YT and GNCODES.TK
 * Get more Codes from gncodes.tk
 */

package com.bboyexclusive.earns;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.startapp.sdk.adsbase.AutoInterstitialPreferences;
import com.startapp.sdk.adsbase.StartAppAd;

public class Redeem extends AppCompatActivity {
    private TextView coins2;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private int usercoin;
    private  float x;
    private Integer integer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        if(dpHeight>700) {
            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }

        ImageView imageView = findViewById(R.id.imageView4);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        final EditText coinedittext = findViewById(R.id.payTmmobile);
        coinedittext.setTextSize(100);
        coinedittext.setMaxEms(10);
        final Handler handler2 = new Handler();
        final int delay2 = 10;
        handler2.postDelayed(new Runnable(){
            public void run(){
                if (coinedittext.length()< 5){
                    coinedittext.setTextSize(100); }
                if (coinedittext.length()== 5){
                    coinedittext.setTextSize(90); }
                if (coinedittext.length()== 6){
                    coinedittext.setTextSize(80); }
                if (coinedittext.length()== 7){
                    coinedittext.setTextSize(70);}
                if (coinedittext.length()== 8){
                    coinedittext.setTextSize(60);}
                if (coinedittext.length()== 10){
                    coinedittext.setTextSize(50); }
                if (coinedittext.length() == 0){
                    coinedittext.setText("0"); }
                handler2.postDelayed(this, delay2);
            }
        }, delay2);
        coinedittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v == coinedittext) {
                    if (hasFocus) {
                        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relativecoinbalance);
                        relativeLayout.getLayoutParams().height = 200;
                        relativeLayout.getLayoutParams().width = 970;
                        relativeLayout.setBackgroundResource(R.drawable.backreedemkeyboard);

                        TextView textView = findViewById(R.id.textView);
                        textView.setPadding(0,47,0,0);
                        TextView textView2 = findViewById(R.id.textViewCoins);
                        textView2.setTextSize(24);
                        textView2.setPadding(520,-15,10,0);
                        TextView textView1 = findViewById(R.id.Checkout);
                        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) textView1.getLayoutParams();
                        params2.setMargins(0,3,0,0);
                        textView1.setLayoutParams(params2);
                        TextView textView3 = findViewById(R.id.textView6);
                        RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) textView3.getLayoutParams();
                        params3.setMargins(0,5,0,0);
                        textView3.setLayoutParams(params3);
                        RelativeLayout relativeLayout2 = (RelativeLayout) findViewById(R.id.relative);
                        relativeLayout2.getLayoutParams().height = 650;
                        ImageView imageView = findViewById(R.id.imageView5);
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                        params.width = 150;
                        params.height = 150;
                        params.setMarginStart(27);
                        imageView.setPadding(35,35,35,35);
                        imageView.setLayoutParams(params);
                        ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(coinedittext, InputMethodManager.SHOW_FORCED);
                    } else {
                        ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(coinedittext.getWindowToken(), 0);
                    }}    }
        });

        coins2 = (TextView) findViewById(R.id.textViewCoins);
        final TextView calcmoney = (TextView) findViewById(R.id.textView6);
        final Handler handler = new Handler();
        final  int delay = 1000; //milliseconds
        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user1 =  mAuth.getCurrentUser();
        String userId = user1.getUid();
        mRef =  database.getReference().child("Users").child(userId);
        mRef.child("RedeemCoins").removeValue();
        mRef.child("RedeemUSD").removeValue();
        mRef.child("Coins").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usercoin = Integer.parseInt(dataSnapshot.getValue(String.class));
                coins2.setText(String.valueOf(usercoin));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
                final EditText et = (EditText) findViewById(R.id.payTmmobile);
        et.setText("0");
        handler.postDelayed(new Runnable(){
            public void run(){
                if (et.length()==0){}else {
                  integer = Integer.valueOf(et.getText().toString());
                   x = (float) (0.0001 * integer);
                    calcmoney.setText("It is " + x +"USD");
                    FirebaseDatabase database =  FirebaseDatabase.getInstance();
                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser user1 =  mAuth.getCurrentUser();
                    String userId = user1.getUid();
                    mRef =  database.getReference().child("Users").child(userId);
                                    }
                handler.postDelayed(this, delay);
            }
        }, delay);

        Button button = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (integer < usercoin && integer>10){
                    mRef.child("RedeemUSD").setValue(String.valueOf(x));
                    mRef.child("RedeemCoins").setValue(String.valueOf(integer));
                    Intent openPayTm = new Intent(getApplicationContext(), RedeemPayTm.class);
                    startActivity(openPayTm);
                    StartAppAd.setAutoInterstitialPreferences(
                            new AutoInterstitialPreferences()
                                    .setSecondsBetweenAds(60)
                    );
                    finish();
                } else {
                    Toast.makeText(getApplication(),"You don't have so many coins",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void payTm(View view) {
        Intent openPayTm = new Intent(getApplicationContext(), RedeemPayTm.class);
        startActivity(openPayTm);
        StartAppAd.showAd(this);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,SettingsActivity.class);
        StartAppAd.onBackPressed(this);
        startActivity(intent);

    }
}
