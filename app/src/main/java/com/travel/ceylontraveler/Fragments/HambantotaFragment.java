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
import com.travel.ceylontraveler.Methods.AcessPermisson;
import com.travel.ceylontraveler.Methods.AddRemoveFavoriteActivity;
import com.travel.ceylontraveler.Methods.PublicMethodsActivity;
import com.travel.ceylontraveler.R;
import com.travel.ceylontraveler.Methods.VideoPlayerActivity;


public class HambantotaFragment extends Fragment implements OnMapReadyCallback {
    private Button directionsButton;
    private MapView mapViewHambantota;
    private VideoView videoView;
    private GoogleMap googleMap;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_hambantota, container, false);

        ///////////////////favorite button add or remove page in Favorite fragment/////////////
        AddRemoveFavoriteActivity.setupFavoriteButton(getActivity(), view, "Hambantota");

        //create buttons navigate scrrens
        Button  btnYala = view.findViewById(R.id.btnYala);
        Button btnKatharagama=view.findViewById(R.id.btnKatharagama);
        Button btnKirinda=view.findViewById(R.id.btnKirinda);

        btnYala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublicMethodsActivity.displayFragment(HambantotaFragment.this, new YalaFragment());
            }
        });
        btnKatharagama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublicMethodsActivity.displayFragment(HambantotaFragment.this, new KatharagamaFragment());
            }
        });
        btnKirinda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublicMethodsActivity.displayFragment(HambantotaFragment.this, new KirindaFragment());
            }
        });


        ////////////////////////vedioView///////////////////////////
        VideoView videoView = view.findViewById(R.id.videoHambantota);
        VideoPlayerActivity videoPlayer = new VideoPlayerActivity(getActivity(), videoView);
        videoPlayer.playVideo(R.raw.hambantota);

        ////////////////////////vedioView///////////////////////////


        // Initialize the mapViewKandy
        mapViewHambantota = view.findViewById(R.id.mapViewHambantota);
        mapViewHambantota.onCreate(savedInstanceState);
        mapViewHambantota.getMapAsync(this);

        // Find the directions button and set its click listener.
        directionsButton = view.findViewById(R.id.getDirectionHambantota);
        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the directions activity.
                startDirectionsActivity();
            }
        });
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!AcessPermisson.hasLocationPermission((FragmentActivity) context)) {
            ////////////// Request location permission///////
            AcessPermisson.requestLocationPermission(getActivity());
        }
    }
    //////////////map display/////////////////////
    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        googleMap = map;
        LatLng Hambantota = new LatLng(6.14260033513632, 81.12146840923111);
        googleMap.addMarker(new MarkerOptions().position(Hambantota).title("Hambantota"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Hambantota, 9));

        // Add zoom controls to the map and position them on the left side
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.isCompassEnabled();

    }
    @Override
    public void onResume() {
        super.onResume();
        mapViewHambantota.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapViewHambantota.onPause();
    }
//////////////map display/////////////////////

    public void startDirectionsActivity() {
        // Get the destination address or coordinates from the user.
        String destination = "Hambantota";
        // Build the URI for the directions intent.
        Uri uri = Uri.parse("google.navigation:q=" + destination);
        // Create the intent and start the activity.
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

}