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

    static String qsetPath;
    static int playerCnt;

    static boolean isQuick;

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
}
