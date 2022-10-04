/*
 * Copyright (c) 2020. Created by GNASHER YT and GNCODES.TK
 * Get more Codes from gncodes.tk
 */

package com.bboyexclusive.earns;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.startapp.sdk.adsbase.StartAppAd;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class RedeemPayTm extends AppCompatActivity {
    private TextView mobileno;
    private SharedPreferences coins,money;
    private String currentCoins,currentMoney;
    private String email;
    private EditText editText;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef,mRefStatus;
    private float usermoney;
    private int usermoneyCoins ,usercoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_pay_tm);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE); }

        ImageView imageView = findViewById(R.id.imageView12);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        coins = getSharedPreferences("Rewards", MODE_PRIVATE);
        money = getSharedPreferences("Cointomoney", MODE_PRIVATE);
        currentMoney = money.getString("Money", "0");

        Button button = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                sendMessage();
                }
            });
        editText = findViewById(R.id.payTmmobile);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v == editText) {
                    if (hasFocus) {
                        TextView textView1 = findViewById(R.id.Checkout);
                        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) textView1.getLayoutParams();
                        params2.setMargins(0,17,0,0);
                        textView1.setLayoutParams(params2);
                        ImageView imageView = findViewById(R.id.imageView13);
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
                        params.setMargins(0,40,0,0);
                        imageView.setLayoutParams(params);
                        ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(editText, InputMethodManager.SHOW_FORCED);
                    } else {
                        ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    }}
            }
        });
    }
    private void sendMessage() {
        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user1 =  mAuth.getCurrentUser();
        String userId = user1.getUid();
        mRef =  database.getReference().child("Users").child(userId);
        mRef.child("RedeemUSD").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usermoney = Float.parseFloat(dataSnapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        mRef.child("RedeemCoins").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usermoneyCoins = Integer.parseInt(dataSnapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        mRef.child("Coins").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 usercoins = Integer.parseInt(dataSnapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        final ProgressDialog dialog = new ProgressDialog(RedeemPayTm.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    email = editText.getText().toString();
                    dialog.dismiss();
                    int result = usercoins - usermoneyCoins;
                    mRef.child("RedeemCoins").removeValue();
                    mRef.child("RedeemUSD").removeValue();

                    FirebaseDatabase database =  FirebaseDatabase.getInstance();
                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser user1 =  mAuth.getCurrentUser();
                    String userId = user1.getUid();
                    mRefStatus =  database.getReference().child("Redeem").push();
                    mRefStatus.child("Status").setValue("Review");
                    mRefStatus.child("Email").setValue(email);
                    mRefStatus.child("MoneyUSD").setValue(String.valueOf(usermoney));

                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Redeem").push();
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", databaseReference.getKey());
                    map.put("email", email);
                    map.put("Redeem", usermoney);
                    Calendar c = Calendar.getInstance();

                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int month = c.get(Calendar.MONTH);
                    int year = c.get(Calendar.YEAR);
                    String date = day + ". " + month + ". " + year;
                    map.put("Date", date);
                    databaseReference.setValue(map);

                    SharedPreferences.Editor coinsEdit = coins.edit();
                    coinsEdit.putString("Coins", String.valueOf(result));
                    coinsEdit.apply();

                    Intent intent = new Intent(RedeemPayTm.this, ChoiceSelection.class);
                    startActivity(intent);
                    StartAppAd.showAd(getApplicationContext());
                    finish();

                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
            }
}, 2500);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Redeem.class);
        StartAppAd.onBackPressed(this);
        startActivity(intent);

    }}
