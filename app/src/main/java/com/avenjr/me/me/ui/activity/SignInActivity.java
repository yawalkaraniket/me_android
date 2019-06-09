package com.avenjr.me.me.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.avenjr.me.me.R;
import com.avenjr.me.me.databinding.ActivitySignInBinding;
import com.avenjr.me.me.interfaces.LoginResultCallbacks;
import com.avenjr.me.me.ui.Utils.DialogUtil;
import com.avenjr.me.me.ui.views.NavigationHeader;
import com.avenjr.me.me.viewmodel.LoginViewModel;
import com.avenjr.me.me.viewmodel.modlefactory.LoginViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.avenjr.me.me.ui.views.NavigationHeader.BACK_HEADER;

public class SignInActivity extends AppCompatActivity implements LoginResultCallbacks {

    @BindView(R.id.navigationHeader)
    NavigationHeader navigationHeader;

    @BindView(R.id.signIn_et_forgotPassword)
    TextView btnForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySignInBinding activitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        activitySignInBinding.setViewModel(ViewModelProviders.of(this,
                new LoginViewModelFactory(this)).get(LoginViewModel.class));

        ButterKnife.bind(this);
        navigationHeader.setUp(BACK_HEADER, this, "User Login");
        setUpForgotIdAndPasswordView();
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
