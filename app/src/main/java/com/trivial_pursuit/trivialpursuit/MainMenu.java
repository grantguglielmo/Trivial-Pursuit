package com.trivial_pursuit.trivialpursuit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    /** Called when the user clicks the "Local Play" button */
    public void startLocal(View view) {
        Intent intent = new Intent(this, LocalPlayQSet.class);
        startActivity(intent);
    }
}
