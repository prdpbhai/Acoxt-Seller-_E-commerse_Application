package com.example.acoxtseller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

public class practice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        EditText passwordEditText = findViewById(R.id.passwordEditText);
        ToggleButton toggleButton = findViewById(R.id.toggleButton);

        // Set an initial state for the password visibility
        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());

        // Toggle the password visibility when the button is clicked
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show the password
                    passwordEditText.setTransformationMethod(null);
                } else {
                    // Hide the password
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}