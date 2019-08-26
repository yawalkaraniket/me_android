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
        clearAllNavigationSelection();
        initializePager();
    }

    private void initializePager() {
        navigationViewPager.setOffscreenPageLimit(3);
        navigationViewPager.setAdapter(new NavigationPagerAdapter(getSupportFragmentManager(), this));
    }

    @OnClick(R.id.navigation_home_layout)
    public void navigationHome() {
        clearAllNavigationSelection();
        homeNavigation.setImageDrawable(getResources().getDrawable(R.mipmap.ic_home_active_round));
        navigationViewPager.setCurrentItem(0, true);
    }

    @OnClick(R.id.navigation_friends_layout)
    public void navigationFriends() {
        clearAllNavigationSelection();
        friendsNavigation.setImageDrawable(getResources().getDrawable(R.mipmap.ic_friends_active_round));
        navigationViewPager.setCurrentItem(1, true);
    }

    @OnClick(R.id.navigation_profile_layout)
    public void navigationProfile() {
        clearAllNavigationSelection();
        profileNavigation.setImageDrawable(getResources().getDrawable(R.mipmap.ic_profile_active_round));
        navigationViewPager.setCurrentItem(2, true);
    }

    private void clearAllNavigationSelection() {
        homeNavigation.setImageDrawable(getResources().getDrawable(R.mipmap.ic_hone_non_active_round));
        friendsNavigation.setImageDrawable(getResources().getDrawable(R.mipmap.ic_friends_non_active_round));
        profileNavigation.setImageDrawable(getResources().getDrawable(R.mipmap.ic_profile_non_active_round));
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
