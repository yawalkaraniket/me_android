package com.avenjr.me.me.ui.animation;

import android.animation.ObjectAnimator;
import android.view.View;

public class MoveAnimation {

    public static void moveToLeft(View view) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationX", 100f);
        animation.setDuration(3000);
        animation.start();
    }

    public static void moveToRight(View view) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationX", -100f);
        animation.setDuration(3000);
        animation.start();
    }

    public static void moveFromTop(View view, float value) {
        ObjectAnimator animation = ObjectAnimator.ofFloat(view, "translationY" ,value);
        animation.setDuration(3000);
        animation.start();
    }
}
