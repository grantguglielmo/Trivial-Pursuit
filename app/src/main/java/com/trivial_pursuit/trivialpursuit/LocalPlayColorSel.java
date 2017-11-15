package com.trivial_pursuit.trivialpursuit;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import static com.trivial_pursuit.trivialpursuit.Globs.Colors.BLUE;
import static com.trivial_pursuit.trivialpursuit.Globs.Colors.GREEN;
import static com.trivial_pursuit.trivialpursuit.Globs.Colors.ORANGE;
import static com.trivial_pursuit.trivialpursuit.Globs.Colors.PINK;
import static com.trivial_pursuit.trivialpursuit.Globs.Colors.PURPLE;
import static com.trivial_pursuit.trivialpursuit.Globs.Colors.YELLOW;

public class LocalPlayColorSel extends AppCompatActivity {
    int player;
    public boolean continueMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        continueMusic = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_play_colorsel);
        Globs.p1 = null;
        Globs.p2 = null;
        Globs.p3 = null;
        Globs.p4 = null;
        Globs.p5 = null;
        Globs.p6 = null;
        player = 1;
        TextView tv1 = (TextView)findViewById(R.id.textColorSel);
        tv1.setText("Player " + player + " Select\n   Your Color");
    }

    public static Drawable convertDrawableToGrayScale(Drawable drawable) {
        if (drawable == null)
            return null;

        Drawable res = drawable.mutate();
        res.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        return res;
    }

    public void blueSel(View v) {
        switch(player){
            case 1:
                Globs.p1 = BLUE;
                break;
            case 2:
                Globs.p2 = BLUE;
                break;
            case 3:
                Globs.p3 = BLUE;
                break;
            case 4:
                Globs.p4 = BLUE;
                break;
            case 5:
                Globs.p5 = BLUE;
                break;
            case 6:
                Globs.p6 = BLUE;
                break;
        }
        if(player == 6 || player == Globs.playerCnt){
            Intent intent = new Intent(this, LocalPlayGame.class);
            startActivity(intent);
        }
        else {
            player++;
            TextView tv1 = (TextView) findViewById(R.id.textColorSel);
            tv1.setText("Player " + player + " Select\n   Your Color");
            ImageButton myBtn = (ImageButton)v.findViewById(R.id.imageButton4);
            myBtn.setEnabled(false);
            Drawable originalIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.bluewheel);
            Drawable icon = convertDrawableToGrayScale(originalIcon);
            myBtn.setImageDrawable(icon);
        }
    }

    public void pinkSel(View v) {
        switch(player){
            case 1:
                Globs.p1 = PINK;
                break;
            case 2:
                Globs.p2 = PINK;
                break;
            case 3:
                Globs.p3 = PINK;
                break;
            case 4:
                Globs.p4 = PINK;
                break;
            case 5:
                Globs.p5 = PINK;
                break;
            case 6:
                Globs.p6 = PINK;
                break;
        }
        if(player == 6 || player == Globs.playerCnt){
            Intent intent = new Intent(this, LocalPlayGame.class);
            startActivity(intent);
        }
        else {
            player++;
            TextView tv1 = (TextView) findViewById(R.id.textColorSel);
            tv1.setText("Player " + player + " Select\n   Your Color");
            ImageButton myBtn = (ImageButton)v.findViewById(R.id.imageButton2);
            myBtn.setEnabled(false);
            Drawable originalIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.pinkwheel);
            Drawable icon = convertDrawableToGrayScale(originalIcon);
            myBtn.setImageDrawable(icon);
        }
    }

    public void yellowSel(View v) {
        switch(player){
            case 1:
                Globs.p1 = YELLOW;
                break;
            case 2:
                Globs.p2 = YELLOW;
                break;
            case 3:
                Globs.p3 = YELLOW;
                break;
            case 4:
                Globs.p4 = YELLOW;
                break;
            case 5:
                Globs.p5 = YELLOW;
                break;
            case 6:
                Globs.p6 = YELLOW;
                break;
        }
        if(player == 6 || player == Globs.playerCnt){
            Intent intent = new Intent(this, LocalPlayGame.class);
            startActivity(intent);
        }
        else {
            player++;
            TextView tv1 = (TextView) findViewById(R.id.textColorSel);
            tv1.setText("Player " + player + " Select\n   Your Color");
            ImageButton myBtn = (ImageButton)v.findViewById(R.id.imageButton3);
            myBtn.setEnabled(false);
            Drawable originalIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.yellowwheel);
            Drawable icon = convertDrawableToGrayScale(originalIcon);
            myBtn.setImageDrawable(icon);
        }
    }

    public void purpleSel(View v) {
        switch(player){
            case 1:
                Globs.p1 = PURPLE;
                break;
            case 2:
                Globs.p2 = PURPLE;
                break;
            case 3:
                Globs.p3 = PURPLE;
                break;
            case 4:
                Globs.p4 = PURPLE;
                break;
            case 5:
                Globs.p5 = PURPLE;
                break;
            case 6:
                Globs.p6 = PURPLE;
                break;
        }
        if(player == 6 || player == Globs.playerCnt){
            Intent intent = new Intent(this, LocalPlayGame.class);
            startActivity(intent);
        }
        else {
            player++;
            TextView tv1 = (TextView) findViewById(R.id.textColorSel);
            tv1.setText("Player " + player + " Select\n   Your Color");
            ImageButton myBtn = (ImageButton)v.findViewById(R.id.imageButton5);
            myBtn.setEnabled(false);
            Drawable originalIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.purplewheel);
            Drawable icon = convertDrawableToGrayScale(originalIcon);
            myBtn.setImageDrawable(icon);
        }
    }

    public void greenSel(View v) {
        switch(player){
            case 1:
                Globs.p1 = GREEN;
                break;
            case 2:
                Globs.p2 = GREEN;
                break;
            case 3:
                Globs.p3 = GREEN;
                break;
            case 4:
                Globs.p4 = GREEN;
                break;
            case 5:
                Globs.p5 = GREEN;
                break;
            case 6:
                Globs.p6 = GREEN;
                break;
        }
        if(player == 6 || player == Globs.playerCnt){
            Intent intent = new Intent(this, LocalPlayGame.class);
            startActivity(intent);
        }
        else {
            player++;
            TextView tv1 = (TextView) findViewById(R.id.textColorSel);
            tv1.setText("Player " + player + " Select\n   Your Color");
            ImageButton myBtn = (ImageButton)v.findViewById(R.id.imageButton6);
            myBtn.setEnabled(false);
            Drawable originalIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.greenwheel);
            Drawable icon = convertDrawableToGrayScale(originalIcon);
            myBtn.setImageDrawable(icon);
        }
    }

    public void orangeSel(View v) {
        switch(player){
            case 1:
                Globs.p1 = ORANGE;
                break;
            case 2:
                Globs.p2 = ORANGE;
                break;
            case 3:
                Globs.p3 = ORANGE;
                break;
            case 4:
                Globs.p4 = ORANGE;
                break;
            case 5:
                Globs.p5 = ORANGE;
                break;
            case 6:
                Globs.p6 = ORANGE;
                break;
        }
        if(player == 6 || player == Globs.playerCnt){
            Intent intent = new Intent(this, LocalPlayGame.class);
            startActivity(intent);
        }
        else {
            player++;
            TextView tv1 = (TextView) findViewById(R.id.textColorSel);
            tv1.setText("Player " + player + " Select\n   Your Color");
            ImageButton myBtn = (ImageButton)v.findViewById(R.id.imageButton7);
            myBtn.setEnabled(false);
            Drawable originalIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.orangewheel);
            Drawable icon = convertDrawableToGrayScale(originalIcon);
            myBtn.setImageDrawable(icon);
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

    @Override
    public void onBackPressed()
    {
        if(player == 1) {
            super.onBackPressed();
        }
        else{
            player--;
            Globs.Colors chk = null;
            switch(player) {
                case 1:
                    chk = Globs.p1;
                    Globs.p1 = null;
                    break;
                case 2:
                    chk = Globs.p2;
                    Globs.p2 = null;
                    break;
                case 3:
                    chk = Globs.p3;
                    Globs.p3 = null;
                    break;
                case 4:
                    chk = Globs.p4;
                    Globs.p4 = null;
                    break;
                case 5:
                    chk = Globs.p5;
                    Globs.p5 = null;
                    break;
                case 6:
                    chk = Globs.p6;
                    Globs.p6 = null;
                    break;
            }
            TextView tv1 = (TextView) findViewById(R.id.textColorSel);
            tv1.setText("Player " + player + " Select\n   Your Color");
            ImageButton myBtn = null;
            Drawable originalIcon = null;
            switch (chk) {
                case BLUE:
                    originalIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.bluewheel);
                    myBtn = (ImageButton) findViewById(R.id.imageButton4);
                    break;
                case PINK:
                    originalIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.pinkwheel);
                    myBtn = (ImageButton) findViewById(R.id.imageButton2);
                    break;
                case YELLOW:
                    originalIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.yellowwheel);
                    myBtn = (ImageButton) findViewById(R.id.imageButton3);
                    break;
                case PURPLE:
                    originalIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.purplewheel);
                    myBtn = (ImageButton) findViewById(R.id.imageButton5);
                    break;
                case GREEN:
                    originalIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.greenwheel);
                    myBtn = (ImageButton) findViewById(R.id.imageButton6);
                    break;
                case ORANGE:
                    originalIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.orangewheel);
                    myBtn = (ImageButton) findViewById(R.id.imageButton7);
                    break;
            }
            myBtn.setEnabled(true);
            myBtn.setImageDrawable(originalIcon);
        }
    }
}
