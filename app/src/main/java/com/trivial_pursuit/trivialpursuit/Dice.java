package com.trivial_pursuit.trivialpursuit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Thread.sleep;

public class Dice extends AppCompatActivity {
    public boolean continueMusic;
    public boolean rolled;
    public boolean rollcomp;
    public int Maxrolls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        continueMusic = true;
        rollcomp = false;
        rolled = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
    }

    public void backG(View view){
        if(rolled){
            if(rollcomp){
                onBackPressed();
            }
        }
        else{
            onBackPressed();
        }
    }

    public void Roll (View view) {
        if(!rolled) {
            rolled = true;
            Random r = new Random();
            Maxrolls = r.nextInt(4) + 14;
            Globs.sound.dicesound();
            rollrec(Maxrolls);
        }
        else if(rollcomp){
            onBackPressed();
        }
    }

    public void rollrec(final int rollsLeft){
        Random r = new Random();
        int roll = r.nextInt(6);
        switch (roll) {
            case 0:
                ((ImageView)findViewById(R.id.dice)).setImageResource(R.drawable.dice1);
                break;
            case 1:
                ((ImageView)findViewById(R.id.dice)).setImageResource(R.drawable.dice2);
                break;
            case 2:
                ((ImageView)findViewById(R.id.dice)).setImageResource(R.drawable.dice3);
                break;
            case 3:
                ((ImageView)findViewById(R.id.dice)).setImageResource(R.drawable.dice4);
                break;
            case 4:
                ((ImageView)findViewById(R.id.dice)).setImageResource(R.drawable.dice5);
                break;
            case 5:
                ((ImageView)findViewById(R.id.dice)).setImageResource(R.drawable.dice6);
                break;
        }
        if(rollsLeft - 1 == 0){
            Globs.newRoll = roll + 1;
            rollcomp = true;
            return;
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                rollrec(rollsLeft - 1);
            }
        }, Min(Max((Maxrolls-rollsLeft)*(Maxrolls-rollsLeft), 36), 225));
    }

    public int Min(int x, int y){
        return x < y ? x : y;
    }

    public int Max(int x, int y){
        return x > y ? x : y;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!continueMusic) {
            MusicManager.pause();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        continueMusic = false;
        MusicManager.start(this, MusicManager.MUSIC_GAME);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(!rolled){
                Globs.newRoll = 1;
            }
            continueMusic = true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
