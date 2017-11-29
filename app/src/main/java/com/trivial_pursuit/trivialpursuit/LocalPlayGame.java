package com.trivial_pursuit.trivialpursuit;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class LocalPlayGame extends AppCompatActivity {
    public boolean continueMusic;

    public Board boardTree;

    int windowwidth;
    int windowheight;
    int startx;
    int starty;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        continueMusic = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_play_game);

        pos = 0;
        boardTree = new Board();
        Globs.p1Loc = boardTree.centerNode;
        Globs.p2Loc = boardTree.centerNode;
        Globs.p3Loc = boardTree.centerNode;
        Globs.p4Loc = boardTree.centerNode;
        Globs.p5Loc = boardTree.centerNode;
        Globs.p6Loc = boardTree.centerNode;

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
        }
        catch(IOException e){

        }

        SharedPreferences mPrefs = getSharedPreferences(Globs.qsetPath.substring(Globs.qsetPath.indexOf("/")+1, Globs.qsetPath.length() - 1), 0);
        String mString = mPrefs.getString("blue", "");
        if(mString.equals("")){
            Random r = new Random();
            Globs.blueIdx = r.nextInt(Globs.blueMax) + 1;
            Globs.pinkIdx = r.nextInt(Globs.pinkMax) + 1;
            Globs.yellowIdx = r.nextInt(Globs.yellowMax) + 1;
            Globs.purpleIdx = r.nextInt(Globs.purpleMax) + 1;
            Globs.greenIdx = r.nextInt(Globs.greenMax) + 1;
            Globs.orangeIdx = r.nextInt(Globs.orangeMax) + 1;
        }
        else{
            Globs.blueIdx = Integer.parseInt(mString);
            Globs.pinkIdx = Integer.parseInt(mPrefs.getString("pink", ""));
            Globs.yellowIdx = Integer.parseInt(mPrefs.getString("yellow", ""));
            Globs.purpleIdx = Integer.parseInt(mPrefs.getString("purple", ""));
            Globs.greenIdx = Integer.parseInt(mPrefs.getString("green", ""));
            Globs.orangeIdx = Integer.parseInt(mPrefs.getString("orange", ""));
        }

        final ScrollView myScroll = (ScrollView) findViewById(R.id.boardscroll);
        myScroll.setOnTouchListener( new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        windowwidth = getWindowManager().getDefaultDisplay().getWidth();
        windowheight = getWindowManager().getDefaultDisplay().getHeight();
        final View vi = (View)findViewById(R.id.board);
        vi.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) vi.getLayoutParams();
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        startx = (int)event.getRawX();
                        starty = (int)event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                    case MotionEvent.ACTION_MOVE:
                        int x_cord = (int)event.getRawX();
                        int y_cord = (int)event.getRawY();

                        if(x_cord > startx + 10){
                           posLeft();
                        }
                        else if(x_cord < startx - 10){
                            posRight();
                        }
                        if(y_cord > starty + 10){
                            posUp();
                        }
                        else if(y_cord < starty - 10){
                            posDown();
                        }
                        startx = (int)event.getRawX();
                        starty = (int)event.getRawY();

                        posMov();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
    int xsize = 700;
    int ysize = 700;
    boolean fromL = true;

    public void posMov(){
        final View vi = (View)findViewById(R.id.board);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) vi.getLayoutParams();
        switch(pos){
            case 0:
                layoutParams.leftMargin = 0;
                layoutParams.topMargin = 0;
                break;
            case 1:
                layoutParams.leftMargin = xsize;
                layoutParams.topMargin = 0;
                break;
            case 2:
                layoutParams.leftMargin = xsize/2;
                layoutParams.topMargin = ysize;
                break;
            case 3:
                layoutParams.leftMargin = -xsize/2;
                layoutParams.topMargin = ysize;
                break;
            case 4:
                layoutParams.leftMargin = -xsize;
                layoutParams.topMargin = 0;
                break;
            case 5:
                layoutParams.leftMargin = -xsize/2;
                layoutParams.topMargin = -ysize;
                break;
            case 6:
                layoutParams.leftMargin = xsize/2;
                layoutParams.topMargin = -ysize;
                break;
        }
        vi.setLayoutParams(layoutParams);
    }

    public void posLeft(){
        switch(pos){
            case 0:
                pos = 1;
                break;
            case 1:
                break;
            case 2:
                pos = 1;
                break;
            case 3:
                pos = 2;
                break;
            case 4:
                pos = 0;
                break;
            case 5:
                pos = 6;
                break;
            case 6:
                pos = 1;
                break;
        }
        if(pos == 1 || pos == 2 || pos == 6){
            fromL = true;
        }
        else if(pos != 0){
            fromL = false;
        }
    }

    public void posRight(){
        switch(pos){
            case 0:
                pos = 4;
                break;
            case 1:
                pos = 0;
                break;
            case 2:
                pos = 3;
                break;
            case 3:
                pos = 4;
                break;
            case 4:
                break;
            case 5:
                pos = 4;
                break;
            case 6:
                pos = 5;
                break;
        }
        if(pos == 1 || pos == 2 || pos == 6){
            fromL = true;
        }
        else if(pos != 0){
            fromL = false;
        }
    }

    public void posDown(){
        switch(pos){
            case 0:
                if(fromL)
                    pos = 6;
                else
                    pos = 5;
                break;
            case 1:
                pos = 6;
                break;
            case 2:
                pos = 1;
                break;
            case 3:
                pos = 4;
                break;
            case 4:
                pos = 5;
                break;
            case 5:
                break;
            case 6:
                break;
        }
        if(pos == 1 || pos == 2 || pos == 6){
            fromL = true;
        }
        else if(pos != 0){
            fromL = false;
        }
    }

    public void posUp(){
        switch(pos){
            case 0:
                if(fromL)
                    pos = 2;
                else
                    pos = 3;
                break;
            case 1:
                pos = 2;
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                pos = 3;
                break;
            case 5:
                pos = 4;
                break;
            case 6:
                pos = 1;
                break;
        }
        if(pos == 1 || pos == 2 || pos == 6){
            fromL = true;
        }
        else if(pos != 0){
            fromL = false;
        }
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
        MusicManager.start(this, MusicManager.MUSIC_GAME, true);
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
}
