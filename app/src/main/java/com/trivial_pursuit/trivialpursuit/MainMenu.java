package com.trivial_pursuit.trivialpursuit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.plattysoft.leonids.ParticleSystem;

import java.io.IOException;

public class MainMenu extends AppCompatActivity {
    public boolean continueMusic;
    public int easter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        easter = 0;
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("Settings", 0);
        Globs.vol = Float.parseFloat(settings.getString("volume", "3"));
        Globs.volFX = Float.parseFloat(settings.getString("volumefx", "3"));
        Globs.timerval = Integer.parseInt(settings.getString("timerval", "3"));
        Globs.timeron = Boolean.parseBoolean(settings.getString("timeron", "true"));

        setContentView(R.layout.activity_main_menu);
        Globs.sound = new SoundPlayer(this);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(4000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        Globs.loadedQSet = false;
        Globs.isQuick = false;
        continueMusic = false;

        findViewById(R.id.imageView1).startAnimation(rotateAnimation);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!continueMusic) {
            MusicManager.pause();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        continueMusic = false;
        MusicManager.start(this, MusicManager.MUSIC_MENU);
    }

    @Override
    protected void onDestroy() {
        try {
            if (Globs.loadedQSet) {
                Globs.blueFile.close();
                Globs.pinkFile.close();
                Globs.yellowFile.close();
                Globs.purpleFile.close();
                Globs.greenFile.close();
                Globs.orangeFile.close();
                SharedPreferences mPref = getSharedPreferences(Globs.qsetPath.substring(Globs.qsetPath.indexOf("/") + 1
                        , Globs.qsetPath.length() - 1), 0);
                SharedPreferences.Editor editor = mPref.edit();
                editor.putString("blue", Integer.toString(Globs.blueIdx));
                editor.putString("pink", Integer.toString(Globs.pinkIdx));
                editor.putString("yellow", Integer.toString(Globs.yellowIdx));
                editor.putString("purple", Integer.toString(Globs.purpleIdx));
                editor.putString("green", Integer.toString(Globs.greenIdx));
                editor.putString("orange", Integer.toString(Globs.orangeIdx));
                editor.commit();
            }
        }
        catch(IOException e){

        }
        super.onDestroy();
    }

    public void secretbutton(View view){
        easter++;
        if(easter == 7){
            new ParticleSystem(this, 1024, R.drawable.star_pink, 3000)
                .setSpeedRange(0.2f, 0.5f)
                .oneShot(view, 1024);
        }
    }

    /** Called when the user clicks the "Local Play" button */
    public void startLocal(View view) {
        Globs.isQuick = false;
        Intent intent = new Intent(this, LocalPlayQSet.class);
        startActivity(intent);
        Globs.sound.playtapsound();
    }

    public void startQuick (View view) {
        Globs.isQuick = true;
        Intent intent = new Intent(this, LocalPlayQSet.class);
        startActivity(intent);
        Globs.sound.playtapsound();
    }

    public void startSettings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
        Globs.sound.playtapsound();
    }
}
