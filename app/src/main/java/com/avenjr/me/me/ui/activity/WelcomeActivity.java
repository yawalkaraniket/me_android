package com.avenjr.me.me.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.views.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.navigation_header)
    NavigationHeader navigationHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        navigationHeader.setUp(NavigationHeader.WELCOME_SKIP, this,
                getResources().getString(R.string.welcome));
    }
}
