package com.trivial_pursuit.trivialpursuit;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FilenameFilter;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);


        findViewById(R.id.imageView1).startAnimation(rotateAnimation);
    }

    /** Called when the user clicks the "Local Play" button */
    public void startLocal(View view) {
        Intent intent = new Intent(this, LocalPlayQSet.class);
        startActivity(intent);
        File file = new File("/assets/Questions/");
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
        for(String dir: directories){
            Button myButton = new Button(this);
            myButton.setText(dir);
            RelativeLayout ll = (RelativeLayout)findViewById(R.id.qset);
            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(myButton, lp);
        }
    }

    public void startQuick (View view) {
        Intent intent = new Intent(this, Quick.class);
        startActivity(intent);
    }

    public void startSettings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
