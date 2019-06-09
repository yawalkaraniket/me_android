package com.avenjr.me.me.ui.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;

import com.avenjr.me.me.R;
import com.avenjr.me.me.ui.views.CustomNumberPicker;

import java.time.Year;
import java.util.GregorianCalendar;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class DialogUtil {

    public static Dialog forgotPasswordDialog(Activity activity, final View.OnClickListener positiveClick,
                                              final View.OnClickListener negativeClick) {
        View view = activity.getLayoutInflater().inflate(R.layout.forgot_password_dialog, null);

        final android.app.Dialog dialog = new android.app.Dialog(activity, R.style.dialog_style);
        dialog.setContentView(view);

        View submit = view.findViewById(R.id.btn_id_submit);
        View cancel = view.findViewById(R.id.btn_cancel);

        try {
            dialog.show();
        } catch (Exception e) {
            dialog.dismiss();
            e.printStackTrace();
        }

        cancel.setOnClickListener(v -> {
            dialog.dismiss();
            if (negativeClick != null) {
                negativeClick.onClick(v);
            }
        });

        submit.setOnClickListener(v -> {
            dialog.dismiss();
            if (positiveClick != null) {
                positiveClick.onClick(v);
            }
        });


        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);
        window.setLayout(MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        return dialog;
    }

    public static Dialog forgotEmailDialog(Activity activity, final View.OnClickListener positiveClick,
                                              final View.OnClickListener negativeClick) {
        int muxYear = 0;
        int minYear;
        final int[] muxDate = new int[1];
        int minDate;
        final int[] selectedYear = new int[1];
        String month[] = new String[] {"January","February","March","April","May","June","July",
                "August","September","October","November","December"};
        final String[] selectedMonth = new String[1];
        View view = activity.getLayoutInflater().inflate(R.layout.forgot_email_dialog, null);

        final android.app.Dialog dialog = new android.app.Dialog(activity, R.style.dialog_style);
        dialog.setContentView(view);

        View submit = view.findViewById(R.id.btn_id_submit);
        View cancel = view.findViewById(R.id.btn_cancel);

        CustomNumberPicker yearPicker = view.findViewById(R.id.dialog_year_picker);
        CustomNumberPicker monthPicker = view.findViewById(R.id.dialog_month_picker);
        CustomNumberPicker datePicker = view.findViewById(R.id.dialog_date_picker);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            muxYear = Calendar.getInstance().get(Calendar.YEAR);
        }

        minYear = 1965;
        yearPicker.setMaxValue(muxYear);
        yearPicker.setMinValue(minYear);
        yearPicker.setValue(2000);

        monthPicker.setDisplayedValues(month);
        monthPicker.setMinValue(0);
        monthPicker.setMaxValue(month.length - 1);

        datePicker.setValue(1);
        datePicker.setMinValue(1);
        datePicker.setMaxValue(31);

        NumberPicker.OnValueChangeListener valueChangeListener = (picker, oldVal, newVal) -> {

            selectedYear[0] = yearPicker.getValue();
            selectedMonth[0] = month[monthPicker.getValue()];
            muxDate[0] = getCurrentMuxDate(yearPicker.getValue(), monthPicker.getValue());
            datePicker.setMaxValue(muxDate[0]);

        };

        yearPicker.setOnValueChangedListener(valueChangeListener);
        monthPicker.setOnValueChangedListener(valueChangeListener);


        try {
            dialog.show();
        } catch (Exception e) {
            dialog.dismiss();
            e.printStackTrace();
        }

        cancel.setOnClickListener(v -> {
            dialog.dismiss();
            if (negativeClick != null) {
                negativeClick.onClick(v);
            }
        });

        submit.setOnClickListener(v -> {
            dialog.dismiss();
            if (positiveClick != null) {
                positiveClick.onClick(v);
            }
        });


        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);
        window.setLayout(MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        return dialog;
    }

    private static int getCurrentMuxDate(int year, int month) {
        // get a calendar object
        GregorianCalendar calendar = new GregorianCalendar();

        // set the date of the calendar to the date provided
        calendar.set(year, month, 1);

        return calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    }

}
