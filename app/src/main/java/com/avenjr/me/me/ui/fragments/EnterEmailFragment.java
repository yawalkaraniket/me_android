package com.avenjr.me.me.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avenjr.me.me.R;

import butterknife.ButterKnife;

public class EnterEmailFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enter_email, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

}
