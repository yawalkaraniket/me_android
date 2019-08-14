package com.avenjr.me.me.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.adapters.WelcomePagerAdapter;
import com.avenjr.me.me.ui.views.CustomPagerIndicator;
import com.avenjr.me.me.ui.views.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.navigation_header)
    NavigationHeader navigationHeader;

    @BindView(R.id.welcome_pager)
    ViewPager welcomePager;

    @Nullable
    @BindView(R.id.pager_indicator)
    CustomPagerIndicator pagerIndicator;

    @BindView(R.id.welcome__next)
    CardView nextButton;

    @BindView(R.id.welcome__back)
    CardView backButton;

    int lastSelectedScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        navigationHeader.setUp(NavigationHeader.WELCOME_SKIP, this,
                getResources().getString(R.string.my_intrest));

        setUpPager();
    }

    @OnClick(R.id.welcome__back)
    public void backButtonClick() {
        welcomePager.setCurrentItem(lastSelectedScreen - 1, true);
    }

    @OnClick(R.id.welcome__next)
    public void nextButtonClick() {
        welcomePager.setCurrentItem(lastSelectedScreen + 1, true);
    }

    private void setUpPager() {
        welcomePager.setOffscreenPageLimit(2);
        welcomePager.setAdapter(new WelcomePagerAdapter(getSupportFragmentManager(), this));
        pagerIndicator.show(2, 0);

        welcomePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lastSelectedScreen = position;
                pagerIndicator.show(2, position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
