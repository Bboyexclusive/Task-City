/*
 * Copyright (c) 2020. Created by GNASHER YT and GNCODES.TK
 * Get more Codes from gncodes.tk
 */

package com.bboyexclusive.earns;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.startapp.sdk.adsbase.AutoInterstitialPreferences;
import com.startapp.sdk.adsbase.StartAppAd;


public class Instructions extends AppCompatActivity {
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        }
        ImageView imageView = findViewById(R.id.imageView14);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        viewFlipper = findViewById(R.id.viewFlipper);
        final Handler handler = new Handler();
        final int delay = 100; //milliseconds
        handler.postDelayed(new Runnable(){
            public void run(){
                if (viewFlipper.getDisplayedChild() == 4){
                    Button button = findViewById(R.id.play4);
                    button.setText("CLOSE INSTRUCTIONS");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Instructions.this, ChoiceSelection.class);
                            startActivity(intent);
                            StartAppAd.setAutoInterstitialPreferences(
                                    new AutoInterstitialPreferences()
                                            .setSecondsBetweenAds(60)
                            );
                        }
                    });
                }
                handler.postDelayed(this, delay);
            }
        }, delay);}
    public void nextView(View v) {
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        viewFlipper.showNext();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
}