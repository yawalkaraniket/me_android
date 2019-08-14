package com.avenjr.me.me.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.avenjr.me.me.R;

import static com.avenjr.me.me.ui.Utils.UiUtil.dp;

public class CustomPagerIndicator extends LinearLayout {

    public CustomPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
    }

    public void show(int count, int selection) {
        removeAllViews();
        for (int i = 0; i < count; i++) {
            ImageView dot = new ImageView(getContext());
            if (i == selection) {
                dot.setLayoutParams(new LinearLayout.LayoutParams(dp(15),dp(15)));
                dot.setPadding(dp(3),dp(3),dp(3),dp(3));
                dot.setImageDrawable(getResources().getDrawable(R.drawable.dark_app_color_dot));
            } else {
                dot.setLayoutParams(new LinearLayout.LayoutParams(dp(15),dp(15)));
                dot.setPadding(dp(5),dp(5),dp(5),dp(5));
                dot.setImageDrawable(getResources().getDrawable(R.drawable.app_color_dot));
            }
            addView(dot);
        }
    }
}
