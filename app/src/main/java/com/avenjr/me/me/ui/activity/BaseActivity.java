package com.avenjr.me.me.ui.activity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.views.CustomTextView;

import static com.avenjr.me.me.ui.Utils.UiUtil.dp;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    protected ProgressBar progressBar;
    protected View blockLayout;
    protected CustomTextView loading;

    public final static int PROGRESS_TYPE_NO_BLOCK = 0;
    public final static int PROGRESS_TYPE_BLOCK_TRANSPARENT = 1;

    protected void addFragment(FragmentActivity activity, Class fragmentClass) {
        FragmentManager manager = activity.getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        manager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    protected void replaceFragment(FragmentActivity activity, Class fragmentClass) {
        FragmentManager manager = activity.getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        manager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    protected void clearFragmentManager() {
        try {
            String name = getSupportFragmentManager().getBackStackEntryAt(0).getName();
            getSupportFragmentManager().popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (IllegalStateException s) {
            // There's no way to avoid getting this if saveInstanceState has already been called.
        }
    }

    /**
     * Adding progress bar
     * Link: https://www.zoftino.com/adding-views-&-constraints-to-android-constraint-layout-programmatically : Adding constraint layout programatically
     *
     * @param layout Parent layout
     */

    protected void setUpProgress(View layout) {

        if (layout instanceof ConstraintLayout) {

            ConstraintLayout constraintLayout = (ConstraintLayout) layout;

            // Setting icon and color for the text view.
            progressBar = new ProgressBar(layout.getContext());
            progressBar.setBackgroundResource(R.mipmap.ic_launcher_foreground);
            progressBar.setId(R.id.onboarding_progress);
            progressBar.setLayoutParams(new LinearLayout.LayoutParams((int) (60 * dp()), (int) (60 * dp())));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                progressBar.setIndeterminateTintList(ColorStateList.valueOf(Color.WHITE));
            } else {
                // Add the code for below API 21.
            }

            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            progressBar.setLayoutParams(layoutParams);
            constraintLayout.addView(progressBar);

            // Adding layout canter to the parent
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.connect(progressBar.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 18);
            constraintSet.connect(progressBar.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 18);
            constraintSet.connect(progressBar.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 18);
            constraintSet.connect(progressBar.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 18);
            constraintSet.applyTo(constraintLayout);

            // Layout for the block layout
            blockLayout = new ConstraintLayout(this);
            blockLayout.setLayoutParams( new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT));
            constraintLayout.addView(blockLayout);

            // Setting visibility for all layout gone for the first time.
            blockLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);

            // Adding parent listener
            blockLayout.setOnClickListener(v -> { });

        } else if (layout instanceof RelativeLayout) {

            RelativeLayout relativeLayout = (RelativeLayout) layout;
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            progressBar = new ProgressBar(layout.getContext());
            progressBar.setBackgroundResource(R.mipmap.ic_launcher_foreground);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                progressBar.setIndeterminateTintList(ColorStateList.valueOf(Color.WHITE));
            } else {
                // Add the code for below API 21.
            }
            progressBar.setLayoutParams(new LinearLayout.LayoutParams((int) (60 * dp()), (int) (60 * dp())));

            RelativeLayout.LayoutParams params;
            linearLayout.setLayoutParams(params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setGravity(Gravity.CENTER);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);

            loading = new CustomTextView(this);
            loading.setText(getString(R.string.loading_generic_message));
            loading.setTextColor(getResources().getColor(R.color.white));
            loading.setGravity(Gravity.CENTER);

            linearLayout.addView(progressBar);
            linearLayout.addView(loading);

            blockLayout = new RelativeLayout(this);
            blockLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            relativeLayout.addView(blockLayout);
            blockLayout.setBackgroundColor(getResources().getColor(R.color.black_transparent));
            blockLayout.setOnClickListener(click -> {
            });

            relativeLayout.addView(linearLayout);
            blockLayout.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
        }
    }

    protected void showProgress() {
        showProgress(PROGRESS_TYPE_BLOCK_TRANSPARENT);
    }

    protected void showProgress(int type) {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (type == PROGRESS_TYPE_BLOCK_TRANSPARENT && blockLayout != null) {
            blockLayout.setVisibility(View.VISIBLE);
        }
        if (type == PROGRESS_TYPE_NO_BLOCK && blockLayout != null) {
            blockLayout.setVisibility(View.GONE);
        }
    }

    protected void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
        if (blockLayout != null) {
            blockLayout.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
        }
    }
}
