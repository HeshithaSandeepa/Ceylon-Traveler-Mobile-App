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

public class RuwanwalimahasayaFragment extends Fragment implements OnMapReadyCallback {
    private LiveLocationTrackerActivity liveLocationTrackerActivity;
    private MapView mapViewRuwanwalimahasaya;
    private GoogleMap googleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liveLocationTrackerActivity = new LiveLocationTrackerActivity(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ruwanwalimahasaya, container, false);

        ///////////////////favorite button add or remove page in Favorite fragment/////////////
        AddRemoveFavoriteActivity.setupFavoriteButton(getActivity(), view, "Ruwanwali Mahasaya");

        // Initialize MapView
        mapViewRuwanwalimahasaya = view.findViewById(R.id.mapViewRuwanwalisaya);
        mapViewRuwanwalimahasaya.onCreate(savedInstanceState);

        // Call method to start playing video
        VideoView videoView = view.findViewById(R.id.vedioRuwanwalisaya);
        VideoPlayerActivity videoPlayer = new VideoPlayerActivity(getActivity(), videoView);
        videoPlayer.playVideo(R.raw.jaffnafort);

        // Get reference to GoogleMap
        mapViewRuwanwalimahasaya.getMapAsync(this);

        //  "More Details" button
        Button btnOpenUrl = view.findViewById(R.id.btnMoreDetilsRuwanwalisaya);
        btnOpenUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://cyelontaveler.blogspot.com/";
                openUrl(url);
            }
        });

        // booking button
        Button btnBooking = view.findViewById(R.id.btnBookingDetilsRuwanwalisaya);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.booking.com/searchresults.html?ss=Ruwanweli+Maha+Seya%2C+Abhayawewa+Road%2C+Anuradhapura%2C+Sri+Lanka&ssne=Jaffna+Fort&ssne_untouched=Jaffna+Fort&label=msn-5v4p76R4b4u2dySli_cXGQ-80195722701371%3Atikwd-17155438432%3Aloc-36%3Aneo%3Amtp%3Alp147906%3Adec%3Aqsboking%2B.com%2B%2Bnagadeepaya%2B&aid=393655&lang=en-us&sb=1&src_elem=sb&src=searchresults&dest_id=ChIJQRJv0Zb1_DoRjWifejv6EvQ&dest_type=landmark&place_id=ChIJQRJv0Zb1_DoRjWifejv6EvQ&latitude=8.3500293&longitude=80.3963687&ac_position=0&ac_click_type=g&ac_langcode=en-us&ac_suggestion_list_length=5&search_selected=true&search_pageview_id=34574ef49dd401aa&checkin=2023-08-04&checkout=2023-08-05&group_adults=2&no_rooms=1&group_children=0";
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
        LatLng Ruwanwalimahasaya = new LatLng(8.350209748411897, 80.39639015287986);
        googleMap.addMarker(new MarkerOptions().position(Ruwanwalimahasaya).title("Ruwanwalimahasaya"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Ruwanwalimahasaya, 10));
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
        mapViewRuwanwalimahasaya.onResume();
        liveLocationTrackerActivity.startLocationUpdates();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapViewRuwanwalimahasaya.onPause();
        liveLocationTrackerActivity.startLocationUpdates();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapViewRuwanwalimahasaya.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapViewRuwanwalimahasaya.onLowMemory();
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
