package com.trivial_pursuit.trivialpursuit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(4000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);


        findViewById(R.id.imageView1).startAnimation(rotateAnimation);
    }

    /** Called when the user clicks the "Local Play" button */
    public void startLocal(View view) {
        Intent intent = new Intent(this, LocalPlayQSet.class);
        startActivity(intent);
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
