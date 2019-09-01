package com.avenjr.me.me.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.Utils.UiUtil;
import com.avenjr.me.me.ui.adapters.FriendsNotificationsAdapter;
import com.google.android.material.tabs.TabLayout;
import com.wunderlist.slidinglayer.SlidingLayer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileSlider extends SlidingLayer {

    @BindView(R.id.friends_tab)
    TabLayout tabLayout;

    @BindView(R.id.friends_tab_pager)
    ViewPager viewPager;

    @BindView(R.id.slilder_header)
    RelativeLayout header;

    private Context context;
    private FragmentManager fragmentManager;

    public ProfileSlider(Context context, FragmentManager fragmentManager) {
        super(context);

        this.context = context;
        this.fragmentManager = fragmentManager;
        init();
    }

    public ProfileSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProfileSlider(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // Initialize the drawer.
    private void init() {
        setOffsetDistance(UiUtil.dp(40));

        View view = LayoutInflater.from(context).inflate(R.layout.profile_slider_layout, null);
        addView(view);
        ButterKnife.bind(this);
        setStickTo(STICK_TO_BOTTOM);

        initializePager();

        setOnInteractListener(new SliderListener() {
            @Override
            public void onOpen() {
                header.setVisibility(GONE);
                tabLayout.setVisibility(VISIBLE);
            }

            @Override
            public void onClose() {
                header.setVisibility(VISIBLE);
                tabLayout.setVisibility(GONE);
            }
        });

    }

    private void initializePager() {
        addTabOptions();

        FriendsNotificationsAdapter notificationsAdapter = new FriendsNotificationsAdapter(context, fragmentManager, tabLayout.getTabCount());
        viewPager.setAdapter(notificationsAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void addTabOptions() {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_people_foreground));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_message_foreground));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_notifications_foreground));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

}
