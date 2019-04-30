package com.avenjr.me.me.ui.activity;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.avenjr.me.me.R;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    protected void addFragment(FragmentActivity activity, Class fragmentClass) {
        FragmentManager manager =  activity.getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        try {
            fragment = (Fragment) fragmentClass.newInstance() ;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        manager.beginTransaction()
                .add(R.id.fragment_container,fragment)
                .addToBackStack(null)
                .commit();
    }

    protected void replaceFragment(FragmentActivity activity, Class fragmentClass) {
        FragmentManager manager =  activity.getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        try {
            fragment = (Fragment) fragmentClass.newInstance() ;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        manager.beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .addToBackStack(null)
                .commit();
    }
}
