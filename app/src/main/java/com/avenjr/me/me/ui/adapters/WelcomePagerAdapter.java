package com.avenjr.me.me.ui.adapters;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.fragments.WelcomeFragment;

public class WelcomePagerAdapter extends FragmentStatePagerAdapter {

    private FragmentManager fragmentManager;
    private Context context;

    public WelcomePagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.context = context;
        this.fragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle bundle = new Bundle();

        switch (position) {
            case 0:
                fragmentClass  = WelcomeFragment.class;
                bundle.putSerializable("screen", context.getResources().getString(R.string.select_hobbies_title));
                break;
            case 1:
                fragmentClass = WelcomeFragment.class;
                bundle.putSerializable("screen", context.getResources().getString(R.string.select_extra_activities_title));
                break;
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
        return 2;
    }
}
