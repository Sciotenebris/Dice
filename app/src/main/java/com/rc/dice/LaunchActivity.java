package com.rc.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;


public class LaunchActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private int uiSound;
    private Intent oneDiceIntent, twoDiceIntent;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        //AdMob init
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //SoundPool preparation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        //Init adView
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Load sound
        uiSound = soundPool.load(this, R.raw.ui_click, 1);

        //Intents
        oneDiceIntent = new Intent(getApplicationContext(), OneDice.class);
        twoDiceIntent = new Intent(getApplicationContext(), TwoDice.class);

        //Buttons
        Button one_dice = findViewById(R.id.one_dice_button);
        Button two_dice = findViewById(R.id.two_dice_button);

        //Button behaviour
        one_dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playUIAudio();
                startActivity(oneDiceIntent);

            }
        });
        two_dice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playUIAudio();
                startActivity(twoDiceIntent);
            }
        });
    }



    /**
     * Closes ad if active.
     */
    @Override
    protected void onDestroy() {
        adView.destroy();
        super.onDestroy();
    }

    /**
     * Plays the UI sound effect when called.
     */
    public void playUIAudio() {
        soundPool.autoPause();
        soundPool.play(uiSound, 1, 1, 0, 0, 1);
    }
}