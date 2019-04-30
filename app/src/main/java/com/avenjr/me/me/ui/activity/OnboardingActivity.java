package com.avenjr.me.me.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.CardView;

import com.avenjr.me.me.R;
import com.avenjr.me.me.db.AppPreferences;
import com.avenjr.me.me.ui.fragments.EnterEmailFragment;
import com.avenjr.me.me.ui.fragments.EnterPasswordFragment;
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

    AppPreferences preferences;
    int backStackCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        addFragment(this, EnterEmailFragment.class);

        // Shared Preference
        preferences = new AppPreferences(getApplicationContext());

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
        }
    }

    @OnClick(R.id.reg_back_cv_id)
    public void navigateBack() {
        backStackCount = this.getSupportFragmentManager().getBackStackEntryCount();
        if (backStackCount == 1) {
            return;
        }
        this.getSupportFragmentManager().popBackStack();
    }
}
