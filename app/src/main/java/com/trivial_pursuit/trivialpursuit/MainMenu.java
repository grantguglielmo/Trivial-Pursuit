package com.trivial_pursuit.trivialpursuit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import java.io.IOException;

public class MainMenu extends AppCompatActivity {
    private SoundPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(4000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        Globs.loadedQSet = false;
        Globs.isQuick = false;

        findViewById(R.id.imageView1).startAnimation(rotateAnimation);

        sound = new SoundPlayer(this);
        //Globs.bg = new BackgroundSoundService();
        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc);
//        setVolumeControlStream(AudioManager.STREAM_MUSIC);
//        Globs.mp = MediaPlayer.create(this, R.raw.audio);
//        Globs.mp.setLooping(true);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onStop(){
        super.onStop();
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
        //Globs.bg.onDestroy();
        super.onDestroy();
    }

//    @Override
//    public void onBackPressed()
//    {
//        super.onBackPressed();
//    }
//
//    @Override
//    public void onUserLeaveHint(){
//        super.onUserLeaveHint();
//    }

    /** Called when the user clicks the "Local Play" button */
    public void startLocal(View view) {
        Globs.isQuick = false;
        Intent intent = new Intent(this, LocalPlayQSet.class);
        startActivity(intent);
        sound.playtapsound();
    }

    public void startQuick (View view) {
        Globs.isQuick = true;
        Intent intent = new Intent(this, LocalPlayQSet.class);
        startActivity(intent);
        sound.playtapsound();
    }

    public void startSettings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
