package com.travel.ceylontraveler.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.travel.ceylontraveler.R;

public class SettingsFragment extends Fragment {
    public SettingsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        TextView favoriteTextView = view.findViewById(R.id.tFavorite);
        favoriteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavoriteFragment favoriteFragment = new FavoriteFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.replace(R.id.fragment_container, favoriteFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        TextView versionTextView = view.findViewById(R.id.tVersion);
        versionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String version = "Version - 1.0";

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("App Version")
                        .setMessage(version)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        TextView privacyTextView = view.findViewById(R.id.tPrivacy);
        privacyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String privacyPolicy = "Welcome to cyelon traveler " +
                        "travel app to plan and experience tours " +
                        "all over Sri Lanka. Our app is designed to provide seamless " +
                        "travel solutions and personalized experiences " +
                        "even when you don't have an internet connection, all while prioritizing" +
                        " your privacy and data security.";

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Privacy Policy")
                        .setMessage(privacyPolicy)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        TextView aboutTextView = view.findViewById(R.id.tAbout);
        aboutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aboutMessage = "\"Ceylon Traveler\" is a travel " +
                        "app that provides information for travelers" +
                        " visiting Sri Lanka. It offers details about tourist places " +
                        "in Sri Lanka through videos, images, and short descriptions." +
                        " One of its notable features is the ability to use the app without" +
                        " an internet connection. Additionally, the app includes a dark mode for " +
                        "better visibility in low-light" +
                        " conditions. The current " +
                        "version of the app is 1.0.\n\n" +
                        "Contact Details:\n" +
                        "Name: Heshitha Sandeepa\n" +
                        "Email: hesithedg@gmail.com";

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("About")
                        .setMessage(aboutMessage)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }
}
