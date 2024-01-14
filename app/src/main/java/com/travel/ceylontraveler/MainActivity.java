package com.travel.ceylontraveler;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;
import com.travel.ceylontraveler.Database.DatabaseHelper;
import com.travel.ceylontraveler.Fragments.AccountStatusFragment;
import com.travel.ceylontraveler.Fragments.FavoriteFragment;
import com.travel.ceylontraveler.Fragments.HomeFragment;
import com.travel.ceylontraveler.Fragments.LoginFragment;
import com.travel.ceylontraveler.Fragments.SearchFragment;
import com.travel.ceylontraveler.Fragments.SettingsFragment;
import com.travel.ceylontraveler.databinding.ActivityMainBinding;

// ...

// ...

public class MainActivity extends AppCompatActivity {
    private Switch aSwitch;
    private ActivityMainBinding binding;
    private DatabaseHelper databaseHelper;
    private boolean isSwitchChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this); // Initialize DatabaseHelper

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout, new LoginFragment())
                    .commit();
            replaceFragment(new LoginFragment());
        }

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.search:
                        replaceFragment(new SearchFragment());
                        break;
                    case R.id.favorite:
                        replaceFragment(new FavoriteFragment());
                        break;
                    case R.id.user:
                        replaceFragment(new AccountStatusFragment());
                        break;
                    case R.id.settings:
                        replaceFragment(new SettingsFragment());
                        break;
                }
                return true;
            }
        });

        aSwitch = findViewById(R.id.darkModeSwitch); //dark mode light mode switch
        isSwitchChecked = isDarkModeEnabled(); // Load the saved switch state

        aSwitch.setChecked(isSwitchChecked); // Set the initial switch state

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSwitchChecked = isChecked; // Save the current switch state
                saveDarkModeEnabled(isChecked); // Persist the switch state
                if (isChecked) {
                    applyDayNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    applyDayNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void applyDayNightMode(int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }
    //save data sharedPreferences
    private void saveDarkModeEnabled(boolean isEnabled) {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("dark_mode_enabled", isEnabled);
        editor.apply();
    }

    // get save data form sharedPrefrences feature
    private boolean isDarkModeEnabled() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        return preferences.getBoolean("dark_mode_enabled", false);
    }

}

