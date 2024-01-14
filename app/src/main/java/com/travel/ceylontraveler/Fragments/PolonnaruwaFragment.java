package com.travel.ceylontraveler.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
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


public class PolonnaruwaFragment extends Fragment implements OnMapReadyCallback {
    private Button directionsButton;
    private MapView mapViewPolonnruwa;
    private VideoView videoView;
    private GoogleMap googleMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_polonnaruwa, container, false);

        ///////////////////favorite button add or remove page in Favorite fragment/////////////
        AddRemoveFavoriteActivity.setupFavoriteButton(getActivity(), view, "Polonnaruwa");



        ////////////////////////vedioView///////////////////////////
        videoView = view.findViewById(R.id.videoPolonnaruwa);
        Uri videoUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.polonnaruwa);
        videoView.setVideoURI(videoUri);
        videoView.start();
        MediaController mediaController = new MediaController(getActivity());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        ////////////////////////vedioView///////////////////////////


        // Initialize the mapViewPolonnruwa
        mapViewPolonnruwa = view.findViewById(R.id.mapViewPolonnaruwa);
        mapViewPolonnruwa.onCreate(savedInstanceState);
        mapViewPolonnruwa.getMapAsync(this);

        // Find the directions button and set its click listener.
        directionsButton = view.findViewById(R.id.getDirectionPolonnaruwa);
        //create buttons navigate scrrens
        Button btnGalviharaya = view.findViewById(R.id.btnGalviharaya);
        Button btnkingparakarama=view.findViewById(R.id.btnkingparakarama);
        Button btnPalace=view.findViewById(R.id.btnPalace);

        btnGalviharaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublicMethodsActivity.displayFragment(PolonnaruwaFragment.this, new GalviharayaFragment());
            }
        });

        btnkingparakarama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublicMethodsActivity.displayFragment(PolonnaruwaFragment.this, new KingParakramabahuFragment());
            }
        });
        btnPalace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublicMethodsActivity.displayFragment(PolonnaruwaFragment.this, new AncientpalaceFragment());
            }
        });
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
        LatLng Polonnaruwa = new LatLng(7.940401897851641,   81.01678225047526);
        googleMap.addMarker(new MarkerOptions().position(Polonnaruwa).title("Polonnaruwa"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Polonnaruwa, 9));

        // Add zoom controls to the map and position them on the left side
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.isCompassEnabled();

    }
    @Override
    public void onResume() {
        super.onResume();
        mapViewPolonnruwa.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapViewPolonnruwa.onPause();
    }
//////////////map display/////////////////////

    public void startDirectionsActivity() {
        // Get the destination address or coordinates from the user.
        String destination = "Polonnaruwa";
        // Build the URI for the directions intent.
        Uri uri = Uri.parse("google.navigation:q=" + destination);
        // Create the intent and start the activity.
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

}