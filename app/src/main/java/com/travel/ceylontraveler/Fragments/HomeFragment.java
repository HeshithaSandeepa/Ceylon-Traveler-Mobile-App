package com.travel.ceylontraveler.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.travel.ceylontraveler.R;

public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, //create link for  screens
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton button1 = (ImageButton)view.findViewById(R.id.button1);
        ImageButton button2=(ImageButton)view.findViewById(R.id.button2);
        ImageButton button3=(ImageButton)view.findViewById(R.id.button3);
        ImageButton button4=(ImageButton)view.findViewById(R.id.button4);
        ImageButton button5=(ImageButton)view.findViewById(R.id.button5);
        ImageButton button6=(ImageButton)view.findViewById(R.id.button6);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new JaffnaFragment()); }  });              /////////////////          JAFFFNA

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                  /////////                         Anuradapuraya
                replaceFragment(new AnuradapurayaFragment()); }  });

        button3.setOnClickListener(new View.OnClickListener() {   ///////////////            Polonnaruwa
            @Override
            public void onClick(View v) {
                replaceFragment(new PolonnaruwaFragment()); }  });

        button4.setOnClickListener(new View.OnClickListener() { ///////////////            Sigiriya
            @Override
            public void onClick(View v) {
                replaceFragment(new SigiriyaFragment()); }  });

        button5.setOnClickListener(new View.OnClickListener() {///////////////            Kandy
            @Override
            public void onClick(View v) {
                replaceFragment(new KandyFragment()); }  });

        button6.setOnClickListener(new View.OnClickListener() {///////////////           Hambantota
            @Override
            public void onClick(View v) {
                replaceFragment(new HambantotaFragment()); }  });
        return view;
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
}
