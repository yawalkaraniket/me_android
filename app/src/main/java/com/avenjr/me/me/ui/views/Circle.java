package com.avenjr.me.me.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

// This is use to draw a circle.

public class Circle extends View {

    // Angle
    private static final int START_ANGLE_POINT = 90;

    private final Paint paint;
    private final RectF rect;
    private int strokeWidth;


    private float angle;

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);

        if(this.strokeWidth == 0) {
            strokeWidth = 10;
        }

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        //Circle color
        paint.setColor(Color.RED);

        //size 200x200 example width and height of the circle
        rect = new RectF(strokeWidth, strokeWidth,  250 + strokeWidth, 250 + strokeWidth);

        //Initial Angle (optional, it can be zero)
        angle = 120;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, START_ANGLE_POINT, angle, true, paint);
    }

    public float getAngle() {
        return angle;
    }

    // Width of the stroke
    public void setStroke(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}