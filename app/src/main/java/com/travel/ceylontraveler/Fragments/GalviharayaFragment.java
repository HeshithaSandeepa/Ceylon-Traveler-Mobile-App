package com.travel.ceylontraveler.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.travel.ceylontraveler.LiveLocationTrackerActivity;
import com.travel.ceylontraveler.Methods.AcessPermisson;
import com.travel.ceylontraveler.Methods.AddRemoveFavoriteActivity;
import com.travel.ceylontraveler.Methods.EnableCurrentLocationButtonActivity;
import com.travel.ceylontraveler.Methods.VideoPlayerActivity;
import com.travel.ceylontraveler.R;

public class GalviharayaFragment extends Fragment implements OnMapReadyCallback {
    private LiveLocationTrackerActivity liveLocationTrackerActivity;
    private MapView mapviewGalviharaya;
    private GoogleMap googleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liveLocationTrackerActivity = new LiveLocationTrackerActivity(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_galviharaya, container, false);

        ///////////////////favorite button add or remove page in Favorite fragment/////////////
        AddRemoveFavoriteActivity.setupFavoriteButton(getActivity(), view, "Gal Viharaya");

        // Initialize MapView
        mapviewGalviharaya = view.findViewById(R.id.mapViewGalviaraya);
        mapviewGalviharaya.onCreate(savedInstanceState);

        // Call method to start playing video
        VideoView videoView = view.findViewById(R.id.vedioGalviaraya);
        VideoPlayerActivity videoPlayer = new VideoPlayerActivity(getActivity(), videoView);
        videoPlayer.playVideo(R.raw.galviharaya);

        // Get reference to GoogleMap
        mapviewGalviharaya.getMapAsync(this);

        //  "More Details" button
        Button btnOpenUrl = view.findViewById(R.id.btnMoreDetilsGalviharaya);
        btnOpenUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://cyelontaveler.blogspot.com/";
                openUrl(url);
            }
        });

        // booking button
        Button btnBooking = view.findViewById(R.id.btnBookingDetilsGalviharaya);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.booking.com/searchresults.html?ss=Gal+Viharaya%2C+Polonnaruwa%2C+Polonnaruwa+District%2C+Sri+Lanka&ssne=Gal+Viharaya&ssne_untouched=Gal+Viharaya&efdco=1&label=gen173nr-1FCAEoggI46AdIM1gEaIUBiAEBmAExuAEXyAEP2AEB6AEB-AECiAIBqAIDuAL53e6jBsACAdICJGEyMzZkNjE1LTY4OTctNGUxOS1iOGQ5LWE2MDg5M2ZiMmFmONgCBeACAQ&aid=304142&lang=en-us&sb=1&src_elem=sb&src=searchresults&dest_id=900125565&dest_type=landmark&ac_position=0&ac_click_type=b&ac_langcode=en&ac_suggestion_list_length=1&search_selected=true&search_pageview_id=87269773ddb00082&ac_meta=GhA4NzI2OTc3M2RkYjAwMDgyIAAoATICZW46CGdhbCB2aWhhQABKAFAA&group_adults=2&no_rooms=1&group_children=0";
                openUrl(url);
            }
        });

        return view;
    }

    // Request location permission
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!AcessPermisson.hasLocationPermission((FragmentActivity) context)) {
            // Request location permission
            AcessPermisson.requestLocationPermission(getActivity());
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;
        LatLng Galviharaya = new LatLng(7.966248638943754, 81.00461570684689);
        googleMap.addMarker(new MarkerOptions().position( Galviharaya).title(" Galviharaya"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom( Galviharaya, 10));
        // Add zoom controls to the map and position
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.isCompassEnabled();

        // Enable currentLocation Button
        EnableCurrentLocationButtonActivity.enableMyLocation(getActivity(), googleMap);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapviewGalviharaya.onResume();
        liveLocationTrackerActivity.startLocationUpdates();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapviewGalviharaya.onPause();
        liveLocationTrackerActivity.startLocationUpdates();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapviewGalviharaya.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapviewGalviharaya.onLowMemory();
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
