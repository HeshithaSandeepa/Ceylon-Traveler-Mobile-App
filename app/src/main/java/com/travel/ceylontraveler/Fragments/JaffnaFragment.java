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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.travel.ceylontraveler.Methods.AcessPermisson;
import com.travel.ceylontraveler.Methods.AddRemoveFavoriteActivity;
import com.travel.ceylontraveler.Methods.EnableCurrentLocationButtonActivity;
import com.travel.ceylontraveler.Methods.PublicMethodsActivity;
import com.travel.ceylontraveler.Methods.VideoPlayerActivity;
import com.travel.ceylontraveler.R;


public class JaffnaFragment extends Fragment implements OnMapReadyCallback {
    private Button directionsButton;
    private MapView mapViewJaffna;

    private GoogleMap googleMap;

    public JaffnaFragment() {
        // Required empty public constructor
    }
    private void addFavoriteScreen() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_jaffna, container, false);


        ///////////////////favorite button add or remove page in Favorite fragment/////////////
        AddRemoveFavoriteActivity.setupFavoriteButton(getActivity(), view, "Jaffna");

        ////////////////////////vedioView///////////////////////////
        // call method  to start playing video
        VideoView videoView = view.findViewById(R.id.videoJaffna);
        VideoPlayerActivity videoPlayer = new VideoPlayerActivity(getActivity(), videoView);
        videoPlayer.playVideo(R.raw.jaffna);

        ////////////////////////vedioView///////////////////////////


        // Initialize the mapViewjaffna
        mapViewJaffna = view.findViewById(R.id.mapViewJaffna);
        mapViewJaffna.onCreate(savedInstanceState);
        mapViewJaffna.getMapAsync(this);

        // Find the directions button and set its click listener.
        directionsButton = view.findViewById(R.id.getDirectionJaffna);
        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the directions activity.
                startDirectionsActivity();
            }
        });

        ////////////Click Button ---ViewDetails--- and Go another Screen////////////////////////////

        Button btnNagadeepaya = view.findViewById(R.id.btnNagadeepaya);
        btnNagadeepaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublicMethodsActivity.displayFragment(JaffnaFragment.this, new NagadeepayaFragment());
            }
        });

        Button btnbtnNallur = view.findViewById(R.id.btnNallur);
        btnbtnNallur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublicMethodsActivity.displayFragment(JaffnaFragment.this, new NallurFragment());
            }
        });

        Button btnJaffnaFort = view.findViewById(R.id.btnJaffnaFort);
        btnJaffnaFort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublicMethodsActivity.displayFragment(JaffnaFragment.this, new JaffnaFortFragment());
            }
        });


////////////Click Button ---ViewDetails--- and Go another Screen////////////////////////////

        // Find the video button and set its click listener.
        Button btnVedio = view.findViewById(R.id.btnVedio);
        btnVedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the full screen video fragment.
                openVedioFullScreen();
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
        LatLng Jaffna = new LatLng(9.66160782714345, 80.02550671063467);
        googleMap.addMarker(new MarkerOptions().position(Jaffna).title("Jaffna"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Jaffna, 9));

        // Add zoom controls to the map and position
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.isCompassEnabled();

        EnableCurrentLocationButtonActivity.enableMyLocation(getActivity(), googleMap);
    }
    @Override
    public void onResume() {
        super.onResume();
        mapViewJaffna.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapViewJaffna.onPause();
    }
//////////////map display/////////////////////

    public void startDirectionsActivity() {
        // Get the destination address or coordinates from the user.
        String destination = "Jaffna";
        // Build the URI for the directions intent.
        Uri uri = Uri.parse("google.navigation:q=" + destination);
        // Create the intent and start the activity.
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
    // openFullScreenVedioFragment() method

    private void openVedioFullScreen() {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FullScreenVedioFragment fragment = new FullScreenVedioFragment();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




}