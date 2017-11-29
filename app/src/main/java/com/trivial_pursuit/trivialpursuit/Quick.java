package com.trivial_pursuit.trivialpursuit;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Stack;

import static com.trivial_pursuit.trivialpursuit.Quick.Category.BLUE;
import static com.trivial_pursuit.trivialpursuit.Quick.Category.GREEN;
import static com.trivial_pursuit.trivialpursuit.Quick.Category.ORANGE;
import static com.trivial_pursuit.trivialpursuit.Quick.Category.PINK;
import static com.trivial_pursuit.trivialpursuit.Quick.Category.PURPLE;
import static com.trivial_pursuit.trivialpursuit.Quick.Category.RANDOM;
import static com.trivial_pursuit.trivialpursuit.Quick.Category.YELLOW;

public class Quick extends AppCompatActivity {
    public enum Category{
        RANDOM, BLUE, PINK, YELLOW, PURPLE, GREEN, ORANGE
    }
    public Category cat;
    public boolean continueMusic;

    public int bluei;
    public int pinki;
    public int yellowi;
    public int purplei;
    public int greeni;
    public int orangei;

    public boolean isopened;
    public int tabloc;

    public String Ques;
    public String Ans;
    public Category Qcat;

    public class qaset{
        public String Q;
        public String A;
        public Category C;

        public qaset(String a, String b, Category d){
            Q = a;
            A = b;
            C = d;
        }
    }
    public class SizedStack<T> extends Stack<T> {
        private int maxSize;

        public SizedStack(int size) {
            super();
            this.maxSize = size;
        }

