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

public class KingParakramabahuFragment extends Fragment implements OnMapReadyCallback {
    private LiveLocationTrackerActivity liveLocationTrackerActivity;
    private MapView mapViewStatue;
    private GoogleMap googleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liveLocationTrackerActivity = new LiveLocationTrackerActivity(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_king_parakramabahu, container, false);

        ///////////////////favorite button add or remove page in Favorite fragment/////////////
        AddRemoveFavoriteActivity.setupFavoriteButton(getActivity(), view, "King Parakramabahu Statue");
        // Initialize MapView
        mapViewStatue = view.findViewById(R.id.mapViewParakramabahuStatue);
        mapViewStatue.onCreate(savedInstanceState);

        // Call method to start playing video
        VideoView videoView = view.findViewById(R.id.vedioParakramabahuStatue);
        VideoPlayerActivity videoPlayer = new VideoPlayerActivity(getActivity(), videoView);
        videoPlayer.playVideo(R.raw.parakramabau);

        // Get reference to GoogleMap
        mapViewStatue.getMapAsync(this);

        //  "More Details" button
        Button btnOpenUrl = view.findViewById(R.id.btnMoreDetilsParakramabahuStatue);
        btnOpenUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://cyelontaveler.blogspot.com/";
                openUrl(url);
            }
        });

        // booking button
        Button btnBooking = view.findViewById(R.id.btnBookingDetilsParakramabahuStatue);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.booking.com/searchresults.html?ss=Pothgul+Viharaya%2C+Bund+Road%2C+Polonnaruwa%2C+Sri+Lanka&ssne=Gal+Viharaya&ssne_untouched=Gal+Viharaya&efdco=1&label=gen173nr-1FCAEoggI46AdIM1gEaIUBiAEBmAExuAEXyAEP2AEB6AEB-AECiAIBqAIDuAL53e6jBsACAdICJGEyMzZkNjE1LTY4OTctNGUxOS1iOGQ5LWE2MDg5M2ZiMmFmONgCBeACAQ&aid=304142&lang=en-us&sb=1&src_elem=sb&src=searchresults&dest_id=ChIJeRbTpslE-zoRI7DORrXYefc&dest_type=landmark&place_id=ChIJeRbTpslE-zoRI7DORrXYefc&latitude=7.9245435&longitude=80.9945329&ac_position=2&ac_click_type=g&ac_langcode=en-us&ac_suggestion_list_length=5&search_selected=true&search_pageview_id=ff15977b52db04c5&group_adults=2&no_rooms=1&group_children=0";
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
        LatLng ParakramabahuStatue = new LatLng(7.92649041876565, 80.99476859338438);
        googleMap.addMarker(new MarkerOptions().position( ParakramabahuStatue).title(" Statue of King Parakramabahu"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom( ParakramabahuStatue, 10));
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
        mapViewStatue.onResume();
        liveLocationTrackerActivity.startLocationUpdates();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapViewStatue.onPause();
        liveLocationTrackerActivity.startLocationUpdates();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapViewStatue.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapViewStatue.onLowMemory();
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
