package com.avenjr.me.me.ui.adapters;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.avenjr.me.me.ui.fragments.FriendsFragment;
import com.avenjr.me.me.ui.fragments.HomeFragment;
import com.avenjr.me.me.ui.fragments.ProfileFragment;

public class NavigationPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private FragmentManager fragmentManager;

    public NavigationPagerAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.fragmentManager = fm;
        this.context = context;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle bundle = new Bundle();

        switch (position) {
            case 0:
                fragmentClass  = HomeFragment.class;
                break;
            case 1:
                fragmentClass = FriendsFragment.class;
                break;
            case 2:
                fragmentClass = ProfileFragment.class;
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
        return 3;
    }
}
