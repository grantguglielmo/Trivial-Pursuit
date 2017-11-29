package com.trivial_pursuit.trivialpursuit;

import android.content.Context;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

import java.util.Collection;
import java.util.HashMap;

public class MusicManager {
    private static final String TAG = "MusicManager";
    public static final int MUSIC_PREVIOUS = -1;
    public static final int MUSIC_MENU = 0;
    public static final int MUSIC_QUICK = 1;
    public static final int MUSIC_GAME = 2;

    private static HashMap players = new HashMap();
    private static int currentMusic = -1;
    private static int previousMusic = -1;

    public static float getMusicVolume() {
        return Globs.vol;
    }

    public static void start(Context context, int music) {
        start(context, music, false);
    }

    public static void start(Context context, int music, boolean force) {
        if (!force && currentMusic > -1) {
// already playing some music and not forced to change
            return;
        }
        if (music == MUSIC_PREVIOUS) {
            music = previousMusic;
        }
        if (currentMusic == music) {
// already playing this music
            return;
        }
        if (currentMusic != -1) {
            previousMusic = currentMusic;
// playing some other music, pause it and change
            pause();
        }
        currentMusic = music;
        MediaPlayer mp = (MediaPlayer)players.get(music);
        if (mp != null) {
            if (!mp.isPlaying()) {
                mp.start();
            }
        } else {
            if (music == MUSIC_MENU) {
                mp = MediaPlayer.create(context, R.raw.audio0);
            }
            else if(music == MUSIC_QUICK){
                mp = MediaPlayer.create(context, R.raw.audio1);
            }
            else if(music == MUSIC_GAME){
                mp = MediaPlayer.create(context, R.raw.audio2);
            }
            else{
                return;
            }
            players.put(music, mp);
            float volume = getMusicVolume();
            mp.setVolume(volume, volume);
            if (mp == null) {
            } else {
                try {
                    mp.setLooping(true);
                    mp.start();
                } catch (Exception e) {
                }
            }
        }
    }

    public static void pause() {
        Collection mps = players.values();
        for (MediaPlayer p : (Collection<MediaPlayer>)mps) {
            if (p.isPlaying()) {
                p.pause();
            }
        }
// previousMusic should always be something valid
        if (currentMusic != -1) {
            previousMusic = currentMusic;
        }
        currentMusic = -1;
    }

    public static void updateVolumeFromPrefs() {
        try {
            float volume = getMusicVolume();
            Collection mps = players.values();
            for (MediaPlayer p : (Collection<MediaPlayer>)mps) {
                p.setVolume(volume, volume);
            }
        } catch (Exception e) {
        }
    }

    public static void release() {
        Collection mps = players.values();
        for (MediaPlayer mp : (Collection<MediaPlayer>)mps) {
            try {
                if (mp != null) {
                    if (mp.isPlaying()) {
                        mp.stop();
                    }
                    mp.release();
                }
            } catch (Exception e) {
            }
        }
        mps.clear();
        if (currentMusic != -1) {
            previousMusic = currentMusic;
        }
        currentMusic = -1;
    }
}
