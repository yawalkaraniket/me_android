package com.avenjr.me.me.ui.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtils {

    public static final String CONTACTS = "CONTACTS";
    private static final int REQUEST_READ_CONTACTS = 1;

    public static boolean isPermissionGrantedFor(Activity activity, String permissionFor) {
        boolean isPermissionGranted = false;

        switch (permissionFor) {
            case CONTACTS:
                isPermissionGranted  = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_CALENDAR)
                        == PackageManager.PERMISSION_GRANTED;
        }
        return isPermissionGranted;
    }

    public static void requestPermissionFor(Activity activity, String permissionFor) {
        switch (permissionFor) {
            case CONTACTS:
                ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.READ_CONTACTS},
                        REQUEST_READ_CONTACTS);
        }
    }
}
