package com.avenjr.me.me.ui.views;

import android.app.Activity;
import android.content.Context;
import androidx.cardview.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avenjr.me.me.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationHeader extends RelativeLayout {

    String type;
    public static String BACK_HEADER = "Back Header";
    public static String HOME_SCREEN = "Home Screen";

    @BindView(R.id.nv_back_btn)
    CardView navigationBack;

    @BindView(R.id.nv_next_button)
    CardView navigationNext;

    public NavigationHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        type = HOME_SCREEN;
    }

    public void setUp(String type, final Activity activity, String title) {
        this.type = type;
        setUpUI(title);

        navigationBack.setOnClickListener(view -> activity.finish());
    }

    private void setUpUI(String title) {
        removeAllViews();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.navigation_header, null);
        TextView headerTitle = view.findViewById(R.id.header_title);
        headerTitle.setText(title);
        ButterKnife.bind(this, view);

        if (type.equals(BACK_HEADER)) {
            navigationNext.setVisibility(GONE);
        }

        addView(view);
    }


}
