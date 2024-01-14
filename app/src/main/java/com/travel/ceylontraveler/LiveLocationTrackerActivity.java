package com.travel.ceylontraveler;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class LiveLocationTrackerActivity {

    private Context context;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    public LiveLocationTrackerActivity(Context context) {
        this.context = context;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

        // Call method to create location request
        createLocationRequest();

        // Call method to create location callback
        createLocationCallback();
    }

    // Create location request
    private void createLocationRequest() {
        locationRequest = LocationRequest.create();
        // Set the time interval
        locationRequest.setInterval(10000); // 10 secondsd
        locationRequest.setFastestInterval(5000); // 5 seconds
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    // Create callback for location updates
    private void createLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }


                for (Location location : locationResult.getLocations()) {   //tesing live location
                    Log.d("CheckLiveLocationData", "Location data: " +
                            "provider=" + location.getProvider() +
                            ", time=" + location.getTime() +
                            ", latitude=" + location.getLatitude() +
                            ", longitude=" + location.getLongitude() +
                            ", speed=" + location.getSpeed() +
                            ", accuracy=" + location.getAccuracy());
                }
            }
        };
    }

    // Start receiving location updates
    public void startLocationUpdates() {
        // Check if app has permission to access device location
        if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Request location updates
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }
    }

    // Stop receiving location updates
    public void stopLocationUpdates() {
        // Remove location updates
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }
}
