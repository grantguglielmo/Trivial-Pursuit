package com.trivial_pursuit.trivialpursuit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

public class LocalPlayNumP extends AppCompatActivity{
    public boolean continueMusic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        continueMusic = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_play_nump);
        Globs.playerCnt = 2;
        NumberPicker np = (NumberPicker) findViewById(R.id.np);
        np.setMinValue(2);
        np.setMaxValue(6);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                Globs.playerCnt = newVal;
            }
        });
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
        MusicManager.start(this, MusicManager.MUSIC_MENU);
        if(Globs.backToMain)
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

    public void Backbutt (View view) {
        Globs.sound.playtapsound();
        onBackPressed();
    }

    public void startColorSel(View v){
        Intent intent = new Intent(this, LocalPlayColorSel.class);
        Globs.sound.playtapsound();
        startActivity(intent);
    }
}
