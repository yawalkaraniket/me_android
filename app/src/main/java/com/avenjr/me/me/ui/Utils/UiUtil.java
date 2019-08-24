package com.avenjr.me.me.ui.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import static android.content.Context.VIBRATOR_SERVICE;
import static android.content.Context.WINDOW_SERVICE;

public class UiUtil {

    public static int getScreenWidthInPixel(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeightInPixel(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getScreenWidthInDp(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int pixelDpi = displayMetrics.densityDpi;
        return (displayMetrics.widthPixels / pixelDpi) * 160;
    }


    public static int getScreenHeightInDp(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int pixelDpi = displayMetrics.densityDpi;
        return (displayMetrics.heightPixels / pixelDpi) * 160;
    }

    private static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }

    public static float dp() {
        DisplayMetrics metrics = getDisplayMetrics();
        return (float) metrics.densityDpi / 160f;
    }

    public static int dp(float value) {
        return (int) (value * dp());
    }

    public static void vibrate(Activity activity) {
        Vibrator vibrator =(Vibrator) activity.getSystemService(VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            vibrator.vibrate(200);
        }
    }
}
