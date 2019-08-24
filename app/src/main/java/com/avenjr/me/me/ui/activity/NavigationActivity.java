package com.avenjr.me.me.ui.activity;

import android.os.Bundle;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.Utils.PermissionUtils;

import butterknife.ButterKnife;

public class NavigationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
