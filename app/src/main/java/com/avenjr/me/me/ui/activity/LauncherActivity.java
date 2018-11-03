package com.avenjr.me.me.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.Utils.AnimationsUtil;
import com.avenjr.me.me.ui.animation.FlipAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.avenjr.me.me.ui.Utils.UiUtil.getScreenWidthInPixel;

public class LauncherActivity extends AppCompatActivity {

    @BindView(R.id.circle_view)
    RelativeLayout circleLayout;

    @BindView(R.id.launcher_parent)
    RelativeLayout parentView;

    @BindView(R.id.profile_image_view)
    ImageView profileImageView;

    @BindView(R.id.logo_parent)
    LinearLayout logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);

        logo.setVisibility(View.GONE);
        setCircleLayout();
        profileImageView.getLayoutParams().height = getScreenWidthInPixel(getApplicationContext())/2;
        profileImageView.getLayoutParams().height =  getScreenWidthInPixel(getApplicationContext())/2;
        flipCard();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.godown, R.anim.goup);
            }
        },2000L);
    }

    private void setCircleLayout() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.height = getScreenWidthInPixel(getApplicationContext());
        params.width = getScreenWidthInPixel(getApplicationContext());
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        circleLayout.setLayoutParams(params);
    }

    private void flipCard()
    {
        FlipAnimation flipAnimation = new FlipAnimation(profileImageView, logo);
        profileImageView.startAnimation(flipAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logo.setAnimation(AnimationsUtil.loadFadeInAnimation(getApplicationContext()));

            }
        }, 1000);
    }
}
