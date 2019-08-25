package com.avenjr.me.me.ui.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.Utils.PermissionUtils;
import com.avenjr.me.me.ui.adapters.NavigationPagerAdapter;
import com.avenjr.me.me.ui.adapters.NavigationViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationActivity extends BaseActivity {

    @BindView(R.id.navigation_profile_layout)
    CircleImageView profileNavigation;

    @BindView(R.id.navigation_pager)
    NavigationViewPager navigationViewPager;

    @BindView(R.id.navigation_friends_layout)
    CircleImageView friendsNavigation;

    @BindView(R.id.navigation_home_layout)
    CircleImageView homeNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        initializePager();

    }

    private void initializePager() {
        navigationViewPager.setOffscreenPageLimit(3);
        navigationViewPager.setAdapter(new NavigationPagerAdapter(getSupportFragmentManager(), this));
    }

    @OnClick(R.id.navigation_home_layout)
    public void navigationHome() {
        navigationViewPager.setCurrentItem(0, true);
    }

    @OnClick(R.id.navigation_friends_layout)
    public void navigationFriends() {
        navigationViewPager.setCurrentItem(1, true);
    }

    @OnClick(R.id.navigation_profile_layout)
    public void navigationProfile() {
        navigationViewPager.setCurrentItem(2, true);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
