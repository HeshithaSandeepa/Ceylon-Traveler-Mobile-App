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

public class IsurumuniyaFragment extends Fragment implements OnMapReadyCallback {
    private LiveLocationTrackerActivity liveLocationTrackerActivity;
    private MapView mapViewIsurumuniya;
    private GoogleMap googleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liveLocationTrackerActivity = new LiveLocationTrackerActivity(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_isurumuniya, container, false);

        ///////////////////favorite button add or remove page in Favorite fragment/////////////
        AddRemoveFavoriteActivity.setupFavoriteButton(getActivity(), view, "Isurumuniya");

        // Initialize MapView
        mapViewIsurumuniya = view.findViewById(R.id.mapViewIsurumuniya);
        mapViewIsurumuniya.onCreate(savedInstanceState);

        // Call method to start playing video
        VideoView videoView = view.findViewById(R.id.vedioIsurumuniya);
        VideoPlayerActivity videoPlayer = new VideoPlayerActivity(getActivity(), videoView);
        videoPlayer.playVideo(R.raw.isurumuniya);

        // Get reference to GoogleMap
        mapViewIsurumuniya.getMapAsync(this);

        //  "More Details" button
        Button btnOpenUrl = view.findViewById(R.id.btnMoreDetilsIsurumuniya);
        btnOpenUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://cyelontaveler.blogspot.com/";
                openUrl(url);
            }
        });

        // booking button
        Button btnBooking = view.findViewById(R.id.btnBookingDetilsIsurumuniya);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.booking.com/searchresults.html?ss=Isurumuniya+Rajamaha+Viharaya+-+%E0%B6%89%E0%B7%83%E0%B7%94%E0%B6%BB%E0%B7%94%E0%B6%B8%E0%B7%94%E0%B6%AB%E0%B7%92%E0%B6%BA+%E0%B6%BB%E0%B6%A2%E0%B6%B8%E0%B7%84%E0%B7%8F+%E0%B7%80%E0%B7%92%E0%B7%84%E0%B7%8F%E0%B6%BB%E0%B6%BA%2C+Anuradhapura%2C+Sri+Lanka&ssne=Ruwanweli+Maha+Seya%2C+Abhayawewa+Road%2C+Anuradhapura%2C+Sri+Lanka&ssne_untouched=Ruwanweli+Maha+Seya%2C+Abhayawewa+Road%2C+Anuradhapura%2C+Sri+Lanka&label=msn-5v4p76R4b4u2dySli_cXGQ-80195722701371%3Atikwd-17155438432%3Aloc-36%3Aneo%3Amtp%3Alp147906%3Adec%3Aqsboking%2B.com%2B%2Bnagadeepaya%2B&aid=393655&lang=en-us&sb=1&src_elem=sb&src=searchresults&dest_id=ChIJ2QpzKbj1_DoRkFt0Zuoq3is&dest_type=landmark&place_id=ChIJ2QpzKbj1_DoRkFt0Zuoq3is&latitude=8.334575&longitude=80.390255&ac_position=1&ac_click_type=g&ac_langcode=en-us&ac_suggestion_list_length=5&search_selected=true&search_pageview_id=a09f5330dcbd01f7&checkin=2023-08-04&checkout=2023-08-05&group_adults=2&no_rooms=1&group_children=0";
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
        LatLng Isurumuniya = new LatLng(8.334755455564627, 80.39022280870051);
        googleMap.addMarker(new MarkerOptions().position(Isurumuniya).title("Isurumuniya"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Isurumuniya, 10));
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
        mapViewIsurumuniya.onResume();
        liveLocationTrackerActivity.startLocationUpdates();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapViewIsurumuniya.onPause();
        liveLocationTrackerActivity.startLocationUpdates();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapViewIsurumuniya.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapViewIsurumuniya.onLowMemory();
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
