package com.trivial_pursuit.trivialpursuit;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.plattysoft.leonids.ParticleSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class LocalPlayGame extends AppCompatActivity {
    public boolean continueMusic;

    public Board boardTree;

    int windowwidth;
    int windowheight;
    int startx;
    int starty;
    int pos;

    int playerTurn;

    public int p1wedges;
    public int p2wedges;
    public int p3wedges;
    public int p4wedges;
    public int p5wedges;
    public int p6wedges;

    public boolean cardPlayed;
    public long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        continueMusic = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_play_game);

        hidewedges();

        pos = 0;
        boardTree = new Board();
        Globs.p1Loc = boardTree.centerNode;
        Globs.p2Loc = boardTree.centerNode;
        Globs.p3Loc = boardTree.centerNode;
        Globs.p4Loc = boardTree.centerNode;
        Globs.p5Loc = boardTree.centerNode;
        Globs.p6Loc = boardTree.centerNode;
        playerTurn = 1;

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
        Quick.pushToLineNum(Globs.blueIdx, Globs.blueFile);
        Quick.pushToLineNum(Globs.pinkIdx, Globs.pinkFile);
        Quick.pushToLineNum(Globs.yellowIdx, Globs.yellowFile);
        Quick.pushToLineNum(Globs.purpleIdx, Globs.purpleFile);
        Quick.pushToLineNum(Globs.greenIdx, Globs.greenFile);
        Quick.pushToLineNum(Globs.orangeIdx, Globs.orangeFile);
        ImageView imgView= (ImageView) findViewById(R.id.board);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener);
        imgView= (ImageView) findViewById(R.id.board1);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener1);
        imgView= (ImageView) findViewById(R.id.board2);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener2);
        imgView= (ImageView) findViewById(R.id.board3);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener3);
        imgView= (ImageView) findViewById(R.id.board4);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener4);
        imgView= (ImageView) findViewById(R.id.board5);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener5);
        imgView= (ImageView) findViewById(R.id.board6);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener6);
        imgView= (ImageView) findViewById(R.id.board7);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener7);
        imgView= (ImageView) findViewById(R.id.board8);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener8);
        imgView= (ImageView) findViewById(R.id.board9);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener9);
        imgView= (ImageView) findViewById(R.id.board10);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener10);
        imgView= (ImageView) findViewById(R.id.board11);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener11);
        imgView= (ImageView) findViewById(R.id.board12);
        imgView.setDrawingCacheEnabled(true);
        imgView.setOnTouchListener(boardListener12);

        imgView = (ImageView) findViewById(R.id.die);
        imgView.setVisibility(View.INVISIBLE);

        setPics();

        if(Globs.playerCnt < 6){
            imgView = (ImageView) findViewById(R.id.p6);
            imgView.setVisibility(View.INVISIBLE);
        }
        if(Globs.playerCnt < 5){
            imgView = (ImageView) findViewById(R.id.p5);
            imgView.setVisibility(View.INVISIBLE);
        }
        if(Globs.playerCnt < 4){
            imgView = (ImageView) findViewById(R.id.p4);
            imgView.setVisibility(View.INVISIBLE);
        }
        if(Globs.playerCnt < 3){
            imgView = (ImageView) findViewById(R.id.p3);
            imgView.setVisibility(View.INVISIBLE);
        }

        p1wedges = 0;
        p2wedges = 0;
        p3wedges = 0;
        p4wedges = 0;
        p5wedges = 0;
        p6wedges = 0;

        Globs.gameOn = false;
        turnOn = false;
        cardPlayed = false;
    }

    public ArrayList<BoardNode> moveableLocs = null;
    public boolean turnOn;

    public void startTurn(){
        if(Globs.gameOn) {
            turnOn = true;
            Globs.gameOn = false;
            int roll = Globs.newRoll;
            changeDie(roll);
            BoardNode playerLoc = getPlayerLoc();
            moveableLocs = playerLoc.adjList(roll, playerLoc);
        }
        else{
            ImageView imgView = (ImageView) findViewById(R.id.die);
            imgView.setVisibility(View.INVISIBLE);
            toFront();
            moveableLocs = null;
            Intent intent = new Intent(this, Dice.class);
            startActivity(intent);
        }
    }

    public boolean rulesShown = false;

    public void showRule(View v){
        rulesShown = true;
        Intent intent = new Intent(this, Rules.class);
        startActivity(intent);
    }

    public void endTurn(){
        if(Globs.newQ == Globs.Cat.BLACK || Globs.newQ == Globs.Cat.WHITE){
            startTurn();
        }
        else {
            cardPlayed = true;
            Intent intent = new Intent(this, Cards.class);
            startActivity(intent);
        }
    }

    public void teamPick(Globs.Colors c){
        ImageView i = (ImageView)findViewById(R.id.team);
        switch (c){
            case BLUE:
                Globs.teamDraw = R.drawable.blueteam;
                i.setImageResource(R.drawable.blueteam);
                break;
            case PINK:
                Globs.teamDraw = R.drawable.pinkteam;
                i.setImageResource(R.drawable.pinkteam);
                break;
            case YELLOW:
                Globs.teamDraw = R.drawable.yellowteam;
                i.setImageResource(R.drawable.yellowteam);
                break;
            case PURPLE:
                Globs.teamDraw = R.drawable.purpleteam;
                i.setImageResource(R.drawable.purpleteam);
                break;
            case GREEN:
                Globs.teamDraw = R.drawable.greenteam;
                i.setImageResource(R.drawable.greenteam);
                break;
            case ORANGE:
                Globs.teamDraw = R.drawable.orangeteam;
                i.setImageResource(R.drawable.orangeteam);
                break;
        }
    }

    public void toFront(){
        ImageView imgView;
        switch(playerTurn){
            case 1:
                imgView = (ImageView) findViewById(R.id.p1);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p1wbl);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p1wgr);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p1wor);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p1wpi);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p1wpu);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p1wye);
                imgView.bringToFront();
                teamPick(Globs.p1);
                break;
            case 2:
                imgView = (ImageView) findViewById(R.id.p2);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p2wbl);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p2wgr);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p2wor);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p2wpi);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p2wpu);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p2wye);
                imgView.bringToFront();
                teamPick(Globs.p2);
                break;
            case 3:
                imgView = (ImageView) findViewById(R.id.p3);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p3wbl);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p3wgr);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p3wor);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p3wpi);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p3wpu);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p3wye);
                imgView.bringToFront();
                teamPick(Globs.p3);
                break;
            case 4:
                imgView = (ImageView) findViewById(R.id.p4);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p4wbl);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p4wgr);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p4wor);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p4wpi);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p4wpu);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p4wye);
                imgView.bringToFront();
                teamPick(Globs.p4);
                break;
            case 5:
                imgView = (ImageView) findViewById(R.id.p5);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p5wbl);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p5wgr);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p5wor);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p5wpi);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p5wpu);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p5wye);
                imgView.bringToFront();
                teamPick(Globs.p5);
                break;
            case 6:
                imgView = (ImageView) findViewById(R.id.p6);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p6wbl);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p6wgr);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p6wor);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p6wpi);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p6wpu);
                imgView.bringToFront();
                imgView = (ImageView) findViewById(R.id.p6wye);
                imgView.bringToFront();
                teamPick(Globs.p6);
                break;
        }
    }

    public void setPics(){
        ImageView imgView;
        if(Globs.p1 != null) {
            imgView = (ImageView) findViewById(R.id.p1);
            switch (Globs.p1){
                case BLUE:
                    imgView.setImageResource(R.drawable.piecebl);
                    break;
                case PINK:
                    imgView.setImageResource(R.drawable.piecepi);
                    break;
                case YELLOW:
                    imgView.setImageResource(R.drawable.pieceye);
                    break;
                case PURPLE:
                    imgView.setImageResource(R.drawable.piecepu);
                    break;
                case GREEN:
                    imgView.setImageResource(R.drawable.piecegr);
                    break;
                case ORANGE:
                    imgView.setImageResource(R.drawable.pieceor);
                    break;
            }
        }
        if(Globs.p2 != null) {
            imgView = (ImageView) findViewById(R.id.p2);
            switch (Globs.p2){
                case BLUE:
                    imgView.setImageResource(R.drawable.piecebl);
                    break;
                case PINK:
                    imgView.setImageResource(R.drawable.piecepi);
                    break;
                case YELLOW:
                    imgView.setImageResource(R.drawable.pieceye);
                    break;
                case PURPLE:
                    imgView.setImageResource(R.drawable.piecepu);
                    break;
                case GREEN:
                    imgView.setImageResource(R.drawable.piecegr);
                    break;
                case ORANGE:
                    imgView.setImageResource(R.drawable.pieceor);
                    break;
            }
        }
        if(Globs.p3 != null) {
            imgView = (ImageView) findViewById(R.id.p3);
            switch (Globs.p3){
                case BLUE:
                    imgView.setImageResource(R.drawable.piecebl);
                    break;
                case PINK:
                    imgView.setImageResource(R.drawable.piecepi);
                    break;
                case YELLOW:
                    imgView.setImageResource(R.drawable.pieceye);
                    break;
                case PURPLE:
                    imgView.setImageResource(R.drawable.piecepu);
                    break;
                case GREEN:
                    imgView.setImageResource(R.drawable.piecegr);
                    break;
                case ORANGE:
                    imgView.setImageResource(R.drawable.pieceor);
                    break;
            }
        }
        if(Globs.p4 != null) {
            imgView = (ImageView) findViewById(R.id.p4);
            switch (Globs.p4){
                case BLUE:
                    imgView.setImageResource(R.drawable.piecebl);
                    break;
                case PINK:
                    imgView.setImageResource(R.drawable.piecepi);
                    break;
                case YELLOW:
                    imgView.setImageResource(R.drawable.pieceye);
                    break;
                case PURPLE:
                    imgView.setImageResource(R.drawable.piecepu);
                    break;
                case GREEN:
                    imgView.setImageResource(R.drawable.piecegr);
                    break;
                case ORANGE:
                    imgView.setImageResource(R.drawable.pieceor);
                    break;
            }
        }
        if(Globs.p5 != null) {
            imgView = (ImageView) findViewById(R.id.p5);
            switch (Globs.p5){
                case BLUE:
                    imgView.setImageResource(R.drawable.piecebl);
                    break;
                case PINK:
                    imgView.setImageResource(R.drawable.piecepi);
                    break;
                case YELLOW:
                    imgView.setImageResource(R.drawable.pieceye);
                    break;
                case PURPLE:
                    imgView.setImageResource(R.drawable.piecepu);
                    break;
                case GREEN:
                    imgView.setImageResource(R.drawable.piecegr);
                    break;
                case ORANGE:
                    imgView.setImageResource(R.drawable.pieceor);
                    break;
            }
        }
        if(Globs.p6 != null) {
            imgView = (ImageView) findViewById(R.id.p6);
            switch (Globs.p6){
                case BLUE:
                    imgView.setImageResource(R.drawable.piecebl);
                    break;
                case PINK:
                    imgView.setImageResource(R.drawable.piecepi);
                    break;
                case YELLOW:
                    imgView.setImageResource(R.drawable.pieceye);
                    break;
                case PURPLE:
                    imgView.setImageResource(R.drawable.piecepu);
                    break;
                case GREEN:
                    imgView.setImageResource(R.drawable.piecegr);
                    break;
                case ORANGE:
                    imgView.setImageResource(R.drawable.pieceor);
                    break;
            }
        }
    }

    public void changeDie(int r){
        ImageView imgView = (ImageView) findViewById(R.id.die);
        imgView.setVisibility(View.VISIBLE);
        switch(r){
            case 1:
                imgView.setImageResource(R.drawable.dice1);
                break;
            case 2:
                imgView.setImageResource(R.drawable.dice2);
                break;
            case 3:
                imgView.setImageResource(R.drawable.dice3);
                break;
            case 4:
                imgView.setImageResource(R.drawable.dice4);
                break;
            case 5:
                imgView.setImageResource(R.drawable.dice5);
                break;
            case 6:
                imgView.setImageResource(R.drawable.dice6);
                break;
        }
    }

    public BoardNode getPlayerLoc(){
        switch(playerTurn){
            case 1:
                return Globs.p1Loc;
            case 2:
                return Globs.p2Loc;
            case 3:
                return Globs.p3Loc;
            case 4:
                return Globs.p4Loc;
            case 5:
                return Globs.p5Loc;
            default:
                return Globs.p6Loc;
        }
    }

    public void updatePiece(BoardNode n){
        switch(playerTurn){
            case 1:
                Globs.p1Loc = n;
                break;
            case 2:
                Globs.p2Loc = n;
                break;
            case 3:
                Globs.p3Loc = n;
                break;
            case 4:
                Globs.p4Loc = n;
                break;
            case 5:
                Globs.p5Loc = n;
                break;
            case 6:
                Globs.p6Loc = n;
                break;
        }
        turnOn = false;
    }

    public View.OnTouchListener boardListenerp1 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    };
    public View.OnTouchListener boardListenerp2 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    };
    public View.OnTouchListener boardListenerp3 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    };
    public View.OnTouchListener boardListenerp4 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    };
    public View.OnTouchListener boardListenerp5 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    };
    public View.OnTouchListener boardListenerp6 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    };

    public View.OnTouchListener boardListener1 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                int redV = Color.red(color);
                int greenV = Color.green(color);
                int blueV = Color.blue(color);
                Globs.Cat cat = detectColor(redV, greenV, blueV);
                Globs.newQ = cat;
                BoardNode node = null;
                if(moveableLocs != null) {
                    switch (cat) {
                        case BLUE:
                            node = boardTree.allNodes.get(12);
                            break;
                        case PINK:
                            node = boardTree.allNodes.get(10);
                            break;
                        case YELLOW:
                            break;
                        case PURPLE:
                            node = boardTree.allNodes.get(13);
                            break;
                        case GREEN:
                            node = boardTree.allNodes.get(9);
                            break;
                        case ORANGE:
                            node = boardTree.allNodes.get(7);
                            break;
                        case WHITE:
                            node = boardTree.allNodes.get(8);
                            break;
                        case BLACK:
                            node = boardTree.allNodes.get(11);
                            break;
                        case NONE:
                            break;
                    }
                    if((node != null) && moveableLocs.contains(node)){
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        if(node.wedge){
                            wedgeP = true;
                        }
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                            Context context = getApplicationContext();
                            CharSequence text = "Illegal Move";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                    }
                }
                return true;
            }
        }
    };

    public View.OnTouchListener boardListener2 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                int redV = Color.red(color);
                int greenV = Color.green(color);
                int blueV = Color.blue(color);
                Globs.Cat cat = detectColor(redV, greenV, blueV);
                Globs.newQ = cat;
                BoardNode node = null;
                if(moveableLocs != null) {
                    switch (cat) {
                        case BLUE:
                            node = boardTree.allNodes.get(14);
                            break;
                        case PINK:
                            node = boardTree.allNodes.get(19);
                            break;
                        case YELLOW:
                            node = boardTree.allNodes.get(17);
                            break;
                        case PURPLE:
                            break;
                        case GREEN:
                            node = boardTree.allNodes.get(20);
                            break;
                        case ORANGE:
                            node = boardTree.allNodes.get(16);
                            break;
                        case WHITE:
                            node = boardTree.allNodes.get(15);
                            break;
                        case BLACK:
                            node = boardTree.allNodes.get(18);
                            break;
                        case NONE:
                            break;
                    }
                    if((node != null) && moveableLocs.contains(node)){
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        if(node.wedge){
                            wedgeP = true;
                        }
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal Move";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return true;
            }
        }
    };

    public View.OnTouchListener boardListener3 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                int redV = Color.red(color);
                int greenV = Color.green(color);
                int blueV = Color.blue(color);
                Globs.Cat cat = detectColor(redV, greenV, blueV);
                Globs.newQ = cat;
                BoardNode node = null;
                if(moveableLocs != null) {
                    switch (cat) {
                        case BLUE:
                            node = boardTree.allNodes.get(23);
                            break;
                        case PINK:
                            node = boardTree.allNodes.get(21);
                            break;
                        case YELLOW:
                            node = boardTree.allNodes.get(26);
                            break;
                        case PURPLE:
                            node = boardTree.allNodes.get(24);
                            break;
                        case GREEN:
                            break;
                        case ORANGE:
                            node = boardTree.allNodes.get(27);
                            break;
                        case WHITE:
                            node = boardTree.allNodes.get(22);
                            break;
                        case BLACK:
                            node = boardTree.allNodes.get(25);
                            break;
                        case NONE:
                            break;
                    }
                    if((node != null) && moveableLocs.contains(node)){
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        if(node.wedge){
                            wedgeP = true;
                        }
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal Move";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return true;
            }
        }
    };

    public View.OnTouchListener boardListener4 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                int redV = Color.red(color);
                int greenV = Color.green(color);
                int blueV = Color.blue(color);
                Globs.Cat cat = detectColor(redV, greenV, blueV);
                Globs.newQ = cat;
                BoardNode node = null;
                if(moveableLocs != null) {
                    switch (cat) {
                        case BLUE:
                            node = boardTree.allNodes.get(34);
                            break;
                        case PINK:
                            node = boardTree.allNodes.get(30);
                            break;
                        case YELLOW:
                            node = boardTree.allNodes.get(28);
                            break;
                        case PURPLE:
                            node = boardTree.allNodes.get(33);
                            break;
                        case GREEN:
                            node = boardTree.allNodes.get(31);
                            break;
                        case ORANGE:
                            break;
                        case WHITE:
                            node = boardTree.allNodes.get(29);
                            break;
                        case BLACK:
                            node = boardTree.allNodes.get(32);
                            break;
                        case NONE:
                            break;
                    }
                    if((node != null) && moveableLocs.contains(node)){
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        if(node.wedge){
                            wedgeP = true;
                        }
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal Move";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return true;
            }
        }
    };

    public View.OnTouchListener boardListener5 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                int redV = Color.red(color);
                int greenV = Color.green(color);
                int blueV = Color.blue(color);
                Globs.Cat cat = detectColor(redV, greenV, blueV);
                Globs.newQ = cat;
                BoardNode node = null;
                if(moveableLocs != null) {
                    switch (cat) {
                        case BLUE:
                            break;
                        case PINK:
                            node = boardTree.allNodes.get(41);
                            break;
                        case YELLOW:
                            node = boardTree.allNodes.get(37);
                            break;
                        case PURPLE:
                            node = boardTree.allNodes.get(35);
                            break;
                        case GREEN:
                            node = boardTree.allNodes.get(40);
                            break;
                        case ORANGE:
                            node = boardTree.allNodes.get(38);
                            break;
                        case WHITE:
                            node = boardTree.allNodes.get(36);
                            break;
                        case BLACK:
                            node = boardTree.allNodes.get(39);
                            break;
                        case NONE:
                            break;
                    }
                    if((node != null) && moveableLocs.contains(node)){
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        if(node.wedge){
                            wedgeP = true;
                        }
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal Move";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return true;
            }
        }
    };

    public View.OnTouchListener boardListener6 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                int redV = Color.red(color);
                int greenV = Color.green(color);
                int blueV = Color.blue(color);
                Globs.Cat cat = detectColor(redV, greenV, blueV);
                Globs.newQ = cat;
                BoardNode node = null;
                if(moveableLocs != null) {
                    switch (cat) {
                        case BLUE:
                            node = boardTree.allNodes.get(45);
                            break;
                        case PINK:
                            break;
                        case YELLOW:
                            node = boardTree.allNodes.get(6);
                            break;
                        case PURPLE:
                            node = boardTree.allNodes.get(44);
                            break;
                        case GREEN:
                            node = boardTree.allNodes.get(42);
                            break;
                        case ORANGE:
                            node = boardTree.allNodes.get(47);
                            break;
                        case WHITE:
                            node = boardTree.allNodes.get(43);
                            break;
                        case BLACK:
                            node = boardTree.allNodes.get(46);
                            break;
                        case NONE:
                            break;
                    }
                    if((node != null) && moveableLocs.contains(node)){
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        if(node.wedge){
                            wedgeP = true;
                        }
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal Move";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return true;
            }
        }
    };

    public View.OnTouchListener boardListener7 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                int redV = Color.red(color);
                int greenV = Color.green(color);
                int blueV = Color.blue(color);
                Globs.Cat cat = detectColor(redV, greenV, blueV);
                Globs.newQ = cat;
                BoardNode node = null;
                if(moveableLocs != null) {
                    switch (cat) {
                        case BLUE:
                            node = boardTree.allNodes.get(68);
                            break;
                        case PINK:
                            break;
                        case YELLOW:
                            node = boardTree.allNodes.get(69);
                            break;
                        case PURPLE:
                            node = boardTree.allNodes.get(70);
                            break;
                        case GREEN:
                            node = boardTree.allNodes.get(72);
                            break;
                        case ORANGE:
                            node = boardTree.allNodes.get(71);
                            break;
                        case WHITE:
                            break;
                        case NONE:
                            break;
                    }
                    if((node != null) && moveableLocs.contains(node)){
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        if(node.wedge){
                            wedgeP = true;
                        }
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal Move";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return true;
            }
        }
    };

    public View.OnTouchListener boardListener8 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                int redV = Color.red(color);
                int greenV = Color.green(color);
                int blueV = Color.blue(color);
                Globs.Cat cat = detectColor(redV, greenV, blueV);
                Globs.newQ = cat;
                BoardNode node = null;
                if(moveableLocs != null) {
                    switch (cat) {
                        case BLUE:
                            node = boardTree.allNodes.get(4);
                            break;
                        case PINK:
                            node = boardTree.allNodes.get(1);
                            break;
                        case YELLOW:
                            break;
                        case PURPLE:
                            node = boardTree.allNodes.get(2);
                            break;
                        case GREEN:
                            node = boardTree.allNodes.get(3);
                            break;
                        case ORANGE:
                            node = boardTree.allNodes.get(5);
                            break;
                        case WHITE:
                            break;
                        case NONE:
                            break;
                    }
                    if((node != null) && moveableLocs.contains(node)){
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        if(node.wedge){
                            wedgeP = true;
                        }
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal Move";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return true;
            }
        }
    };

    public View.OnTouchListener boardListener9 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                int redV = Color.red(color);
                int greenV = Color.green(color);
                int blueV = Color.blue(color);
                Globs.Cat cat = detectColor(redV, greenV, blueV);
                Globs.newQ = cat;
                BoardNode node = null;
                if(moveableLocs != null) {
                    switch (cat) {
                        case BLUE:
                            node = boardTree.allNodes.get(52);
                            break;
                        case PINK:
                            node = boardTree.allNodes.get(51);
                            break;
                        case YELLOW:
                            node = boardTree.allNodes.get(48);
                            break;
                        case PURPLE:
                            break;
                        case GREEN:
                            node = boardTree.allNodes.get(49);
                            break;
                        case ORANGE:
                            node = boardTree.allNodes.get(50);
                            break;
                        case WHITE:
                            break;
                        case NONE:
                            break;
                    }
                    if((node != null) && moveableLocs.contains(node)){
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        if(node.wedge){
                            wedgeP = true;
                        }
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal Move";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return true;
            }
        }
    };

    public View.OnTouchListener boardListener10 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                int redV = Color.red(color);
                int greenV = Color.green(color);
                int blueV = Color.blue(color);
                Globs.Cat cat = detectColor(redV, greenV, blueV);
                Globs.newQ = cat;
                BoardNode node = null;
                if(moveableLocs != null) {
                    switch (cat) {
                        case BLUE:
                            node = boardTree.allNodes.get(55);
                            break;
                        case PINK:
                            node = boardTree.allNodes.get(57);
                            break;
                        case YELLOW:
                            node = boardTree.allNodes.get(56);
                            break;
                        case PURPLE:
                            node = boardTree.allNodes.get(53);
                            break;
                        case GREEN:
                            break;
                        case ORANGE:
                            node = boardTree.allNodes.get(54);
                            break;
                        case WHITE:
                            break;
                        case NONE:
                            break;
                    }
                    if((node != null) && moveableLocs.contains(node)){
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        if(node.wedge){
                            wedgeP = true;
                        }
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal Move";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return true;
            }
        }
    };

    public View.OnTouchListener boardListener11 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                int redV = Color.red(color);
                int greenV = Color.green(color);
                int blueV = Color.blue(color);
                Globs.Cat cat = detectColor(redV, greenV, blueV);
                Globs.newQ = cat;
                BoardNode node = null;
                if(moveableLocs != null) {
                    switch (cat) {
                        case BLUE:
                            node = boardTree.allNodes.get(59);
                            break;
                        case PINK:
                            node = boardTree.allNodes.get(60);
                            break;
                        case YELLOW:
                            node = boardTree.allNodes.get(62);
                            break;
                        case PURPLE:
                            node = boardTree.allNodes.get(61);
                            break;
                        case GREEN:
                            node = boardTree.allNodes.get(58);
                            break;
                        case ORANGE:
                            break;
                        case WHITE:
                            break;
                        case NONE:
                            break;
                    }
                    if((node != null) && moveableLocs.contains(node)){
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        if(node.wedge){
                            wedgeP = true;
                        }
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal Move";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return true;
            }
        }
    };

    public View.OnTouchListener boardListener12 = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                int redV = Color.red(color);
                int greenV = Color.green(color);
                int blueV = Color.blue(color);
                Globs.Cat cat = detectColor(redV, greenV, blueV);
                Globs.newQ = cat;
                BoardNode node = null;
                if(moveableLocs != null) {
                    switch (cat) {
                        case BLUE:
                            break;
                        case PINK:
                            node = boardTree.allNodes.get(64);
                            break;
                        case YELLOW:
                            node = boardTree.allNodes.get(65);
                            break;
                        case PURPLE:
                            node = boardTree.allNodes.get(67);
                            break;
                        case GREEN:
                            node = boardTree.allNodes.get(66);
                            break;
                        case ORANGE:
                            node = boardTree.allNodes.get(63);
                            break;
                        case WHITE:
                            break;
                        case NONE:
                            break;
                    }
                    if((node != null) && moveableLocs.contains(node)){
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        if(node.wedge){
                            wedgeP = true;
                        }
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal Move";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return true;
            }
        }
    };

    public View.OnTouchListener boardListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
            int color = bmp.getPixel((int) event.getX(), (int) event.getY());
            if (color == Color.TRANSPARENT || !turnOn)
                return false;
            else {
                if(moveableLocs != null) {
                    BoardNode node = boardTree.allNodes.get(0);
                    if (moveableLocs.contains(node)) {
                        Globs.newQ = Globs.Cat.BLUE;
                        movPiece(event.getX(), event.getY());
                        updatePiece(node);
                        centerP = true;
                        endTurn();
                    }
                    else{
                        long curtime = System.currentTimeMillis();
                        if(curtime < time + 3000) {
                            return true;
                        }
                        time = curtime;
                        Context context = getApplicationContext();
                        CharSequence text = "Illegal Move";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }
                return true;
            }
        }
    };

    public void awardWedge(){
        ImageView i;
        switch(playerTurn){
            case 1:
                switch(Globs.newQ){
                    case BLUE:
                        i = (ImageView)findViewById(R.id.p1wbl);
                        if(i.getVisibility() == View.INVISIBLE){
                            p1wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case PINK:
                        i = (ImageView)findViewById(R.id.p1wpi);
                        if(i.getVisibility() == View.INVISIBLE){
                            p1wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case YELLOW:
                        i = (ImageView)findViewById(R.id.p1wye);
                        if(i.getVisibility() == View.INVISIBLE){
                            p1wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case PURPLE:
                        i = (ImageView)findViewById(R.id.p1wpu);
                        if(i.getVisibility() == View.INVISIBLE){
                            p1wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case GREEN:
                        i = (ImageView)findViewById(R.id.p1wgr);
                        if(i.getVisibility() == View.INVISIBLE){
                            p1wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case ORANGE:
                        i = (ImageView)findViewById(R.id.p1wor);
                        if(i.getVisibility() == View.INVISIBLE){
                            p1wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case 2:
                switch(Globs.newQ){
                    case BLUE:
                        i = (ImageView)findViewById(R.id.p2wbl);
                        if(i.getVisibility() == View.INVISIBLE){
                            p2wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case PINK:
                        i = (ImageView)findViewById(R.id.p2wpi);
                        if(i.getVisibility() == View.INVISIBLE){
                            p2wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case YELLOW:
                        i = (ImageView)findViewById(R.id.p2wye);
                        if(i.getVisibility() == View.INVISIBLE){
                            p2wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case PURPLE:
                        i = (ImageView)findViewById(R.id.p2wpu);
                        if(i.getVisibility() == View.INVISIBLE){
                            p2wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case GREEN:
                        i = (ImageView)findViewById(R.id.p2wgr);
                        if(i.getVisibility() == View.INVISIBLE){
                            p2wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case ORANGE:
                        i = (ImageView)findViewById(R.id.p2wor);
                        if(i.getVisibility() == View.INVISIBLE){
                            p2wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case 3:
                switch(Globs.newQ){
                    case BLUE:
                        i = (ImageView)findViewById(R.id.p3wbl);
                        if(i.getVisibility() == View.INVISIBLE){
                            p3wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case PINK:
                        i = (ImageView)findViewById(R.id.p3wpi);
                        if(i.getVisibility() == View.INVISIBLE){
                            p3wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case YELLOW:
                        i = (ImageView)findViewById(R.id.p3wye);
                        if(i.getVisibility() == View.INVISIBLE){
                            p3wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case PURPLE:
                        i = (ImageView)findViewById(R.id.p3wpu);
                        if(i.getVisibility() == View.INVISIBLE){
                            p3wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case GREEN:
                        i = (ImageView)findViewById(R.id.p3wgr);
                        if(i.getVisibility() == View.INVISIBLE){
                            p3wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case ORANGE:
                        i = (ImageView)findViewById(R.id.p3wor);
                        if(i.getVisibility() == View.INVISIBLE){
                            p3wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case 4:
                switch(Globs.newQ){
                    case BLUE:
                        i = (ImageView)findViewById(R.id.p4wbl);
                        if(i.getVisibility() == View.INVISIBLE){
                            p4wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case PINK:
                        i = (ImageView)findViewById(R.id.p4wpi);
                        if(i.getVisibility() == View.INVISIBLE){
                            p4wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case YELLOW:
                        i = (ImageView)findViewById(R.id.p4wye);
                        if(i.getVisibility() == View.INVISIBLE){
                            p4wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case PURPLE:
                        i = (ImageView)findViewById(R.id.p4wpu);
                        if(i.getVisibility() == View.INVISIBLE){
                            p4wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case GREEN:
                        i = (ImageView)findViewById(R.id.p4wgr);
                        if(i.getVisibility() == View.INVISIBLE){
                            p4wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case ORANGE:
                        i = (ImageView)findViewById(R.id.p4wor);
                        if(i.getVisibility() == View.INVISIBLE){
                            p4wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case 5:
                switch(Globs.newQ){
                    case BLUE:
                        i = (ImageView)findViewById(R.id.p5wbl);
                        if(i.getVisibility() == View.INVISIBLE){
                            p5wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case PINK:
                        i = (ImageView)findViewById(R.id.p5wpi);
                        if(i.getVisibility() == View.INVISIBLE){
                            p5wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case YELLOW:
                        i = (ImageView)findViewById(R.id.p5wye);
                        if(i.getVisibility() == View.INVISIBLE){
                            p5wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case PURPLE:
                        i = (ImageView)findViewById(R.id.p5wpu);
                        if(i.getVisibility() == View.INVISIBLE){
                            p5wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case GREEN:
                        i = (ImageView)findViewById(R.id.p5wgr);
                        if(i.getVisibility() == View.INVISIBLE){
                            p5wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case ORANGE:
                        i = (ImageView)findViewById(R.id.p5wor);
                        if(i.getVisibility() == View.INVISIBLE){
                            p5wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                }
                break;
            case 6:
                switch(Globs.newQ){
                    case BLUE:
                        i = (ImageView)findViewById(R.id.p6wbl);
                        if(i.getVisibility() == View.INVISIBLE){
                            p6wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case PINK:
                        i = (ImageView)findViewById(R.id.p6wpi);
                        if(i.getVisibility() == View.INVISIBLE){
                            p6wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case YELLOW:
                        i = (ImageView)findViewById(R.id.p6wye);
                        if(i.getVisibility() == View.INVISIBLE){
                            p6wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case PURPLE:
                        i = (ImageView)findViewById(R.id.p6wpu);
                        if(i.getVisibility() == View.INVISIBLE){
                            p6wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case GREEN:
                        i = (ImageView)findViewById(R.id.p6wgr);
                        if(i.getVisibility() == View.INVISIBLE){
                            p6wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                    case ORANGE:
                        i = (ImageView)findViewById(R.id.p6wor);
                        if(i.getVisibility() == View.INVISIBLE){
                            p6wedges++;
                        }
                        i.setVisibility(View.VISIBLE);
                        break;
                }
                break;
        }
    }

    public void checkWinner(){
        switch(playerTurn){
            case 1:
                if(p1wedges == 6){
                    chickenDinner = true;
                }
                break;
            case 2:
                if(p2wedges == 6){
                    chickenDinner = true;
                }
                break;
            case 3:
                if(p3wedges == 6){
                    chickenDinner = true;
                }
                break;
            case 4:
                if(p4wedges == 6){
                    chickenDinner = true;
                }
                break;
            case 5:
                if(p5wedges == 6){
                    chickenDinner = true;
                }
                break;
            case 6:
                if(p6wedges == 6){
                    chickenDinner = true;
                }
                break;
        }
    }

    public void fireworks(){
        View view = playerView();
        Globs.sound.chickensound();
        ParticleSystem p0 = new ParticleSystem(this, 100, R.drawable.star_blue, 4000);
        p0.setSpeedRange(0.2f, 0.5f);
        ParticleSystem p1 = new ParticleSystem(this, 100, R.drawable.star_pink, 4000);
        p1.setSpeedRange(0.2f, 0.5f);
        ParticleSystem p2 = new ParticleSystem(this, 100, R.drawable.star_yellow, 4000);
        p2.setSpeedRange(0.2f, 0.5f);
        ParticleSystem p3 = new ParticleSystem(this, 100, R.drawable.star_purple, 4000);
        p3.setSpeedRange(0.2f, 0.5f);
        ParticleSystem p4 = new ParticleSystem(this, 100, R.drawable.star_green, 4000);
        p4.setSpeedRange(0.2f, 0.5f);
        ParticleSystem p5 = new ParticleSystem(this, 100, R.drawable.star_orange, 4000);
        p5.setSpeedRange(0.2f, 0.5f);
        p0.oneShot(view, 100);
        p1.oneShot(view, 100);
        p2.oneShot(view, 100);
        p3.oneShot(view, 100);
        p4.oneShot(view, 100);
        p5.oneShot(view, 100);
    }

    public void endGame(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Globs.backToMain = true;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                onBackPressed();
            }
        }, 3200);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                fireworks();
            }
        }, 1000);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public View playerView(){
        switch (playerTurn){
            case 1:
                return findViewById(R.id.p1);
            case 2:
                return findViewById(R.id.p2);
            case 3:
                return findViewById(R.id.p3);
            case 4:
                return findViewById(R.id.p4);
            case 5:
                return findViewById(R.id.p5);
            default:
                return findViewById(R.id.p6);
        }
    }

    //GLOBALS
    boolean moved;
    int xsize = 200;
    int ysize = 200;
    boolean fromL = true;
    ArrayList<View> array = new ArrayList<View>();
    public boolean wedgeP = false;
    public boolean centerP = false;
    public boolean chickenDinner = false;

    public void movPiece(float x, float y){
        View v;
        View v1;
        View v2;
        View v3;
        View v4;
        View v5;
        View v6;

        switch(playerTurn){
            case 1:
                v = (View)findViewById(R.id.p1);
                v1 = (View)findViewById(R.id.p1wbl);
                v2 = (View)findViewById(R.id.p1wye);
                v3 = (View)findViewById(R.id.p1wgr);
                v4 = (View)findViewById(R.id.p1wpu);
                v5 = (View)findViewById(R.id.p1wpi);
                v6 = (View)findViewById(R.id.p1wor);
                break;
            case 2:
                v = (View)findViewById(R.id.p2);
                v1 = (View)findViewById(R.id.p2wbl);
                v2 = (View)findViewById(R.id.p2wye);
                v3 = (View)findViewById(R.id.p2wgr);
                v4 = (View)findViewById(R.id.p2wpu);
                v5 = (View)findViewById(R.id.p2wpi);
                v6 = (View)findViewById(R.id.p2wor);
                break;
            case 3:
                v = (View)findViewById(R.id.p3);
                v1 = (View)findViewById(R.id.p3wbl);
                v2 = (View)findViewById(R.id.p3wye);
                v3 = (View)findViewById(R.id.p3wgr);
                v4 = (View)findViewById(R.id.p3wpu);
                v5 = (View)findViewById(R.id.p3wpi);
                v6 = (View)findViewById(R.id.p3wor);
                break;
            case 4:
                v = (View)findViewById(R.id.p4);
                v1 = (View)findViewById(R.id.p4wbl);
                v2 = (View)findViewById(R.id.p4wye);
                v3 = (View)findViewById(R.id.p4wgr);
                v4 = (View)findViewById(R.id.p4wpu);
                v5 = (View)findViewById(R.id.p4wpi);
                v6 = (View)findViewById(R.id.p4wor);
                break;
            case 5:
                v = (View)findViewById(R.id.p5);
                v1 = (View)findViewById(R.id.p5wbl);
                v2 = (View)findViewById(R.id.p5wye);
                v3 = (View)findViewById(R.id.p5wgr);
                v4 = (View)findViewById(R.id.p5wpu);
                v5 = (View)findViewById(R.id.p5wpi);
                v6 = (View)findViewById(R.id.p5wor);
                break;
            default:
                v = (View)findViewById(R.id.p6);
                v1 = (View)findViewById(R.id.p6wbl);
                v2 = (View)findViewById(R.id.p6wye);
                v3 = (View)findViewById(R.id.p6wgr);
                v4 = (View)findViewById(R.id.p6wpu);
                v5 = (View)findViewById(R.id.p6wpi);
                v6 = (View)findViewById(R.id.p6wor);
                break;
        }
        v.setX(x - v.getWidth()/2);
        v.setY(y - v.getHeight()/2);
        v1.setX(x - v.getWidth()/2);
        v1.setY(y - v.getHeight()/2);
        v2.setX(x - v.getWidth()/2);
        v2.setY(y - v.getHeight()/2);
        v3.setX(x - v.getWidth()/2);
        v3.setY(y - v.getHeight()/2);
        v4.setX(x - v.getWidth()/2);
        v4.setY(y - v.getHeight()/2);
        v5.setX(x - v.getWidth()/2);
        v5.setY(y - v.getHeight()/2);
        v6.setX(x - v.getWidth()/2);
        v6.setY(y - v.getHeight()/2);
    }

    public Globs.Cat detectColor(int r, int g, int b){
        int offset = 12;
        //PURPLE
        if(r >= (165 - offset) && r <= (165 + offset)){
            if(g >= (98 - offset) && g <= (98 + offset)){
                if(b >= (162 - offset) && b <= (162 + offset)){
                    return Globs.Cat.PURPLE;
                }
            }
        }
        //GREEN
        if(r >= (55 - offset) && r <= (55 + offset)){
            if(g >= (173 - offset) && g <= (173 + offset)){
                if(b >= (72 - offset) && b <= (72 + offset)){
                    return Globs.Cat.GREEN;
                }
            }
        }
        //BLUE
        if(r >= (12 - offset) && r <= (12 + offset)){
            if(g >= (163 - offset) && g <= (163 + offset)){
                if(b >= (209 - offset) && b <= (209 + offset)){
                    return Globs.Cat.BLUE;
                }
            }
        }
        //ORANGE
        if(r >= (235 - 2*offset) && r <= (235 + 2*offset)){
            if(g >= (144 - 2*offset) && g <= (144 + 2*offset)){
                if(b >= (48 - 2*offset) && b <= (48 + 2*offset)){
                    return Globs.Cat.ORANGE;
                }
            }
        }
        //YELLOW
        if(r >= (238 - offset) && r <= (238 + offset)){
            if(g >= (221 - offset) && g <= (221 + offset)){
                if(b >= (42 - offset) && b <= (42 + offset)){
                    return Globs.Cat.YELLOW;
                }
            }
        }
        //WHITE
        if(r >= (251 - offset) && r <= (251 + offset)){
            if(g >= (251 - offset) && g <= (251 + offset)){
                if(b >= (251 - offset) && b <= (251 + offset)){
                    return Globs.Cat.WHITE;
                }
            }
        }
        //BLACK
        if(r <= (0 + offset)){
            if(g <= (0 + offset)){
                if(b <= (0 + offset)){
                    return Globs.Cat.BLACK;
                }
            }
        }
        //PINK
        if(r >= (220 - 4*offset) && r <= (220 + 4*offset)){
            if(g >= (77 - 4*offset) && g <= (77 + 4*offset)){
                if(b >= (191 - 4*offset) && b <= (191 + 4*offset)){
                    return Globs.Cat.PINK;
                }
            }
        }
        return Globs.Cat.NONE;
    }

    public void hidewedges(){
        ImageView i = (ImageView)findViewById(R.id.p1wbl);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p1wgr);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p1wor);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p1wye);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p1wpu);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p1wpi);
        i.setVisibility(View.INVISIBLE);

        i = (ImageView)findViewById(R.id.p2wbl);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p2wgr);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p2wor);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p2wye);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p2wpu);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p2wpi);
        i.setVisibility(View.INVISIBLE);

        i = (ImageView)findViewById(R.id.p3wbl);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p3wgr);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p3wor);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p3wye);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p3wpu);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p3wpi);
        i.setVisibility(View.INVISIBLE);

        i = (ImageView)findViewById(R.id.p4wbl);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p4wgr);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p4wor);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p4wye);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p4wpu);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p4wpi);
        i.setVisibility(View.INVISIBLE);

        i = (ImageView)findViewById(R.id.p5wbl);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p5wgr);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p5wor);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p5wye);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p5wpu);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p5wpi);
        i.setVisibility(View.INVISIBLE);

        i = (ImageView)findViewById(R.id.p6wbl);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p6wgr);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p6wor);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p6wye);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p6wpu);
        i.setVisibility(View.INVISIBLE);
        i = (ImageView)findViewById(R.id.p6wpi);
        i.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!continueMusic) {
            MusicManager.pause();
        }
        try {
                Globs.blueFile.close();
                Globs.pinkFile.close();
                Globs.yellowFile.close();
                Globs.purpleFile.close();
                Globs.greenFile.close();
                Globs.orangeFile.close();
                SharedPreferences mPref = getSharedPreferences(Globs.qsetPath.substring(Globs.qsetPath.indexOf("/") + 1
                        , Globs.qsetPath.length() - 1), 0);
                SharedPreferences.Editor editor = mPref.edit();
                editor.putString("blue", Integer.toString(Globs.blueIdx));
                editor.putString("pink", Integer.toString(Globs.pinkIdx));
                editor.putString("yellow", Integer.toString(Globs.yellowIdx));
                editor.putString("purple", Integer.toString(Globs.purpleIdx));
                editor.putString("green", Integer.toString(Globs.greenIdx));
                editor.putString("orange", Integer.toString(Globs.orangeIdx));
                editor.commit();
                Globs.loadedQSet = false;
        }
        catch(IOException e){

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        continueMusic = false;
        MusicManager.start(this, MusicManager.MUSIC_GAME, true);
        if(Globs.killGame){
            Globs.killGame = false;
            onBackPressed();
        }
        if(rulesShown){
            rulesShown = false;
            return;
        }
        else {
            if (!Globs.correctbool && cardPlayed) {
                cardPlayed = false;
                playerTurn++;
                if (playerTurn > Globs.playerCnt)
                    playerTurn = 1;
            }
            else if(wedgeP){
                awardWedge();
            }
            else if(centerP){
                checkWinner();
            }
            wedgeP = false;
            centerP = false;
            if(!chickenDinner)
                startTurn();
            else
                endGame();
        }
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
