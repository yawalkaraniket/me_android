package com.avenjr.me.me.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avenjr.me.me.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileNotificationsFragment extends Fragment {

    @BindView(R.id.title)
    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_notifications, container, false);
        ButterKnife.bind(this, view);

        String screen = getArguments().getString("notification_type");
        title.setText(screen);

        return view;
    }

}
