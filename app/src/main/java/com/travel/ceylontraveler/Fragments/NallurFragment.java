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

public class NallurFragment extends Fragment implements OnMapReadyCallback {
    private LiveLocationTrackerActivity liveLocationTrackerActivity;
    private VideoView videoView;
    private MapView mapViewNallur;
    private GoogleMap googleMap;
    public NallurFragment() {
        // Required empty public constructor
    }
    public static NallurFragment newInstance(String param1, String param2) {
        NallurFragment fragment = new NallurFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liveLocationTrackerActivity = new LiveLocationTrackerActivity(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nallur, container, false);

        ///////////////////favorite button add or remove page in Favorite fragment/////////////
        AddRemoveFavoriteActivity.setupFavoriteButton(getActivity(), view, "Nallur");

        // Initialize MapView
        mapViewNallur = view.findViewById(R.id.mapViewNallur);
        mapViewNallur.onCreate(savedInstanceState);

        // Call method to start playing video
        VideoView videoView = view.findViewById(R.id.vedioNallur);
        VideoPlayerActivity videoPlayer = new VideoPlayerActivity(getActivity(), videoView);
        videoPlayer.playVideo(R.raw.nallur);

        // Get reference to GoogleMap
        mapViewNallur.getMapAsync(this);

        //  "More Details" button
        Button btnOpenUrl = view.findViewById(R.id.btnMoreDetilsNallur);
        btnOpenUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://cyelontaveler.blogspot.com/";
                openUrl(url);
            }
        });

        // booking button
        Button btnBooking = view.findViewById(R.id.btnBookingDetilsNallur);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.booking.com/searchresults.html?ss=Nallur&ssne=Nagadeepaya%2C+Sri+Lanka&ssne_untouched=Nagadeepaya%2C+Sri+Lanka&efdco=1&label=msn-5v4p76R4b4u2dySli_cXGQ-80195722701371%3Atikwd-17155438432%3Aloc-36%3Aneo%3Amtp%3Alp147906%3Adec%3Aqsboking%2B.com%2B%2Bnagadeepaya%2B&aid=393655&lang=en-us&sb=1&src_elem=sb&src=searchresults&group_adults=2&no_rooms=1&group_children=0";
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
        LatLng Nallur = new LatLng(9.674849577870422, 80.02951899707075);
        googleMap.addMarker(new MarkerOptions().position(Nallur).title("Nallur"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Nallur, 10));
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
        mapViewNallur.onResume();
        liveLocationTrackerActivity.startLocationUpdates();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapViewNallur.onPause();
        liveLocationTrackerActivity.startLocationUpdates();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapViewNallur.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapViewNallur.onLowMemory();
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
