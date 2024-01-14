package com.travel.ceylontraveler.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.travel.ceylontraveler.Database.DatabaseHelper;
import com.travel.ceylontraveler.MainActivity;
import com.travel.ceylontraveler.R;



public class AccountStatusFragment extends Fragment {
    Button btnLogout;
    Button btnDeleteAccount;
    private FragmentManager mFragmentManager;

    public AccountStatusFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_status, container, false);

// Initialize the delete account button
        btnDeleteAccount = view.findViewById(R.id.btnDeleteAccount);
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccount();
            }
        });


        // Initialize the FragmentManager object
        mFragmentManager = getParentFragmentManager();

        // Initialize the logout button
        btnLogout = view.findViewById(R.id.btnLogout);
        btnDeleteAccount = view.findViewById(R.id.btnDeleteAccount);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset login details by clearing saved preferences or tokens
                SharedPreferences preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();

                // Restart the app and navigate to the LoginFragment
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        // Get the username of the logged-in user from the SQLite database
        String username = "";
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {DatabaseHelper.COLUMN_3};
        String selection = DatabaseHelper.COLUMN_IS_LOGGED_IN + " = ?";
        String[] selectionArgs = {"1"};
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_3));
        }
        cursor.close();
        db.close();

// Display the username in a TextView in UserAccountStatusFragment

        TextView userName = view.findViewById(R.id.accountName);
        if (username != null && !username.isEmpty()) {
            userName.setText(username);
        } else {
            userName.setText("No username found");
        }

        return view;
    }
    private void deleteAccount() {
        // Get the current user's id
        SharedPreferences preferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        long id = preferences.getLong("id", -1);

        // Delete the user account from the database using the retrieved user ID
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        dbHelper.deleteAccount(id);
    }





}


