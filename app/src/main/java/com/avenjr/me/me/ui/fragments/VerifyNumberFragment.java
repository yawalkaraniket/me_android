package com.avenjr.me.me.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avenjr.me.me.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyNumberFragment extends Fragment {


    public VerifyNumberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_verify_number, container, false);
    }

}
