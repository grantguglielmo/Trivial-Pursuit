package com.trivial_pursuit.trivialpursuit;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

/**
 * Created by vibhuappalaraju on 11/15/17.
 */

public class SoundPlayer {
    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX =2;

    private static SoundPool soundPool;
    private static int tapsound;
    private static int dicesound;
    private static int cardsound;
    private static int dicerollsound;

    public SoundPlayer(Context context){


        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP){
            audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();
            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(SOUND_POOL_MAX).build();

        }
        else {//SoundPool(int maxStreams, int streamType, int srcQuality)
            soundPool = new SoundPool(SOUND_POOL_MAX, AudioManager.STREAM_MUSIC , 0); }


        tapsound = soundPool.load(context,R.raw.beep,1);
        cardsound = soundPool.load(context,R.raw.card,1);
        dicesound = soundPool.load(context,R.raw.dice,1);
        dicerollsound = soundPool.load(context,R.raw.beep2,1);


    }

    public void playtapsound(){
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(tapsound,Globs.volFX,Globs.volFX,1,0,1.0f);
    }
    public void dicesound(){
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(dicesound,Globs.volFX,Globs.volFX,1,0,1.0f);
    }
    public void cardsound(){
        //play(int soundID, float leftVolume, float rightVolume, int priority, int loop, float rate)
        soundPool.play(cardsound,Globs.volFX,Globs.volFX,1,0,1.0f);
    }
    public void beep2sound(){
        soundPool.play(dicerollsound,Globs.volFX,Globs.volFX,1,0,1.0f);


    }

}
