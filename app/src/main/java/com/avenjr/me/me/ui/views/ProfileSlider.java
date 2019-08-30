package com.avenjr.me.me.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.Utils.UiUtil;
import com.wunderlist.slidinglayer.SlidingLayer;

import butterknife.ButterKnife;

public class ProfileSlider extends SlidingLayer {

    private Context context;

    public ProfileSlider(Context context) {
        super(context);

        this.context = context;
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
        setOffsetDistance(UiUtil.dp(50));

        View view = LayoutInflater.from(context).inflate(R.layout.profile_slider_layout, null);
        addView(view);
        ButterKnife.bind(this);
        setStickTo(STICK_TO_BOTTOM);

    }
}
