package com.avenjr.me.me.ui.Utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.avenjr.me.me.R;

/*
    Purpose: To perform different animations on the view
    Date:    10/03/2018
    Author:  Aniket Yawalkar
 */

public class AnimationsUtil {

    public static Animation loadFadeInAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.fade_in_animation);
    }

    public static Animation loadFadInAnimationForLauncher(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.fade_in_animation_for_launcher);
    }

    public static Animation loadFadeoutAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.fade_out);
    }

    public static Animation loadBounceAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.bounce);
    }

    public static Animation loadCrossFiddingAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.cross_fidding);
    }

    public static Animation loadMoveAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.move);
    }

    public static Animation loadRotateAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.rotate);
    }

    public static Animation loadSlideDownAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.slide_down);
    }

    public static Animation loadSlideUpAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.slide_up);
    }

    public static Animation loadZoomInAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.zoom_in);
    }

    public static Animation loadZoomOutAnimation(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.zoom_out);
    }
}
