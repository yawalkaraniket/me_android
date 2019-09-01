package com.avenjr.me.me.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.views.ProfileSlider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsActivity extends AppCompatActivity {

    @BindView(R.id.friends_layout_parent)
    ConstraintLayout root;

    ProfileSlider profileSlider;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        ButterKnife.bind(this);

        initializeProfileSlider();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initializeProfileSlider() {
        ConstraintSet set = new ConstraintSet();
        profileSlider = new ProfileSlider(this, getSupportFragmentManager());
        profileSlider.setId(View.generateViewId());
        root.addView(profileSlider);
        set.clone(root);
        set.connect(profileSlider.getId(), ConstraintSet.BOTTOM, root.getId(), ConstraintSet.BOTTOM, -10);
        set.applyTo(root);

/*
        root.addView(profileSlider = new ProfileSlider(this.getContext()));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) profileSlider.getLayoutParams();
        layoutParams.setMargins(0, dp(40), 0, 0);
        profileSlider.setVisibility(View.VISIBLE);
*/
    }
}
