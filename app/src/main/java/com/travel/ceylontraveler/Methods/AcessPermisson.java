
package com.travel.ceylontraveler.Methods;

import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class AcessPermisson {

    // Request code for location permission
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    // Check if the app has location permission
    public static boolean hasLocationPermission(FragmentActivity context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
    // Request location permission
    public static void requestLocationPermission(FragmentActivity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE);
    }

    // Handle location permission request result
    public static boolean handleLocationPermissionResult(int requestCode, int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                return true;
            } else {
                // Permission denied
                return false;
            }
        }
        return false;
    }
}













