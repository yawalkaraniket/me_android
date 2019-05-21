package com.avenjr.me.me.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.widget.ProgressBar;

import com.avenjr.me.me.R;
import com.avenjr.me.me.db.AppPreferences;
import com.avenjr.me.me.ui.fragments.EnterEmailFragment;
import com.avenjr.me.me.ui.fragments.EnterPasswordFragment;
import com.avenjr.me.me.ui.fragments.VerifyNumberFragment;
import com.avenjr.me.me.ui.views.NavigationHeader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.avenjr.me.me.ui.views.NavigationHeader.BACK_HEADER;

public class OnboardingActivity extends BaseActivity {

    @BindView(R.id.navigation_header)
    NavigationHeader navigationHeader;

    @BindView(R.id.reg_back_cv_id)
    CardView navigateBack;

    @BindView(R.id.reg_next_cv_id)
    CardView next;

    @BindView(R.id.onboarding_progress)
    ProgressBar onboardingProgress;

    int muxProgress = 100;
    int numberOfFragment = 3;

    AppPreferences preferences;
    int backStackCount, currentProgress = muxProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        addFragment(this, EnterEmailFragment.class);

        // Shared Preference
        preferences = new AppPreferences(getApplicationContext());
        onboardingProgress.setMax(100);

        navigationHeader.setUp(BACK_HEADER, this, "Registration");
    }

    @OnClick(R.id.reg_next_cv_id)
    public void navigateNextScreen() {
        backStackCount = this.getSupportFragmentManager().getBackStackEntryCount();
        switch (backStackCount) {
            case 1:
                replaceFragment(this, EnterEmailFragment.class);
                break;
            case 2:
                replaceFragment(this, EnterPasswordFragment.class);
                break;
            case 3:
                replaceFragment(this, VerifyNumberFragment.class);
                break;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentProgress = (muxProgress/numberOfFragment) * backStackCount;
                onboardingProgress.setProgress(currentProgress);
            }
        }, 200);
    }

    @OnClick(R.id.reg_back_cv_id)
    public void navigateBack() {
        backStackCount = this.getSupportFragmentManager().getBackStackEntryCount();
        if (backStackCount <= 2) {
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentProgress = currentProgress - (muxProgress/numberOfFragment);
                onboardingProgress.setProgress(currentProgress);
            }
        }, 200);
        this.getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
