package com.trivial_pursuit.trivialpursuit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class LocalPlayQSet extends AppCompatActivity {
    public boolean continueMusic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        continueMusic = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_play_qset);
    }

    public void onClickM(View v){
        Globs.qsetPath = "Questions/Master/";
        if(Globs.isQuick){
            Intent intent = new Intent(this, Quick.class);
            Globs.sound.playtapsound();
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, LocalPlayNumP.class);
            Globs.sound.playtapsound();
            startActivity(intent);
        }
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
        MusicManager.start(this, MusicManager.MUSIC_MENU, true);
    }

    public void roll(View view){
        Intent intent = new Intent(this, Dice.class);
        startActivity(intent);
    }

    public void Backbutt (View view) {
        Globs.sound.playtapsound();
        onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            continueMusic = true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onClickC(View v){
        Globs.qsetPath = "Questions/Custom/";
        if(Globs.isQuick){
            Intent intent = new Intent(this, Quick.class);
            Globs.sound.playtapsound();
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, LocalPlayNumP.class);
            Globs.sound.playtapsound();
            startActivity(intent);
        }
    }
}
