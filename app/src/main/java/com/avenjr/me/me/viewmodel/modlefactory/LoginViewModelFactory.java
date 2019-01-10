package com.avenjr.me.me.viewmodel.modlefactory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.avenjr.me.me.interfaces.LoginResultCallbacks;
import com.avenjr.me.me.viewmodel.LoginViewModel;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private LoginResultCallbacks loginResultCallbacks;

    public LoginViewModelFactory(LoginResultCallbacks loginResultCallbacks) {
        this.loginResultCallbacks = loginResultCallbacks;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new LoginViewModel(loginResultCallbacks);
    }
}
