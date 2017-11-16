package com.trivial_pursuit.trivialpursuit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LocalPlayNumP extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public boolean continueMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        continueMusic = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_play_nump);

        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        String[] items = new String[]{"2", "3", "4", "5", "6"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setSelection(0);
        Globs.playerCnt = 2;
        dropdown.setOnItemSelectedListener(this);

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 2:
                // Whatever you want to happen when the first item gets selected
                Globs.playerCnt = 2;
                break;
            case 3:
                Globs.playerCnt = 3;
                // Whatever you want to happen when the second item gets selected
                break;
            case 4:
                Globs.playerCnt = 4;
                // Whatever you want to happen when the thrid item gets selected
                break;
            case 5:
                Globs.playerCnt = 5;
                break;
            case 6:
                Globs.playerCnt = 6;
                break;
        }
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

    public void onNothingSelected(AdapterView<?> parent) {
        Globs.playerCnt = 2;
    }

    protected void startColorSel(View v){
        Spinner mySpinner=(Spinner) findViewById(R.id.spinner1);
        String text = mySpinner.getSelectedItem().toString();
        Globs.playerCnt = Integer.parseInt(text);
        Intent intent = new Intent(this, LocalPlayColorSel.class);
        Globs.sound.playtapsound();
        startActivity(intent);
    }
}
