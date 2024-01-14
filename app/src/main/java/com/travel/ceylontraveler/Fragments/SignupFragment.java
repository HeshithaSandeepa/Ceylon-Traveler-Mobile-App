package com.travel.ceylontraveler.Fragments;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.travel.ceylontraveler.Database.DatabaseHelper;
import com.travel.ceylontraveler.R;

public class SignupFragment extends Fragment {

    private EditText emailEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signupButton;
    private DatabaseHelper dbHelper;
    private CheckBox showPasswordCheckBox;

    public SignupFragment() {
        // Required empty public constructor
    }
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        dbHelper = new DatabaseHelper(getActivity());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signup, container, false);
        emailEditText = root.findViewById(R.id.emailEditText);
        usernameEditText = root.findViewById(R.id.usernameEditText);
        passwordEditText = root.findViewById(R.id.passwordEditText);
        signupButton = root.findViewById(R.id.signupButton);
        showPasswordCheckBox = root.findViewById(R.id.showPasswordCheckBox);

        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // show password check box
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password check box
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty()) {
                    emailEditText.setError("Email is required");
                    emailEditText.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {  //validate email
                    emailEditText.setError("Please enter a valid email");
                    emailEditText.requestFocus();
                    return;
                }

                if (username.isEmpty()) {
                    usernameEditText.setError("Username is required");
                    usernameEditText.requestFocus();
                    return;
                }if (!username.matches("^[a-zA-Z0-9]+$")) {
                    usernameEditText.setError("Username cannot contain special characters");
                    return;
                }

                if (password.isEmpty()) {
                    passwordEditText.setError("Password is required");
                    passwordEditText.requestFocus();
                    return;
                } if (!password.matches(".*[A-Z].*")) {
                    passwordEditText.setError("Password should be at least one uppercase letter");
                    return;
                }
                if (password.length() <= 5) {
                    passwordEditText.setError("Password should be at 5 characters");
                    return;
                }
                if (!password.matches(".*[a-z].*")) {
                    passwordEditText.setError("Password should be at least one lowercase letter");
                    return;
                } if (!password.matches(".*[@#$%^&+=].*")) {
                    passwordEditText.setError("password should be at least one special character");
                    return;
                }

                boolean isInserted = dbHelper.insertLoginDetails(email, username, password);
                if (isInserted) {
                    Toast.makeText(getActivity(), "SigunUp successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Signup failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }
}
