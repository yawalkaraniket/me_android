package com.avenjr.me.me.ui.activity;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
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
    int numberOfFragment = 2;

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
                replaceFragment(this, EnterPasswordFragment.class);
                break;
            case 2:
                replaceFragment(this, VerifyNumberFragment.class);
                break;
        }

        currentProgress = (muxProgress/numberOfFragment) * backStackCount;
        onboardingProgress.setProgress(currentProgress);
    }

    @OnClick(R.id.reg_back_cv_id)
    public void navigateBack() {
        backStackCount = this.getSupportFragmentManager().getBackStackEntryCount();
        if (backStackCount <= 2) {
            return;
        }
        this.getSupportFragmentManager().popBackStack();

        currentProgress = currentProgress - (muxProgress/numberOfFragment);
        onboardingProgress.setProgress(currentProgress);
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
        clearFragmentManager();
        super.onSaveInstanceState(outState);
    }
}
