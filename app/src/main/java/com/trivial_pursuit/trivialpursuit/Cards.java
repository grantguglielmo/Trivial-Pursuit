package com.trivial_pursuit.trivialpursuit;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import static com.trivial_pursuit.trivialpursuit.Quick.Category.BLUE;
import static com.trivial_pursuit.trivialpursuit.Quick.Category.GREEN;
import static com.trivial_pursuit.trivialpursuit.Quick.Category.ORANGE;
import static com.trivial_pursuit.trivialpursuit.Quick.Category.PINK;
import static com.trivial_pursuit.trivialpursuit.Quick.Category.PURPLE;
import static com.trivial_pursuit.trivialpursuit.Quick.Category.YELLOW;

public class Cards extends AppCompatActivity {


    public String Ques;
    public String Ans;
    public boolean continueMusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        continueMusic = true;
        Globs.correctbool=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
        placingcard();
        ImageView i = (ImageView)findViewById(R.id.team);
        i.setImageResource(Globs.teamDraw);

        final View imageButton3 = findViewById(R.id.card1);
        imageButton3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(ansLock){
                    return true;
                }
                TextView tv1 = null;
                tv1 = (TextView) findViewById(R.id.questionTxt2);
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    tv1.setText("Answer: " + Ans);
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    tv1.setText("Answer: " + Ans);
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    tv1.setText(Ques);
                    return true;
                }
                return false;
            }
        });

        TextView t = (TextView)findViewById(R.id.timer);
        t.setText(Globs.timerval + ":00");
        timerOn = Globs.timeron;
        if(timerOn){
            ImageView iv = (ImageView) findViewById(R.id.hour);
            iv.setImageResource(R.drawable.hourrunning);
            final TextView mTextField = (TextView)findViewById(R.id.timer);
            new CountDownTimer(Globs.timerval * 60000, 1000) {

                public void onTick(long millisUntilFinished) {
                    long mins = millisUntilFinished / 1000;
                    long secs = mins % 60;
                    mins = mins / 60;
                    if(secs >= 10)
                        mTextField.setText(mins + ":" + secs);
                    else
                        mTextField.setText(mins + ":0" + secs);
                }

                public void onFinish() {
                    ImageView i = (ImageView) findViewById(R.id.hour);
                    i.setImageResource(R.drawable.hourstart);
                    mTextField.setText("0:00");
                    Globs.correctbool =false;
                    Globs.sound.playtapsound();
                    onBackPressed();
                }

            }.start();
        }
    }

    public boolean timerOn = false;

    public void startTimer (View view) {
        if(timerOn)
            return;
        timerOn = true;
        Globs.sound.playtapsound();
        ImageView i = (ImageView) findViewById(R.id.hour);
        i.setImageResource(R.drawable.hourrunning);
        final TextView mTextField = (TextView)findViewById(R.id.timer);
        new CountDownTimer(Globs.timerval * 60000, 1000) {

            public void onTick(long millisUntilFinished) {
                long mins = millisUntilFinished / 1000;
                long secs = mins % 60;
                mins = mins / 60;
                if(secs >= 10)
                    mTextField.setText(mins + ":" + secs);
                else
                    mTextField.setText(mins + ":0" + secs);
            }

            public void onFinish() {
                ImageView i = (ImageView) findViewById(R.id.hour);
                i.setImageResource(R.drawable.hourstart);
                mTextField.setText("0:00");
                Globs.correctbool =false;
                Globs.sound.playtapsound();
                onBackPressed();
            }

        }.start();
    }

    public void showRule(View v){
        Intent intent = new Intent(this, Rules.class);
        startActivity(intent);
    }

    public boolean lockUsed = false;
    public boolean ansLock = false;

    public void locker (View view) {
        if(ansLock){
            ansLock = false;
            lockUsed = true;
            ImageView i = (ImageView)findViewById(R.id.lock);
            i.setImageResource(R.drawable.unlockused);
            TextView t = (TextView)findViewById(R.id.lockText);
            t.setText("Lock Used");
            t = (TextView)findViewById(R.id.answerHelp2);
            t.setText("Hold Card For Answer");
            return;
        }
        if(lockUsed){
            return;
        }
        ansLock = true;
        ImageView i = (ImageView)findViewById(R.id.lock);
        i.setImageResource(R.drawable.lock);
        TextView t = (TextView)findViewById(R.id.lockText);
        t.setText("Unlock Answer");
        t = (TextView)findViewById(R.id.answerHelp2);
        t.setText("  Answer Locked");
        Globs.sound.playtapsound();

    }

    public void incorrectfunc (View view) {
        Globs.correctbool =false;
        Globs.sound.playtapsound();
        onBackPressed();

    }
    public void correctfunc (View view) {
        Globs.correctbool = true;
        Globs.sound.playtapsound();
        onBackPressed();
    }

    public void placingcard(){

        // probably pass the category as an argument or have a function thatll return the current category
        TextView tv2 = null;
        //just put switch case on the global variable for the category
        Globs.Cat c = Globs.newQ;
        switch(c){
            case BLUE:
                ((ImageView)findViewById(R.id.card1)).setImageResource(R.drawable.trivialcardblue);

                try {
                    if (Globs.blueIdx > Globs.blueMax) {
                        Globs.blueFile.close();
                        AssetManager am = getApplicationContext().getAssets();
                        InputStream is = am.open(Globs.qsetPath + "blue.txt");
                        Globs.blueFile = new BufferedReader(new InputStreamReader(is));
                        Globs.blueIdx = 2;
                    }
                    else{
                        Globs.blueIdx++;
                    }
                    Ques = Globs.blueFile.readLine();
                    Ans = Globs.blueFile.readLine();
                    //Globs.questioncategory = BLUE;
                }
                catch(IOException e){

                }
                tv2 = (TextView) findViewById(R.id.questionTxt2);
                tv2.setText(Ques);


                break;
            case PINK:
                ((ImageView)findViewById(R.id.card1)).setImageResource(R.drawable.trivialcardpink);
                try {
                    if (Globs.pinkIdx > Globs.pinkMax) {
                        Globs.pinkFile.close();
                        AssetManager am = getApplicationContext().getAssets();
                        InputStream is = am.open(Globs.qsetPath + "pink.txt");
                        Globs.pinkFile = new BufferedReader(new InputStreamReader(is));
                        Globs.pinkIdx = 2;
                    }
                    else{
                        Globs.pinkIdx++;
                    }
                    Ques = Globs.pinkFile.readLine();
                    Ans = Globs.pinkFile.readLine();
                    //Globs.questioncategory = PINK;
                }
                catch(IOException e){

                }
                tv2 = (TextView) findViewById(R.id.questionTxt2);
                tv2.setText(Ques);
                break;
            case YELLOW:
                ((ImageView)findViewById(R.id.card1)).setImageResource(R.drawable.trivialcardyellow);
                try {
                    if (Globs.yellowIdx > Globs.yellowMax) {
                        Globs.yellowFile.close();
                        AssetManager am = getApplicationContext().getAssets();
                        InputStream is = am.open(Globs.qsetPath + "yellow.txt");
                        Globs.yellowFile = new BufferedReader(new InputStreamReader(is));
                        Globs.yellowIdx = 2;
                    }
                    else{
                        Globs.yellowIdx++;
                    }
                    Ques = Globs.yellowFile.readLine();
                    Ans = Globs.yellowFile.readLine();
                   // Globs.questioncategory = YELLOW;
                }
                catch(IOException e){

                }
                tv2 = (TextView) findViewById(R.id.questionTxt2);
                tv2.setText(Ques);

                break;
            case PURPLE:
                ((ImageView)findViewById(R.id.card1)).setImageResource(R.drawable.trivialcardpurple);
                try {
                    if (Globs.purpleIdx > Globs.purpleMax) {
                        Globs.purpleFile.close();
                        AssetManager am = getApplicationContext().getAssets();
                        InputStream is = am.open(Globs.qsetPath + "purple.txt");
                        Globs.purpleFile = new BufferedReader(new InputStreamReader(is));
                        Globs.purpleIdx = 2;
                    }
                    else{
                        Globs.purpleIdx++;
                    }
                    Ques = Globs.purpleFile.readLine();
                    Ans = Globs.purpleFile.readLine();
                   // Globs.questioncategory = PURPLE;
                }
                catch(IOException e){

                }
                tv2 = (TextView) findViewById(R.id.questionTxt2);
                tv2.setText(Ques);

                break;
            case GREEN:
                ((ImageView)findViewById(R.id.card1)).setImageResource(R.drawable.trivialcardgreen);
                try {
                    if (Globs.greenIdx > Globs.greenMax) {
                        Globs.greenFile.close();
                        AssetManager am = getApplicationContext().getAssets();
                        InputStream is = am.open(Globs.qsetPath + "green.txt");
                        Globs.greenFile = new BufferedReader(new InputStreamReader(is));
                        Globs.greenIdx = 2;
                    }
                    else{
                        Globs.greenIdx++;
                    }
                    Ques = Globs.greenFile.readLine();
                    Ans = Globs.greenFile.readLine();
                   // Globs.questioncategory = GREEN;
                }
                catch(IOException e){

                }
                tv2 = (TextView) findViewById(R.id.questionTxt2);
                tv2.setText(Ques);



                break;
            case ORANGE:
                ((ImageView)findViewById(R.id.card1)).setImageResource(R.drawable.trivialcardorange);
                try {
                    if (Globs.orangeIdx > Globs.orangeMax) {
                        Globs.orangeFile.close();
                        AssetManager am = getApplicationContext().getAssets();
                        InputStream is = am.open(Globs.qsetPath + "orange.txt");
                        Globs.orangeFile = new BufferedReader(new InputStreamReader(is));
                        Globs.orangeIdx = 2;
                    }
                    else{
                        Globs.orangeIdx++;
                    }
                    Ques = Globs.orangeFile.readLine();
                    Ans = Globs.orangeFile.readLine();
                    //Globs.questioncategory = ORANGE;
                }
                catch(IOException e){

                }
                tv2 = (TextView) findViewById(R.id.questionTxt2);
                tv2.setText(Ques);

                break;


        }



    }


    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
        if (!continueMusic) {
            MusicManager.pause();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        continueMusic = false;
        MusicManager.start(this, MusicManager.MUSIC_GAME, true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //replaces the default 'Back' button action
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            Globs.killGame = true;
            continueMusic = true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
