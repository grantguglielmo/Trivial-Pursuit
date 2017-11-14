package com.trivial_pursuit.trivialpursuit;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import static com.trivial_pursuit.trivialpursuit.Quick.Category.BLUE;
import static com.trivial_pursuit.trivialpursuit.Quick.Category.RANDOM;

public class Quick extends AppCompatActivity {
    public enum Category{
        RANDOM, BLUE, PINK, YELLOW, PURPLE, GREEN, ORANGE
    }
    public Category cat;

    public int bluei;
    public int pinki;
    public int yellowi;
    public int purplei;
    public int greeni;
    public int orangei;

    public String Ques;
    public String Ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick);

        try {
            if(Globs.loadedQSet){
                Globs.blueFile.close();
                Globs.pinkFile.close();
                Globs.yellowFile.close();
                Globs.purpleFile.close();
                Globs.greenFile.close();
                Globs.orangeFile.close();
                SharedPreferences mPref = getSharedPreferences(Globs.qsetPath.substring(Globs.qsetPath.indexOf("/")+1
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
            Globs.loadedQSet = true;

            AssetManager am = getApplicationContext().getAssets();
            InputStream is = am.open(Globs.qsetPath + "blue.txt");
            Globs.blueFile = new BufferedReader(new InputStreamReader(is));
            is = am.open(Globs.qsetPath + "pink.txt");
            Globs.pinkFile = new BufferedReader(new InputStreamReader(is));
            is = am.open(Globs.qsetPath + "yellow.txt");
            Globs.yellowFile = new BufferedReader(new InputStreamReader(is));
            is = am.open(Globs.qsetPath + "purple.txt");
            Globs.purpleFile = new BufferedReader(new InputStreamReader(is));
            is = am.open(Globs.qsetPath + "green.txt");
            Globs.greenFile = new BufferedReader(new InputStreamReader(is));
            is = am.open(Globs.qsetPath + "orange.txt");
            Globs.orangeFile = new BufferedReader(new InputStreamReader(is));

            int lines = 0;
            String line = Globs.blueFile.readLine();
            while(line != null){
                line = Globs.blueFile.readLine();
                lines++;
            }
            Globs.blueMax = lines/2;
            lines = 0;
            line = Globs.pinkFile.readLine();
            while(line != null){
                line = Globs.pinkFile.readLine();
                lines++;
            }
            Globs.pinkMax = lines/2;
            lines = 0;
            line = Globs.yellowFile.readLine();
            while(line != null){
                line = Globs.yellowFile.readLine();
                lines++;
            }
            Globs.yellowMax = lines/2;
            lines = 0;
            line = Globs.purpleFile.readLine();
            while(line != null){
                line = Globs.purpleFile.readLine();
                lines++;
            }
            Globs.purpleMax = lines/2;
            lines = 0;
            line = Globs.greenFile.readLine();
            while(line != null){
                line = Globs.greenFile.readLine();
                lines++;
            }
            Globs.greenMax = lines/2;
            lines = 0;
            line = Globs.orangeFile.readLine();
            while(line != null){
                line = Globs.orangeFile.readLine();
                lines++;
            }
            Globs.orangeMax = lines/2;
            Globs.blueFile.close();
            Globs.pinkFile.close();
            Globs.yellowFile.close();
            Globs.purpleFile.close();
            Globs.greenFile.close();
            Globs.orangeFile.close();
            is = am.open(Globs.qsetPath + "blue.txt");
            Globs.blueFile = new BufferedReader(new InputStreamReader(is));
            is = am.open(Globs.qsetPath + "pink.txt");
            Globs.pinkFile = new BufferedReader(new InputStreamReader(is));
            is = am.open(Globs.qsetPath + "yellow.txt");
            Globs.yellowFile = new BufferedReader(new InputStreamReader(is));
            is = am.open(Globs.qsetPath + "purple.txt");
            Globs.purpleFile = new BufferedReader(new InputStreamReader(is));
            is = am.open(Globs.qsetPath + "green.txt");
            Globs.greenFile = new BufferedReader(new InputStreamReader(is));
            is = am.open(Globs.qsetPath + "orange.txt");
            Globs.orangeFile = new BufferedReader(new InputStreamReader(is));
        }
        catch(IOException e) {

        }
        Ques = "";
        Ans = "";
        Random r = new Random();
        bluei = r.nextInt(Globs.blueMax) + 1;
        pinki = r.nextInt(Globs.pinkMax) + 1;
        yellowi = r.nextInt(Globs.yellowMax) + 1;
        purplei = r.nextInt(Globs.purpleMax) + 1;
        greeni = r.nextInt(Globs.greenMax) + 1;
        orangei = r.nextInt(Globs.orangeMax) + 1;
        pushToLineNum(bluei, Globs.blueFile);
        pushToLineNum(pinki, Globs.pinkFile);
        pushToLineNum(yellowi, Globs.yellowFile);
        pushToLineNum(purplei, Globs.purpleFile);
        pushToLineNum(greeni, Globs.greenFile);
        pushToLineNum(orangei, Globs.orangeFile);
        cat = RANDOM;
        newCard();
        final View imageButton = findViewById(R.id.card);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                newCard();
            }
        });
    }

    protected void pushToLineNum(int line, BufferedReader f){
        int start = 1;
        while(start != line){
            try {
                f.readLine();
                f.readLine();
                start++;
            }
            catch(IOException e){

            }
        }
    }

    protected void newCard(){
        Random r = new Random();
        TextView tv1 = null;
        switch(cat){
            case RANDOM:
                int catRand = r.nextInt(6);
                switch(catRand){
                    case 0: //BLUE
                        ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardblue);
                        try {
                            if (bluei > Globs.blueMax) {
                                Globs.blueFile.close();
                                AssetManager am = getApplicationContext().getAssets();
                                InputStream is = am.open(Globs.qsetPath + "blue.txt");
                                Globs.blueFile = new BufferedReader(new InputStreamReader(is));
                                bluei = 1;
                            }
                            else{
                                bluei++;
                            }
                            Ques = Globs.blueFile.readLine();
                            Ans = Globs.blueFile.readLine();
                        }
                        catch(IOException e){

                        }
                        tv1 = (TextView) findViewById(R.id.questionTxt);
                        tv1.setText(Ques);
                        break;
                    case 1: //PINK
                        ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardpink);
                        try {
                            if (pinki > Globs.pinkMax) {
                                Globs.pinkFile.close();
                                AssetManager am = getApplicationContext().getAssets();
                                InputStream is = am.open(Globs.qsetPath + "pink.txt");
                                Globs.pinkFile = new BufferedReader(new InputStreamReader(is));
                                pinki = 1;
                            }
                            else{
                                pinki++;
                            }
                            Ques = Globs.pinkFile.readLine();
                            Ans = Globs.pinkFile.readLine();
                        }
                        catch(IOException e){

                        }
                        tv1 = (TextView) findViewById(R.id.questionTxt);
                        tv1.setText(Ques);
                        break;
                    case 2: //YELLOW
                        ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardyellow);
                        try {
                            if (yellowi > Globs.yellowMax) {
                                Globs.yellowFile.close();
                                AssetManager am = getApplicationContext().getAssets();
                                InputStream is = am.open(Globs.qsetPath + "yellow.txt");
                                Globs.yellowFile = new BufferedReader(new InputStreamReader(is));
                                yellowi = 1;
                            }
                            else{
                                yellowi++;
                            }
                            Ques = Globs.yellowFile.readLine();
                            Ans = Globs.yellowFile.readLine();
                        }
                        catch(IOException e){

                        }
                        tv1 = (TextView) findViewById(R.id.questionTxt);
                        tv1.setText(Ques);
                        break;
                    case 3: //PURPLE
                        ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardpurple);
                        try {
                            if (purplei > Globs.purpleMax) {
                                Globs.purpleFile.close();
                                AssetManager am = getApplicationContext().getAssets();
                                InputStream is = am.open(Globs.qsetPath + "purple.txt");
                                Globs.purpleFile = new BufferedReader(new InputStreamReader(is));
                                purplei = 1;
                            }
                            else{
                                purplei++;
                            }
                            Ques = Globs.purpleFile.readLine();
                            Ans = Globs.purpleFile.readLine();
                        }
                        catch(IOException e){

                        }
                        tv1 = (TextView) findViewById(R.id.questionTxt);
                        tv1.setText(Ques);
                        break;
                    case 4: //GREEN
                        ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardgreen);
                        try {
                            if (greeni > Globs.greenMax) {
                                Globs.greenFile.close();
                                AssetManager am = getApplicationContext().getAssets();
                                InputStream is = am.open(Globs.qsetPath + "green.txt");
                                Globs.greenFile = new BufferedReader(new InputStreamReader(is));
                                greeni = 1;
                            }
                            else{
                                greeni++;
                            }
                            Ques = Globs.greenFile.readLine();
                            Ans = Globs.greenFile.readLine();
                        }
                        catch(IOException e){

                        }
                        tv1 = (TextView) findViewById(R.id.questionTxt);
                        tv1.setText(Ques);
                        break;
                    case 5: //ORANGE
                        ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardorange);
                        try {
                            if (orangei > Globs.orangeMax) {
                                Globs.orangeFile.close();
                                AssetManager am = getApplicationContext().getAssets();
                                InputStream is = am.open(Globs.qsetPath + "orange.txt");
                                Globs.orangeFile = new BufferedReader(new InputStreamReader(is));
                                orangei = 1;
                            }
                            else{
                                orangei++;
                            }
                            Ques = Globs.orangeFile.readLine();
                            Ans = Globs.orangeFile.readLine();
                        }
                        catch(IOException e){

                        }
                        tv1 = (TextView) findViewById(R.id.questionTxt);
                        tv1.setText(Ques);
                        break;
                }
                break;
            case BLUE:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardblue);
                try {
                    if (bluei > Globs.blueMax) {
                        Globs.blueFile.close();
                        AssetManager am = getApplicationContext().getAssets();
                        InputStream is = am.open(Globs.qsetPath + "blue.txt");
                        Globs.blueFile = new BufferedReader(new InputStreamReader(is));
                        bluei = 1;
                    }
                    else{
                        bluei++;
                    }
                    Ques = Globs.blueFile.readLine();
                    Ans = Globs.blueFile.readLine();
                }
                catch(IOException e){

                }
                tv1 = (TextView) findViewById(R.id.questionTxt);
                tv1.setText(Ques);
                break;
            case PINK:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardpink);
                try {
                    if (pinki > Globs.pinkMax) {
                        Globs.pinkFile.close();
                        AssetManager am = getApplicationContext().getAssets();
                        InputStream is = am.open(Globs.qsetPath + "pink.txt");
                        Globs.pinkFile = new BufferedReader(new InputStreamReader(is));
                        pinki = 1;
                    }
                    else{
                        pinki++;
                    }
                    Ques = Globs.pinkFile.readLine();
                    Ans = Globs.pinkFile.readLine();
                }
                catch(IOException e){

                }
                tv1 = (TextView) findViewById(R.id.questionTxt);
                tv1.setText(Ques);
                break;
            case YELLOW:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardyellow);
                try {
                    if (yellowi > Globs.yellowMax) {
                        Globs.yellowFile.close();
                        AssetManager am = getApplicationContext().getAssets();
                        InputStream is = am.open(Globs.qsetPath + "yellow.txt");
                        Globs.yellowFile = new BufferedReader(new InputStreamReader(is));
                        yellowi = 1;
                    }
                    else{
                        yellowi++;
                    }
                    Ques = Globs.yellowFile.readLine();
                    Ans = Globs.yellowFile.readLine();
                }
                catch(IOException e){

                }
                tv1 = (TextView) findViewById(R.id.questionTxt);
                tv1.setText(Ques);
                break;
            case PURPLE:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardpurple);
                try {
                    if (purplei > Globs.purpleMax) {
                        Globs.purpleFile.close();
                        AssetManager am = getApplicationContext().getAssets();
                        InputStream is = am.open(Globs.qsetPath + "purple.txt");
                        Globs.purpleFile = new BufferedReader(new InputStreamReader(is));
                        purplei = 1;
                    }
                    else{
                        purplei++;
                    }
                    Ques = Globs.purpleFile.readLine();
                    Ans = Globs.purpleFile.readLine();
                }
                catch(IOException e){

                }
                tv1 = (TextView) findViewById(R.id.questionTxt);
                tv1.setText(Ques);
                break;
            case GREEN:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardgreen);
                try {
                    if (greeni > Globs.greenMax) {
                        Globs.greenFile.close();
                        AssetManager am = getApplicationContext().getAssets();
                        InputStream is = am.open(Globs.qsetPath + "green.txt");
                        Globs.greenFile = new BufferedReader(new InputStreamReader(is));
                        greeni = 1;
                    }
                    else{
                        greeni++;
                    }
                    Ques = Globs.greenFile.readLine();
                    Ans = Globs.greenFile.readLine();
                }
                catch(IOException e){

                }
                tv1 = (TextView) findViewById(R.id.questionTxt);
                tv1.setText(Ques);
                break;
            case ORANGE:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardorange);
                try {
                    if (orangei > Globs.orangeMax) {
                        Globs.orangeFile.close();
                        AssetManager am = getApplicationContext().getAssets();
                        InputStream is = am.open(Globs.qsetPath + "orange.txt");
                        Globs.orangeFile = new BufferedReader(new InputStreamReader(is));
                        orangei = 1;
                    }
                    else{
                        orangei++;
                    }
                    Ques = Globs.orangeFile.readLine();
                    Ans = Globs.orangeFile.readLine();
                }
                catch(IOException e){

                }
                tv1 = (TextView) findViewById(R.id.questionTxt);
                tv1.setText(Ques);
                break;
        }

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
                Globs.loadedQSet = false;
            }
        }
        catch(IOException e){

        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed()
    {
        try {
            if(Globs.loadedQSet) {
                Globs.blueFile.close();
                Globs.pinkFile.close();
                Globs.yellowFile.close();
                Globs.purpleFile.close();
                Globs.greenFile.close();
                Globs.orangeFile.close();
                Globs.loadedQSet = false;
            }
        }
        catch(IOException e){

        }
        super.onBackPressed();
    }
}