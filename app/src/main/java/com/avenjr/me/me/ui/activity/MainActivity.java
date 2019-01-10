package com.avenjr.me.me.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.avenjr.me.me.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.avenjr.me.me.ui.Utils.AnimationsUtil.didTapButton;
import static com.avenjr.me.me.ui.animation.MoveAnimation.moveFromTop;
import static com.avenjr.me.me.ui.animation.MoveAnimation.moveToLeft;
import static com.avenjr.me.me.ui.animation.MoveAnimation.moveToRight;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.top_layout)
    RelativeLayout signInOptions;

    @BindView(R.id.sign_in_button)
    RelativeLayout signIn;

    @BindView(R.id.register_button)
    RelativeLayout register;

    @BindView(R.id.already_register_sign_in)
    TextView alreadySignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        moveToLeft(signIn);
        moveToRight(register);
        moveFromTop(signInOptions, 350f);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                didTapButton(view, MainActivity.this);
                moveFromTop(signInOptions, 520f);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                didTapButton(view, MainActivity.this);
//                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
//                startActivity(intent);
//// Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");
            }
        });
    }

    @OnClick(R.id.already_register_sign_in)
    public void setAlreadySignIn() {
        Intent intent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onPause() {
        super.onPause();
        moveFromTop(signInOptions, -70);
    }

    @Override
    protected void onResume() {
        super.onResume();
        moveFromTop(signInOptions, 350f);
    }
}
