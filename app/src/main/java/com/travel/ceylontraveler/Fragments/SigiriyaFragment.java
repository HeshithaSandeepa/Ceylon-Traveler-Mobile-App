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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.travel.ceylontraveler.Methods.AcessPermisson;
import com.travel.ceylontraveler.Methods.AddRemoveFavoriteActivity;
import com.travel.ceylontraveler.R;

public class SigiriyaFragment extends Fragment implements OnMapReadyCallback {
    private  Button getDecartionToSigiriya;
    private VideoView videoView;
    private GoogleMap googleMap;
    private MapView mapViewSigiriya;

    public SigiriyaFragment() {
        // Required empty public constructor
    }
    public static SigiriyaFragment newInstance(String param1, String param2) {
        SigiriyaFragment fragment = new SigiriyaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sigiriya, container, false);


        ///////////////////favorite button add or remove page in Favorite fragment/////////////
        AddRemoveFavoriteActivity.setupFavoriteButton(getActivity(), view, "Sigiriya");

        // Initialize the videoView
        videoView = view.findViewById(R.id.videoSigiriya);
        Uri videoUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.sigiriya);
        videoView.setVideoURI(videoUri);
        videoView.start();
        MediaController mediaController = new MediaController(getActivity());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        // Initialize the mapViewSigiriya
        mapViewSigiriya = view.findViewById(R.id.mapViewSigiriya);
        mapViewSigiriya.onCreate(savedInstanceState);
        mapViewSigiriya.getMapAsync(this);

        // more details button
        Button btnOpenUrl = view.findViewById(R.id.btnMoreDetilsSigiriya);
        btnOpenUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://cyelontaveler.blogspot.com/";
                openUrl(url);
            }
        });

        // booking button
        Button btnBooking = view.findViewById(R.id.btnBookingDetilsSigiriya);
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.booking.com/searchresults.html?ss=Sigiriya%2C+Matale+District%2C+Sri+Lanka&ssne=Kiri+Vehera%2C+Kataragama%2C+Sri+Lanka&ssne_untouched=Kiri+Vehera%2C+Kataragama%2C+Sri+Lanka&efdco=1&label=gen173nr-1FCAEoggI46AdIM1gEaIUBiAEBmAExuAEXyAEP2AEB6AEB-AECiAIBqAIDuAL53e6jBsACAdICJGEyMzZkNjE1LTY4OTctNGUxOS1iOGQ5LWE2MDg5M2ZiMmFmONgCBeACAQ&aid=304142&lang=en-us&sb=1&src_elem=sb&src=searchresults&dest_id=-2235832&dest_type=city&ac_position=0&ac_click_type=b&ac_langcode=en&ac_suggestion_list_length=5&search_selected=true&search_pageview_id=fc9b198ed69e00f4&ac_meta=GhBmYzliMTk4ZWQ2OWUwMGY0IAAoATICZW46CFNpZ2lyaXlhQABKAFAA&group_adults=2&no_rooms=1&group_children=0";
                openUrl(url);
            }
        });

        // Find the directions button and set its click listener.
        getDecartionToSigiriya = view.findViewById(R.id.getDirectionSigiriya);
        getDecartionToSigiriya.setOnClickListener(new View.OnClickListener() {
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
        LatLng Sigiriya = new LatLng(7.957567845840655,   80.75590806328717);
        googleMap.addMarker(new MarkerOptions().position(Sigiriya).title("Sigiriya"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Sigiriya, 8));
    }
    @Override
    public void onResume() {
        super.onResume();
        mapViewSigiriya.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapViewSigiriya.onPause();
    }
//////////////map display/////////////////////
private void openUrl(String url) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(url));
    startActivity(intent);
}
    public void startDirectionsActivity() {
        // Get the destination address or coordinates from the user.
        String destination = "Sigiriya";
        // Build the URI for the directions intent.
        Uri uri = Uri.parse("google.navigation:q=" + destination);
        // Create the intent and start the activity.
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

}