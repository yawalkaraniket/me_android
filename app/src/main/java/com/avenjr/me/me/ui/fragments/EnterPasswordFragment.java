package com.avenjr.me.me.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.avenjr.me.me.R;
import com.avenjr.me.me.modle.User;
import com.avenjr.me.me.ui.views.SelectableEditText;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnterPasswordFragment extends Fragment {

    @BindView(R.id.reg_password_txt)
    SelectableEditText password;

    @BindView(R.id.reg__re_password_txt)
    SelectableEditText rePassword;

    @BindView(R.id.approve_reject__pass_image)
    ImageView approveRejectPassImage;

    @BindView(R.id.approve_reject__confirm_image)
    ImageView approveRejectConfirmPassImage;

    User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enter_password, container, false);
        ButterKnife.bind(view);

        user = User.getInstance();
        validateFields();

        return view;
    }

    private void validateFields() {

        // Validate password.
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 6) {
                    password.valid();
                    approveRejectPassImage.setImageDrawable(getResources().getDrawable(R.drawable.approved_green));
                } else {
                    password.rejected();
                    approveRejectPassImage.setImageDrawable(getResources().getDrawable(R.drawable.reject_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Validate confirm password.
        rePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (Objects.requireNonNull(password.getText()).toString().equals(s.toString())) {
                    password.valid();
                    user.setPassword(s.toString());
                    approveRejectConfirmPassImage.setImageDrawable(getResources().getDrawable(R.drawable.approved_green));
                } else {
                    password.rejected();
                    approveRejectConfirmPassImage.setImageDrawable(getResources().getDrawable(R.drawable.reject_gray));
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

}
