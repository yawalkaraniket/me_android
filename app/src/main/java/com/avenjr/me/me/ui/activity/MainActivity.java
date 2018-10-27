package com.avenjr.me.me.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.avenjr.me.me.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.godown,R.anim.goup);
    }
}
