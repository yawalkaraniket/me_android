package com.avenjr.me.me.ui.views;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CircleAngleAnimation extends Animation {

    private Circle circle;

    private float oldAngle;
    private float newAngle;
    private int strokeWidth;

    public CircleAngleAnimation(Circle circle, int newAngle, int strokeWidth) {
        this.strokeWidth = strokeWidth;
        this.oldAngle = circle.getAngle();
        this.newAngle = newAngle;
        this.circle = circle;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float angle = oldAngle + ((newAngle - oldAngle) * interpolatedTime);

        circle.setStroke(strokeWidth);
        circle.setAngle(angle);
        circle.requestLayout();
    }
}