package com.avenjr.me.me.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.avenjr.me.me.R;
import com.avenjr.me.me.db.AppPreferences;
import com.avenjr.me.me.modle.User;
import com.avenjr.me.me.ui.adapters.NavigationViewPager;
import com.avenjr.me.me.ui.adapters.OnboardingPagerAdapter;
import com.avenjr.me.me.ui.views.NavigationHeader;
import com.rd.PageIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.avenjr.me.me.ui.views.NavigationHeader.BACK_HEADER;

public class OnboardingActivity extends BaseActivity {

    @BindView(R.id.navigation_header)
    NavigationHeader navigationHeader;

    @BindView(R.id.welcome_pager)
    NavigationViewPager welcomePager;

    @BindView(R.id.welcome_pager_indicator)
    PageIndicatorView welcomePagerIndicator;

    @BindView(R.id.reg_back_cv_id)
    CardView navigateBack;

    @BindView(R.id.reg_next_cv_id)
    CardView next;

    AppPreferences preferences;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        // Shared Preference
        preferences = new AppPreferences(getApplicationContext());
        navigationHeader.setUp(BACK_HEADER, this, "Registration");
        user = User.getInstance();

        onboardingPagerSetUp();
    }

    @OnClick(R.id.reg_next_cv_id)
    public void next() {
        int currentSelectedPager = welcomePager.getCurrentItem();
        switch (currentSelectedPager) {
            case 0:
                if (!user.getId().isEmpty()) {
                    navigateBack.setVisibility(View.VISIBLE);
                    welcomePager.setCurrentItem(1, true);
                }
                break;
            case 1:
                if (!user.getPassword().isEmpty()) {
                    welcomePager.setCurrentItem(2, true);
                    next.setVisibility(View.GONE);
                }
                break;
        }
    }

    @OnClick(R.id.reg_back_cv_id)
    public void back() {
        welcomePager.setCurrentItem(welcomePager.getCurrentItem() - 1);
        if (welcomePager.getCurrentItem() == 0) {
            navigateBack.setVisibility(View.GONE);
        }
        next.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Commenting this method as we are not using separate fragment.
        // clearFragmentManager();
        super.onSaveInstanceState(outState);
    }

    private void onboardingPagerSetUp() {
        welcomePager.setOffscreenPageLimit(3);
        welcomePager.setAdapter(new OnboardingPagerAdapter(this, getSupportFragmentManager()));
        welcomePagerIndicator.setViewPager(welcomePager);
    }

}
