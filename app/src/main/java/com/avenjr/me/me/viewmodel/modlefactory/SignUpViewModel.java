package com.avenjr.me.me.viewmodel.modlefactory;

import androidx.lifecycle.ViewModel;
import android.text.Editable;
import android.text.TextWatcher;

import com.avenjr.me.me.interfaces.LoginResultCallbacks;
import com.avenjr.me.me.modle.User;

public class SignUpViewModel extends ViewModel {

    private User user;
    private LoginResultCallbacks loginResultCallbacks;

    public SignUpViewModel(LoginResultCallbacks loginResultCallbacks) {
        this.loginResultCallbacks = loginResultCallbacks;
        this.user = new User();
    }

    // For sign up email text
    public TextWatcher emailTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setPassword(s.toString());
            }
        };
    }


}
