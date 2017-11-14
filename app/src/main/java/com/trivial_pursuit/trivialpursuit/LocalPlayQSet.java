package com.trivial_pursuit.trivialpursuit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LocalPlayQSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_play_qset);
    }

    protected void onClickM(View v){
        Globs.qsetPath = "Questions/Master/";
        if(Globs.isQuick){
            Intent intent = new Intent(this, Quick.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, LocalPlayNumP.class);
            startActivity(intent);
        }
    }

    protected void onClickC(View v){
        Globs.qsetPath = "Questions/Custom/";
        if(Globs.isQuick){
            Intent intent = new Intent(this, Quick.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, LocalPlayNumP.class);
            startActivity(intent);
        }
    }
}
