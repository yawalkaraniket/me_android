package com.avenjr.me.me.ui.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.avenjr.me.me.R;

public class CustomNumberPicker extends android.widget.NumberPicker {
    private Context context;
    private Typeface tfs;
    public CustomNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        tfs = Typeface.createFromAsset(context.getAssets(),getResources().getString(R.string.font_hinted_Averta_Bold));
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    private void updateView(View view) {
        if(view instanceof EditText){
            ((EditText) view).setTypeface(tfs);
            ((EditText) view).setTextSize(25);
            ((EditText) view).setTextColor(getResources().getColor(R.color.black));
        }
    }

}