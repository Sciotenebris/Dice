package com.rc.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TwoDice extends AppCompatActivity {

    private DiceOutcome diceOutcome;
    private int diceRollSound;
    private SoundPool soundPool;
    private ImageView dice_view, dice_view2;
    private Vibrator vibration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_dice);

        //Initialise SoundPool
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

        //Load sound
        diceRollSound = soundPool.load(this, R.raw.dice_roll, 1);

        //Init vibration
        vibration = (Vibrator) TwoDice.this.getSystemService(Context.VIBRATOR_SERVICE);

        //Init buttons and images
        Button roll_button = findViewById(R.id.rollButton2);
        dice_view = findViewById(R.id.mainDiceImage);
        dice_view2 = findViewById(R.id.diceView3);

        //Set number of dice
        diceOutcome = new DiceOutcome(2);

        //OnClick behaviour
        dice_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoRoll();
            }
        });
        dice_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoRoll();
            }
        });
        roll_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                twoRoll();
            }
        });
    }

    /**
     * Plays the Roll sound effect when called.
     */
    public void rollAudio() {
        soundPool.autoPause();
        soundPool.play(diceRollSound, 1, 1, 0, 0, 1);
    }

    /**
     * Output for dice rolling. Displays image, plays sound, and vibrates.
     */
    public void twoRoll() {

        vibration.vibrate(30);
        diceOutcome.rollDice();
        rollAudio();

        if (diceOutcome.getOutcome1() == 1) {
            dice_view.setImageResource(R.drawable.my_dice1);
        } else if (diceOutcome.getOutcome1() == 2) {
            dice_view.setImageResource(R.drawable.my_dice2);
        } else if (diceOutcome.getOutcome1() == 3) {
            dice_view.setImageResource(R.drawable.my_dice3);
        } else if (diceOutcome.getOutcome1() == 4) {
            dice_view.setImageResource(R.drawable.my_dice4);
        } else if (diceOutcome.getOutcome1() == 5) {
            dice_view.setImageResource(R.drawable.my_dice5);
        } else if (diceOutcome.getOutcome1() == 6) {
            dice_view.setImageResource(R.drawable.my_dice6);
        }
        if (diceOutcome.getOutcome2() == 1) {
            dice_view2.setImageResource(R.drawable.my_dice1);
        } else if (diceOutcome.getOutcome2() == 2) {
            dice_view2.setImageResource(R.drawable.my_dice2);
        } else if (diceOutcome.getOutcome2() == 3) {
            dice_view2.setImageResource(R.drawable.my_dice3);
        } else if (diceOutcome.getOutcome2() == 4) {
            dice_view2.setImageResource(R.drawable.my_dice4);
        } else if (diceOutcome.getOutcome2() == 5) {
            dice_view2.setImageResource(R.drawable.my_dice5);
        } else if (diceOutcome.getOutcome2() == 6) {
            dice_view2.setImageResource(R.drawable.my_dice6);
        }
    }
}