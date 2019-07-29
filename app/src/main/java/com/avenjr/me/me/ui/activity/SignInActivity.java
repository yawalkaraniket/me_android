package com.avenjr.me.me.ui.activity;

import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.avenjr.me.me.R;
import com.avenjr.me.me.databinding.ActivitySignInBinding;
import com.avenjr.me.me.interfaces.LoginResultCallbacks;
import com.avenjr.me.me.ui.Utils.DialogUtil;
import com.avenjr.me.me.ui.views.CustomEditText;
import com.avenjr.me.me.ui.views.NavigationHeader;
import com.avenjr.me.me.viewmodel.LoginViewModel;
import com.avenjr.me.me.viewmodel.modlefactory.LoginViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity implements LoginResultCallbacks {

    @BindView(R.id.navigationHeader)
    NavigationHeader navigationHeader;

    @BindView(R.id.signIn_et_forgotPassword)
    TextView btnForgotPassword;

    @BindView(R.id.signIn_et_id)
    CustomEditText email;

    @BindView(R.id.signIn_et_password)
    CustomEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySignInBinding activitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        activitySignInBinding.setViewModel(ViewModelProviders.of(this,
                new LoginViewModelFactory(this)).get(LoginViewModel.class));

        ButterKnife.bind(this);
        navigationHeader.setUp(NavigationHeader.BACK_HEADER, this, getString(R.string.login_title));
        setUpForgotIdAndPasswordView();
        addTouchListener();
    }

    private void addTouchListener() {
        email.setFocusable(false);
        password.setFocusable(false);
        email.setOnTouchListener((v, event) -> {
            email.setFocusable(true);
            email.setFocusableInTouchMode(true);
            return false;
        });
        password.setOnTouchListener((v, event) -> {
            password.setFocusable(true);
            password.setFocusableInTouchMode(true);
            return false;
        });
    }

    private void setUpForgotIdAndPasswordView() {

        final ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.white));
        final ClickableSpan forgotId  = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                DialogUtil.forgotEmailDialog(SignInActivity.this, clicks ->{ }, clicks ->{});
            }
            @Override
            public void updateDrawState(@NonNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        };

        final ClickableSpan forgorPassword = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                DialogUtil.forgotPasswordDialog(SignInActivity.this, clicks ->{ }, clicks ->{});
            }
            @Override
            public void updateDrawState(@NonNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        };

        final SpannableStringBuilder sb = new SpannableStringBuilder(getResources().getString(R.string.forgot_your_email_passwod));

        int emailStart = btnForgotPassword.getText().toString().indexOf(getResources().getString(R.string.emailId));
        int emailEnd = emailStart + getResources().getString(R.string.emailId).length();

        int passwordStart = btnForgotPassword.getText().toString().indexOf(getResources().getString(R.string.password));
        int passwordEnd = passwordStart + getResources().getString(R.string.password).length();
        sb.setSpan(foregroundColorSpan, emailStart, emailStart, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(forgotId, emailStart, emailEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(forgorPassword, passwordStart, passwordEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new UnderlineSpan(), emailStart, emailEnd, 0);
        sb.setSpan(new UnderlineSpan(), passwordStart, passwordEnd, 0);

        btnForgotPassword.setMovementMethod(LinkMovementMethod.getInstance());
        btnForgotPassword.setText(sb);
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
