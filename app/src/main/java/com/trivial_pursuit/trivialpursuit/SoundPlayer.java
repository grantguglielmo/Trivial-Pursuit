package com.trivial_pursuit.trivialpursuit;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

/**
 * Created by vibhuappalaraju on 11/14/17.
 */

public class SoundPlayer {

    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX =2;



    private static SoundPool soundPool;
    private static int tapsound;
    private static int rolldicesound;

    public SoundPlayer(Context context){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){

            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM) // chang heress
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build();

            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(SOUND_POOL_MAX).build();
        }
        else{
            soundPool =  new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_ALARM,0);

        }

       // SoundPool (int maxStreams,int streamType,int srcQuality)


        tapsound = soundPool.load(context,R.raw.slop,1);
        rolldicesound = soundPool.load(context,R.raw.slop,1);

    }

    public void playtapsound(){

        //play(int soundID, float leftVolume, floar rightVolume, int priority, int loop, float rate)
        soundPool.play(tapsound,1.0f,1.0f,1,0,1.0f);
    }

    public void playrolldicesound(){

        //play(int soundID, float leftVolume, floar rightVolume, int priority, int loop, float rate)
        soundPool.play(rolldicesound,1.0f,1.0f,1,0,1.0f);
    }
}

