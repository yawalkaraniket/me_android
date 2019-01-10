package com.avenjr.me.me.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.avenjr.me.me.R;
import com.avenjr.me.me.databinding.ActivitySignInBinding;
import com.avenjr.me.me.interfaces.LoginResultCallbacks;
import com.avenjr.me.me.ui.views.NavigationHeader;
import com.avenjr.me.me.viewmodel.LoginViewModel;
import com.avenjr.me.me.viewmodel.modlefactory.LoginViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.avenjr.me.me.ui.views.NavigationHeader.BACK_HEADER;

public class SignInActivity extends AppCompatActivity implements LoginResultCallbacks {

    @BindView(R.id.navigationHeader)
    NavigationHeader navigationHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySignInBinding activitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        activitySignInBinding.setViewModel(ViewModelProviders.of(this,
                new LoginViewModelFactory(this)).get(LoginViewModel.class));

        ButterKnife.bind(this);
        navigationHeader.setUp(BACK_HEADER, this, "User Login");
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