        @Override
        public T push(T object) {
            //If the stack is too big, remove elements until it's the right size.
            while (this.size() >= maxSize) {
                this.remove(0);
            }
            return super.push(object);
        }
    }
    SizedStack<qaset> qstack;
    SizedStack<qaset> popstack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        continueMusic = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick);
        View v = findViewById(R.id.dim);
        v.setVisibility(View.GONE);
        v = findViewById(R.id.highlight);
        v.setVisibility(View.GONE);
        v = findViewById(R.id.catb);
        v.setEnabled(false);
        v.setVisibility(View.GONE);
        v = findViewById(R.id.catp);
        v.setEnabled(false);
        v.setVisibility(View.GONE);
        v = findViewById(R.id.caty);
        v.setEnabled(false);
        v.setVisibility(View.GONE);
        v = findViewById(R.id.catpu);
        v.setEnabled(false);
        v.setVisibility(View.GONE);
        v = findViewById(R.id.catg);
        v.setEnabled(false);
        v.setVisibility(View.GONE);
        v = findViewById(R.id.cato);
        v.setEnabled(false);
        v.setVisibility(View.GONE);
        v = findViewById(R.id.catr);
        v.setEnabled(false);
        v.setVisibility(View.GONE);
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
        isopened = false;
        tabloc = 0;
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
        Qcat = null;
        qstack = new SizedStack<qaset>(15);
        popstack = new SizedStack<qaset>(15);
        newCard();
        final View imageButton = findViewById(R.id.next);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Globs.sound.cardsound();
                newCard();
            }
        });
        final View imageButton2 = findViewById(R.id.prev);
        imageButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Globs.sound.cardsound();
                prevCard();
            }
        });
        final View imageButton4 = findViewById(R.id.dim);
        imageButton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isopened){
                    View v = findViewById(R.id.category);
                    ObjectAnimator mover = ObjectAnimator.ofFloat(v, "translationX", 480, 0);
                    mover.start();
                    mover.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            View v = findViewById(R.id.backbutton2);
                            v.setVisibility(View.VISIBLE);
                            v.setEnabled(true);
                            v = findViewById(R.id.prev);
                            v.setVisibility(View.VISIBLE);
                            v.setEnabled(true);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    isopened = false;

                    v = findViewById(R.id.dim);
                    v.setVisibility(View.GONE);
                    //v = findViewById(R.id.category);
                    //v.setEnabled(false);
                    //v.setVisibility(View.GONE);
                    v = findViewById(R.id.card);
                    v.setEnabled(true);
                    v = findViewById(R.id.next);
                    v.setEnabled(true);
                    v = findViewById(R.id.backbutton);
                    v.setEnabled(true);

                    v = findViewById(R.id.highlight);
                    v.setVisibility(View.GONE);
                    v = findViewById(R.id.catb);
                    v.setEnabled(false);
                    v.setVisibility(View.GONE);
                    v = findViewById(R.id.catp);
                    v.setEnabled(false);
                    v.setVisibility(View.GONE);
                    v = findViewById(R.id.caty);
                    v.setEnabled(false);
                    v.setVisibility(View.GONE);
                    v = findViewById(R.id.catpu);
                    v.setEnabled(false);
                    v.setVisibility(View.GONE);
                    v = findViewById(R.id.catg);
                    v.setEnabled(false);
                    v.setVisibility(View.GONE);
                    v = findViewById(R.id.cato);
                    v.setEnabled(false);
                    v.setVisibility(View.GONE);
                    v = findViewById(R.id.catr);
                    v.setEnabled(false);
                    v.setVisibility(View.GONE);

                    ImageView iv = (ImageView)findViewById(R.id.category);
                    iv.setImageResource(R.drawable.catnew2);
                }
            }
        });
        final View imageButton3 = findViewById(R.id.card);
        imageButton3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                TextView tv1 = null;
                tv1 = (TextView) findViewById(R.id.questionTxt);
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
        final View catB = findViewById(R.id.catb);
        catB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MoveHighlight(BLUE);
                CatChange(BLUE);
            }
        });
        final View catP = findViewById(R.id.catp);
        catP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MoveHighlight(PINK);
                CatChange(PINK);
            }
        });
        final View catY = findViewById(R.id.caty);
        catY.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MoveHighlight(YELLOW);
                CatChange(YELLOW);
            }
        });
        final View catPu = findViewById(R.id.catpu);
        catPu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MoveHighlight(PURPLE);
                CatChange(PURPLE);
            }
        });
        final View catG = findViewById(R.id.catg);
        catG.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MoveHighlight(GREEN);
                CatChange(GREEN);
            }
        });
        final View catO = findViewById(R.id.cato);
        catO.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MoveHighlight(ORANGE);
                CatChange(ORANGE);
            }
        });
        final View catR = findViewById(R.id.catr);
        catR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                MoveHighlight(RANDOM);
                CatChange(RANDOM);
            }
        });
        final View imageButton5 = findViewById(R.id.category);
        imageButton5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ImageView but = (ImageView)findViewById(R.id.catb);
                if(isopened) {
                    if (motionEvent.getX() > but.getRight()) {
                        tabloc = 1;
                    }
                }
                return false;
            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isopened) {
                    if(tabloc == 1){
                        tabloc = 0;
                        View v = findViewById(R.id.category);
                        ObjectAnimator mover = ObjectAnimator.ofFloat(v, "translationX", 480, 0);
                        mover.start();
                        mover.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animator) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animator) {
                                View v = findViewById(R.id.backbutton2);
                                v.setVisibility(View.VISIBLE);
                                v.setEnabled(true);
                                v = findViewById(R.id.prev);
                                v.setVisibility(View.VISIBLE);
                                v.setEnabled(true);
                            }

                            @Override
                            public void onAnimationCancel(Animator animator) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animator) {

                            }
                        });
                        isopened = false;

                        v = findViewById(R.id.dim);
                        v.setVisibility(View.GONE);
                        //v = findViewById(R.id.category);
                        //v.setEnabled(false);
                        //v.setVisibility(View.GONE);
                        v = findViewById(R.id.card);
                        v.setEnabled(true);
                        v = findViewById(R.id.next);
                        v.setEnabled(true);
                        v = findViewById(R.id.backbutton);
                        v.setEnabled(true);

                        v = findViewById(R.id.highlight);
                        v.setVisibility(View.GONE);
                        v = findViewById(R.id.catb);
                        v.setEnabled(false);
                        v.setVisibility(View.GONE);
                        v = findViewById(R.id.catp);
                        v.setEnabled(false);
                        v.setVisibility(View.GONE);
                        v = findViewById(R.id.caty);
                        v.setEnabled(false);
                        v.setVisibility(View.GONE);
                        v = findViewById(R.id.catpu);
                        v.setEnabled(false);
                        v.setVisibility(View.GONE);
                        v = findViewById(R.id.catg);
                        v.setEnabled(false);
                        v.setVisibility(View.GONE);
                        v = findViewById(R.id.cato);
                        v.setEnabled(false);
                        v.setVisibility(View.GONE);
                        v = findViewById(R.id.catr);
                        v.setEnabled(false);
                        v.setVisibility(View.GONE);

                        ImageView iv = (ImageView)findViewById(R.id.category);
                        iv.setImageResource(R.drawable.catnew2);
                    }
                }
                else{
                    isopened = true;
                    View v = findViewById(R.id.dim);
                    v.setVisibility(View.VISIBLE);
                    //v = findViewById(R.id.category);
                    //v.setEnabled(true);
                    //v.setVisibility(View.VISIBLE);
                    v = findViewById(R.id.card);
                    v.setEnabled(false);
                    v = findViewById(R.id.prev);
                    v.setVisibility(View.GONE);
                    v.setEnabled(false);
                    v = findViewById(R.id.next);
                    v.setEnabled(false);
                    v = findViewById(R.id.backbutton);
                    v.setEnabled(false);
                    v = findViewById(R.id.backbutton2);
                    v.setVisibility(View.GONE);
                    v.setEnabled(false);

                    v = findViewById(R.id.category);
                    ObjectAnimator mover = ObjectAnimator.ofFloat(v, "translationX", 0, 480);
                    mover.start();
                    mover.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            View v = findViewById(R.id.highlight);
                            v.setVisibility(View.VISIBLE);

                            ImageView iv = (ImageView)findViewById(R.id.category);
                            iv.setImageResource(R.drawable.catnew);

                            v = findViewById(R.id.catb);
                            v.setEnabled(true);
                            v.setVisibility(View.VISIBLE);
                            v = findViewById(R.id.catp);
                            v.setEnabled(true);
                            v.setVisibility(View.VISIBLE);
                            v = findViewById(R.id.caty);
                            v.setEnabled(true);
                            v.setVisibility(View.VISIBLE);
                            v = findViewById(R.id.catpu);
                            v.setEnabled(true);
                            v.setVisibility(View.VISIBLE);
                            v = findViewById(R.id.catg);
                            v.setEnabled(true);
                            v.setVisibility(View.VISIBLE);
                            v = findViewById(R.id.cato);
                            v.setEnabled(true);
                            v.setVisibility(View.VISIBLE);
                            v = findViewById(R.id.catr);
                            v.setEnabled(true);
                            v.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }
            }
        });
    }

    public void pushToLineNum(int line, BufferedReader f){
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

    public void MoveHighlight(Category c){
        if(cat == c){
            return;
        }
        ImageView y;
        ImageView s = (ImageView) findViewById(R.id.highlight);
        switch(c){
            case BLUE:
                y = (ImageView) findViewById(R.id.catb);
                s.setY(y.getY());
                break;
            case PINK:
                y = (ImageView) findViewById(R.id.catp);
                s.setY(y.getY());
                break;
            case YELLOW:
                y = (ImageView) findViewById(R.id.caty);
                s.setY(y.getY());
                break;
            case PURPLE:
                y = (ImageView) findViewById(R.id.catpu);
                s.setY(y.getY());
                break;
            case GREEN:
                y = (ImageView) findViewById(R.id.catg);
                s.setY(y.getY());
                break;
            case ORANGE:
                y = (ImageView) findViewById(R.id.cato);
                s.setY(y.getY());
                break;
            case RANDOM:
                y = (ImageView) findViewById(R.id.catr);
                s.setY(y.getY());
                break;
        }
    }

    public void CatChange(Category c){
        if(cat == c){
            return;
        }
        cat = c;
        Qcat = null;
        Ques = "";
        Ans = "";
        qstack = new SizedStack<qaset>(15);
        popstack = new SizedStack<qaset>(15);
        newCard();
    }

    public void prevCard(){
        if(qstack.size() == 0){
            return;
        }
        popstack.push(new qaset(Ques, Ans, Qcat));
        qaset prev = qstack.pop();
        Ques = prev.Q;
        Ans = prev.A;
        Qcat = prev.C;
        TextView tv1 = null;
        tv1 = (TextView) findViewById(R.id.questionTxt);
        tv1.setText(Ques);
        switch(prev.C){
            case BLUE:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardblue);
                break;
            case PINK:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardpink);
                break;
            case YELLOW:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardyellow);
                break;
            case PURPLE:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardpurple);
                break;
            case GREEN:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardgreen);
                break;
            case ORANGE:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardorange);
                break;
        }
    }

    public void popCard(){
        qaset prev = popstack.pop();
        Ques = prev.Q;
        Ans = prev.A;
        Qcat = prev.C;
        TextView tv1 = null;
        tv1 = (TextView) findViewById(R.id.questionTxt);
        tv1.setText(Ques);
        switch(prev.C){
            case BLUE:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardblue);
                break;
            case PINK:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardpink);
                break;
            case YELLOW:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardyellow);
                break;
            case PURPLE:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardpurple);
                break;
            case GREEN:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardgreen);
                break;
            case ORANGE:
                ((ImageView)findViewById(R.id.card)).setImageResource(R.drawable.trivialcardorange);
                break;
        }
    }

    public void newCard(){
        if(!Ques.equals("")){
            qstack.push(new qaset(Ques, Ans, Qcat));
        }
        if(popstack.size() != 0){
            popCard();
            return;
        }
        Random r = new Random();
        TextView tv1 = null;
        if(cat != RANDOM){
            Qcat = cat;
        }
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
                            Qcat = BLUE;
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
                            Qcat = PINK;
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
                            Qcat = YELLOW;
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
                            Qcat = PURPLE;
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
                            Qcat = GREEN;
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
                            Qcat = ORANGE;
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
    public void onDestroy() {
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
        MusicManager.start(this, MusicManager.MUSIC_QUICK, true);
    }

    public void Backbutt (View view) {
        Globs.sound.playtapsound();
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