package com.trivial_pursuit.trivialpursuit;

import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Created by grant on 11/13/2017.
 */

public class Globs {
    public enum Colors{
        BLUE, PINK, YELLOW, PURPLE, GREEN, ORANGE
    }

    public enum Cat{
        BLUE, PINK, YELLOW, PURPLE, GREEN, ORANGE, WHITE, BLACK, NONE
    }

    static String qsetPath;
    static int playerCnt;

    static boolean isQuick;
    static float vol;
    static int timerval;
    static boolean timeron;
    static float volFX;

    static SoundPlayer sound;

    static boolean loadedQSet;

    static BufferedReader blueFile;
    static BufferedReader pinkFile;
    static BufferedReader yellowFile;
    static BufferedReader purpleFile;
    static BufferedReader greenFile;
    static BufferedReader orangeFile;

    static int blueMax;
    static int pinkMax;
    static int yellowMax;
    static int purpleMax;
    static int greenMax;
    static int orangeMax;

    static int blueIdx;
    static int pinkIdx;
    static int yellowIdx;
    static int purpleIdx;
    static int greenIdx;
    static int orangeIdx;

    static Colors p1;
    static Colors p2;
    static Colors p3;
    static Colors p4;
    static Colors p5;
    static Colors p6;

    static BoardNode p1Loc;
    static BoardNode p2Loc;
    static BoardNode p3Loc;
    static BoardNode p4Loc;
    static BoardNode p5Loc;
    static BoardNode p6Loc;

    static int newRoll;
    static boolean gameOn;

    static boolean killGame;

    static boolean correctbool;
    static Cat newQ;

    static int teamDraw;
    static boolean backToMain = false;
}
