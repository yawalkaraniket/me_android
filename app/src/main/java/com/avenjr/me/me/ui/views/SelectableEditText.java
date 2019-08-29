package com.avenjr.me.me.ui.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import com.avenjr.me.me.R;

public class SelectableEditText extends AppCompatEditText {

    public SelectableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void valid() {
        setBackground(getResources().getDrawable(R.drawable.edit_text_valid));
    }

    public void rejected() {
        setBackground(getResources().getDrawable(R.drawable.edit_text_rejected));
    }

    public void unselected() {
        setBackground(getResources().getDrawable(R.drawable.edit_text_unselected));
    }
}