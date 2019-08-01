package com.avenjr.me.me.ui.views;

import android.app.Activity;
import android.content.Context;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.activity.NavigationActivity;
import com.avenjr.me.me.ui.activity.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationHeader extends RelativeLayout {

    String type;
    public static final String BACK_HEADER = "Back Header";
    public static final String HOME_SCREEN = "Home Screen";
    public static final String WELCOME_SKIP = "Navigation Header Skip";

    @BindView(R.id.nv_back_btn)
    CardView navigationBack;

    @BindView(R.id.nv_next_button)
    CardView navigationNext;

    @BindView(R.id.header_title)
    CustomTextView headerTitle;

    @BindView(R.id.skip_button)
    CustomTextView skipButton;

    Activity activity;

    public NavigationHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        type = HOME_SCREEN;
    }

    public void setUp(String type, final Activity activity, String title) {
        this.type = type;
        this.activity = activity;
        setUpUI(title);

        navigationBack.setOnClickListener(view -> activity.finish());
    }

    private void setUpUI(String title) {
        removeAllViews();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.navigation_header, null);
        TextView headerTitle = view.findViewById(R.id.header_title);
        ButterKnife.bind(this, view);

        switch (type) {
            case BACK_HEADER:
                navigationBack.setVisibility(VISIBLE);
                headerTitle.setVisibility(VISIBLE);
                headerTitle.setText(title);
                break;
            case HOME_SCREEN:
                navigationBack.setVisibility(GONE);
                navigationNext.setVisibility(GONE);
                break;
            case WELCOME_SKIP:
                skipButton.setVisibility(VISIBLE);
                skipButton.setOnClickListener(v -> {
                    activity.finish();
                    Intent intent = new Intent(activity, NavigationActivity.class);
                    activity.startActivity(intent);
                });
                headerTitle.setVisibility(VISIBLE);
                headerTitle.setText(title);
                break;
        }
        addView(view);
    }
}
