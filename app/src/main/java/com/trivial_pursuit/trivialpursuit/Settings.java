package com.trivial_pursuit.trivialpursuit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    public boolean continueMusic;
    //global song on already
    boolean songon = false;
    // soundseekbar
    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;
    SeekBar sb;
    TextView valuetxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        continueMusic = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initControls();
        sb = (SeekBar) findViewById(R.id.seekBar);
        sb.setProgress((int)(Globs.volFX*10));
        if(Globs.volFX == 0){
            ImageView i = (ImageView)findViewById(R.id.vollow2);
            i.setImageResource(R.drawable.volmute);
        }
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onStopTrackingTouch(SeekBar arg0)
            {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0)
            {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
            {
                if(Globs.volFX == 0){
                    ImageView i = (ImageView)findViewById(R.id.vollow2);
                    i.setImageResource(R.drawable.vollow);
                }
                Globs.volFX = (float)(progress/10.0);
                if(Globs.volFX == 0){
                    ImageView i = (ImageView)findViewById(R.id.vollow2);
                    i.setImageResource(R.drawable.volmute);
                }
            }
        });

        sb = (SeekBar) findViewById(R.id.seekBar2);
        sb.setProgress(Globs.timerval * 14);
        valuetxt = (TextView) findViewById(R.id.textView3);
        Switch time = (Switch)findViewById(R.id.switch2);
        time.setChecked(Globs.timeron);
        time.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Globs.timeron = b;
            }
        });
        if(Globs.timerval == 1) {
            valuetxt.setText(String.valueOf(Globs.timerval) + " min");
        }
        else{
            valuetxt.setText(String.valueOf(Globs.timerval) + " mins");
        }
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromuser) {
                //global value for timervalue in minutes
                Globs.timerval = (int)(progress/14) + 1;  //100/14=7
                if(Globs.timerval == 1) {
                    valuetxt.setText(String.valueOf(Globs.timerval) + " min");
                }
                else{
                    valuetxt.setText(String.valueOf(Globs.timerval) + " mins");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            continueMusic = true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void Backbutt (View view) {
        Globs.sound.playtapsound();
        onBackPressed();
    }

    public void Backbutton (View view) {
        Globs.sound.playtapsound();
        SharedPreferences settings = getSharedPreferences("Settings", 0);
        SharedPreferences.Editor setEditor = settings.edit();
        setEditor.putString("volume", Float.toString(Globs.vol));
        setEditor.putString("volumefx", Float.toString(Globs.volFX));
        setEditor.putString("timeron", Boolean.toString(Globs.timeron));
        setEditor.putString("timerval", Integer.toString(Globs.timerval));
        setEditor.commit();
        onBackPressed();
    }

    private void initControls()
    {
        try
        {
            volumeSeekbar = (SeekBar)findViewById(R.id.seekBar1);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setProgress((int)(MusicManager.getMusicVolume()*10));
            if(Globs.vol == 0){
                ImageView i = (ImageView)findViewById(R.id.vollow);
                i.setImageResource(R.drawable.volmute);
            }

            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
                {
                    if(Globs.vol == 0){
                        ImageView i = (ImageView)findViewById(R.id.vollow);
                        i.setImageResource(R.drawable.vollow);
                    }
                    Globs.vol = (float)(progress/10.0);
                    MusicManager.updateVolumeFromPrefs();
                    if(Globs.vol == 0){
                        ImageView i = (ImageView)findViewById(R.id.vollow);
                        i.setImageResource(R.drawable.volmute);
                    }
                }
            });
        }
        catch (Exception e)
        {
        }
    }
}

