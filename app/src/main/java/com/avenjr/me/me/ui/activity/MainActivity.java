package com.avenjr.me.me.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.Utils.UiUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.avenjr.me.me.ui.Utils.AnimationsUtil.animateOnScreen;
import static com.avenjr.me.me.ui.Utils.UiUtil.getScreenWidthInPixel;
import static com.avenjr.me.me.ui.animation.MoveAnimation.moveFromTop;

public class MainActivity extends BaseActivity{

    @BindView(R.id.top_layout)
    View signInOptions;

    @BindView(R.id.sign_in_button)
    RelativeLayout signIn;

    @BindView(R.id.register_button)
    RelativeLayout register;

    @BindView(R.id.already_register_sign_in)
    TextView alreadySignIn;

    @BindView(R.id.sign_in_google)
    CircleImageView signInWithGoogle;

    @BindView(R.id.main_activity_parent)
    View parentLayout;

    int orientation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orientation = getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        } else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_main_land);
        }

        ButterKnife.bind(this);

        setUpProgress(parentLayout);

        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.height = getScreenWidthInPixel(getApplicationContext()) / 4;
            params.width = getScreenWidthInPixel(getApplicationContext());
            params.topMargin = - (getScreenWidthInPixel(getApplicationContext()) / 4) * 2;
            signInOptions.setLayoutParams(params);

            moveFromTop(signInOptions, UiUtil.getScreenHeightInDp(getApplicationContext()));
        } else if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            signInOptions.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.sign_in_button)
    public void signIn() {
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            moveFromTop(signInOptions, signInOptions.getHeight() * 2);
        } else if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(signInOptions.getVisibility() == View.GONE) {
                signInOptions.setVisibility(View.VISIBLE);
                animateOnScreen(signInOptions, getApplicationContext());
            }
        }
    }

    @OnClick(R.id.register_button)
    public void register() {
        Intent intent = new Intent(MainActivity.this, OnboardingActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.already_register_sign_in)
    public void setAlreadySignIn() {
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.sign_in_google)
    public void googleSingIn() {

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            moveFromTop(signInOptions, 350f);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
