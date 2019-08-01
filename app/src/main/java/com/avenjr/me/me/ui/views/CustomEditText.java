package com.avenjr.me.me.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import androidx.appcompat.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;

import com.avenjr.me.me.R;

import static android.content.ContentValues.TAG;

public class CustomEditText extends AppCompatEditText {
    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    public void setCustomFont(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.FontTextView);
        String customFont = typedArray.getString(R.styleable.FontTextView_customFont);
        setCustomFont(context, customFont);
        typedArray.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        if (asset != null) {
            Typeface tf = null;
            try {
                tf = Typeface.createFromAsset(ctx.getAssets(), asset);
            } catch (Exception e) {
                Log.e(TAG, "Could not get typeface: " + e.getMessage());
                return false;
            }

            setTypeface(tf);
        } else {
            //set default typeface light
            setDefaultTypeface(ctx);
        }
        return true;
    }

    private void setDefaultTypeface(Context context) {
        setTypeface(Typeface.createFromAsset(context.getAssets(), context.getString(R.string.font_hinted_Averta_Bold)));
    }
}
