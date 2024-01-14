package com.travel.ceylontraveler.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.travel.ceylontraveler.Database.DatabaseHelper;
import com.travel.ceylontraveler.R;

public class LoginFragment extends Fragment {
    private DatabaseHelper databaseHelper;
    private static final String PREFS_KEY = "MyPrefs";//Shared Preferences key
    private static final String PREFS_IS_LOGGED_IN_KEY = "isLoggedIn";
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private Button signupButton;

    public LoginFragment() {
        // Required empty public constructor
    }
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        databaseHelper = new DatabaseHelper(getActivity());

        usernameEditText=view.findViewById(R.id.usernameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        loginButton = view.findViewById(R.id.loginButton);
        signupButton = view.findViewById(R.id.signupButton);

         // Add animation to the signup button
      // signupButton.animate().scaleX(1.2f).scaleY(1.2f).setDuration(1000).withEndAction(new Runnable() {
//            @Override
//            public void run() {
//                signupButton.animate().scaleX(1f).scaleY(1f).setDuration(1000).start();
//            }
//        }).start(); // Add animation to the signup button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Geting user enter password and username
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                                                                                     //Validate username and password fields//
                if (username.isEmpty()) {
                    usernameEditText.setError("Please enter your username");
                    return;
                }
                if (password.isEmpty()) {
                    passwordEditText.setError("Please enter your password");
                    return;
                }

                /////Check user exists in the database for get login
                if (databaseHelper.isUserExist(username, password)) {
                    // perform login action
                    setLoggedInState(true); // save isLoggedIn state
                    BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
                    bottomNavigationView.setVisibility(View.VISIBLE); // show bottom navigation bar
                    replaceFragment(new HomeFragment()); // go to home screen
                } else {
                    Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signupButton = view.findViewById(R.id.signupButton); //navigate sigup scrren
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new SignupFragment());
            }
        });

        return view;
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean(PREFS_IS_LOGGED_IN_KEY, false);

        if (isLoggedIn) {
            // user is already logged in
            replaceFragment(new HomeFragment());
        } else {
            // user is not logged in, show login form
        }
        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setVisibility(View.GONE);
    }

    ////save isLoggedIn state method
    private void setLoggedInState(boolean isLoggedIn) {
        try {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(PREFS_IS_LOGGED_IN_KEY, isLoggedIn);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
