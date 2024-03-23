package com.example.projet;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword, editTextConfirmPassword;
    Button buttonCreateAccount;
    MyDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        editTextEmail = findViewById(R.id.edit_email);
        editTextPassword = findViewById(R.id.edit_password);
        editTextConfirmPassword = findViewById(R.id.edit_confirm_password);
        buttonCreateAccount = findViewById(R.id.button_create_account);
        databaseHelper = new MyDatabaseHelper(this);

        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        Spinner spinnerCity = findViewById(R.id.spinner_city);
        // Création de la liste de villes
        List<String> cities = new ArrayList<>();
        cities.add("Agadir");
        cities.add("Casablanca");
        cities.add("Settat");
        cities.add("Marrakech");
        cities.add("Rabat");

// Création de l'adaptateur pour la barre déroulante
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Assignation de l'adaptateur à la barre déroulante
        spinnerCity.setAdapter(adapter);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (databaseHelper.addUser(email, password)) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, MainActivity3.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Registration failed. Email already exists.", Toast.LENGTH_SHORT).show();
        }
    }
}
