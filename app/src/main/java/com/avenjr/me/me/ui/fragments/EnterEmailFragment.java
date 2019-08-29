package com.avenjr.me.me.ui.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.avenjr.me.me.R;
import com.avenjr.me.me.modle.User;
import com.avenjr.me.me.ui.views.SelectableEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnterEmailFragment extends BaseFragment {

    @BindView(R.id.reg_email_txt)
    SelectableEditText emailText;

    @BindView(R.id.approve_reject_image)
    ImageView approveRejectImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enter_email, container, false);
        ButterKnife.bind(this, view);

        User user = User.getInstance();
        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                approveRejectImage.setBackground(getResources().getDrawable(R.drawable.reject_gray));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    user.setId(s.toString());
                    approveRejectImage.setBackground(getResources().getDrawable(R.drawable.approved_green));
                } else {
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
