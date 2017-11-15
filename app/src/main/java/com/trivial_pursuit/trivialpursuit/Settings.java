package com.trivial_pursuit.trivialpursuit;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class Settings extends AppCompatActivity {


    // soundseekbar
    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;
    //timerseekbar
    SeekBar sb;
    TextView valuetxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);




        //setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();
        //final MediaPlayer mp = MediaPlayer.create(this, R.raw.audio);
        sb = (SeekBar) findViewById(R.id.seekBar2);
        valuetxt = (TextView) findViewById(R.id.textView3);




        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromuser) {
                //global value for timervalue in minutes
            Globs.timerval =1;
             Globs.timerval = (int)(progress/14.28);
                if(Globs.timerval<1){
                    Globs.timerval=1;
                }
            valuetxt.setText(String.valueOf(Globs.timerval));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void Backbutton (View view) {
        finish();
    }

    private void initControls()
    {
        try
        {
            volumeSeekbar = (SeekBar)findViewById(R.id.seekBar1);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));


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
                {   Globs.soundvalue = progress;
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            progress, 0);

                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

