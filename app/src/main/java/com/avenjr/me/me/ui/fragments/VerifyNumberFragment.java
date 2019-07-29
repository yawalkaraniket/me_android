package com.avenjr.me.me.ui.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.adapters.CountryCodeAdapter;
import com.avenjr.me.me.ui.views.CustomEditText;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyNumberFragment extends Fragment {

    @BindView(R.id.approve_reject_image)
    ImageView approveRejectImage;

    @BindView(R.id.reg_mobile_txt)
    CustomEditText mobileNo;

    @BindView(R.id.country_spinner)
    Spinner selectCountrySpinner;

    @BindView(R.id.country_code)
    TextView countryCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verify_number, container, false);
        ButterKnife.bind(this, view);
        ArrayList<String> countries = new ArrayList<>(Arrays.asList("India", "Canada", "China", "Japan"));
        ArrayList<Integer> flags = new ArrayList<>(Arrays.asList(R.drawable.approved_green, R.drawable.approved_green, R.drawable.app_background, R.drawable.circle));
        ArrayList<String> code = new ArrayList<>(Arrays.asList("+91", "+1", "+86", "+81"));

        CountryCodeAdapter countryCodeAdapter = new CountryCodeAdapter(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, countries, flags);
        countryCodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectCountrySpinner.setAdapter(countryCodeAdapter);

        selectCountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryCode.setText(code.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mobileNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 10) {
                    approveRejectImage.setBackground(getResources().getDrawable(R.drawable.approved_green));
                } else if (s.length() != 0) {
                    approveRejectImage.setBackground(getResources().getDrawable(R.drawable.reject_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

}
