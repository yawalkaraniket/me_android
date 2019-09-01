package com.avenjr.me.me.ui.adapters;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.avenjr.me.me.ui.fragments.ProfileNotificationsFragment;

public class FriendsNotificationsAdapter extends FragmentPagerAdapter {

    private Context context;
    private FragmentManager fragmentManager;
    private int totalTabs;

    public FriendsNotificationsAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.context = context;
        this.fragmentManager = fm;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                fragment = new ProfileNotificationsFragment();
                bundle.putSerializable("notification_type", "Friend Request");
                break;
            case 1:
                fragment = new ProfileNotificationsFragment();
                bundle.putSerializable("notification_type", "Message");
                break;
            case 2:
                fragment = new ProfileNotificationsFragment();
                bundle.putSerializable("notification_type", "Notifications");
                break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
