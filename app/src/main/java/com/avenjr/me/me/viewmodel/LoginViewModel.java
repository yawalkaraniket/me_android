package com.avenjr.me.me.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.avenjr.me.me.api.ApiUtils;
import com.avenjr.me.me.interfaces.LoginResultCallbacks;
import com.avenjr.me.me.modle.Employee;
import com.avenjr.me.me.modle.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private User user;
    private LoginResultCallbacks loginResultCallbacks;


    public LoginViewModel(LoginResultCallbacks loginResultCallbacks) {
        this.loginResultCallbacks = loginResultCallbacks;
        this.user = new User();
    }

    // To get the email from edit text
    public TextWatcher idTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                user.setId(editable.toString());
            }
        };
    }

    // To get the password.
    public TextWatcher passwordTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                user.setPassword(editable.toString());
            }
        };
    }

    // To process the login.
    public void onLoginClicked(View view) {
        ApiUtils.getMeService().getEmployee("19204").enqueue(new Callback<Employee>() {
           @Override
           public void onResponse(Call<Employee> call, Response<Employee> response) {
               Log.d("",response.toString());
           }

           @Override
           public void onFailure(Call<Employee> call, Throwable t) {
                Log.d("","");
           }
       });

        if (user.getId().isEmpty()) {
            loginResultCallbacks.onError("User id should not be blank!");
        } else if (user.getPassword().isEmpty()) {
            loginResultCallbacks.onError("Password should not be blank!");
        } else if (user.getPassword().length() < 6) {
            loginResultCallbacks.onError("Password should be greater the 6 character!");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(user.getId()).matches()) {
            loginResultCallbacks.onError("Please enter correct email!");
        } else {
            loginResultCallbacks.onSuccess("Login success!");
        }
    }
}
