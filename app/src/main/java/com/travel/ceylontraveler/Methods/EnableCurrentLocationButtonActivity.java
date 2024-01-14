package com.travel.ceylontraveler.Methods;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;

// enable and get current location button in google map
public class EnableCurrentLocationButtonActivity {

        public static void enableMyLocation(FragmentActivity activity, GoogleMap googleMap) {
            if (AcessPermisson.hasLocationPermission(activity)) {
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);
            }
        }
    }


