package com.avenjr.me.me.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.avenjr.me.me.ui.fragments.EnterEmailFragment;
import com.avenjr.me.me.ui.fragments.EnterPasswordFragment;
import com.avenjr.me.me.ui.fragments.VerifyNumberFragment;

public class OnboardingPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public OnboardingPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle bundle = new Bundle();

        switch (position) {
            case 0:
                fragmentClass  = EnterEmailFragment.class;
                break;
            case 1:
                fragmentClass = EnterPasswordFragment.class;
                break;
            case 2:
                fragmentClass = VerifyNumberFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
