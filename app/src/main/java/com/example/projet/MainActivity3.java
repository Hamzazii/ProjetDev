package com.example.projet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private Spinner spinnerVille;
    private Button buttonSuivant;
    private AnnouncementDatabaseHelper announcementDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        spinnerVille = findViewById(R.id.spinnerVille);
        buttonSuivant = findViewById(R.id.buttonSuivant);
        announcementDBHelper = new AnnouncementDatabaseHelper(this);

        buttonSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCity = spinnerVille.getSelectedItem().toString();
                // Ajouter une annonce pour la ville sélectionnée
                announcementDBHelper.addAnnouncementForCity(selectedCity);

                // Passer à l'activité suivante
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                intent.putExtra("selected_city", selectedCity);
                startActivity(intent);
            }
        });

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
        spinnerVille.setAdapter(adapter);


    Spinner spinnerCategorie = findViewById(R.id.spinnerCategorie);
        Spinner spinnerSecteur = findViewById(R.id.SpinnerSecteur);

// Liste des catégories
        List<String> categories = new ArrayList<>();
        categories.add("Informatique");
        categories.add("Finance");
        categories.add("Marketing");
// Ajoutez d'autres catégories si nécessaire

// Création de l'adaptateur pour la catégorie
        ArrayAdapter<String> categorieAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categorieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Assignation de l'adaptateur à la catégorie Spinner
        spinnerCategorie.setAdapter(categorieAdapter);

// Liste des secteurs
        List<String> secteurs = new ArrayList<>();
        secteurs.add("Fabrication");
        secteurs.add("Commerce de détail");
        secteurs.add("Publicité");
// Ajoutez d'autres secteurs si nécessaire

// Création de l'adaptateur pour le secteur
        ArrayAdapter<String> secteurAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, secteurs);
        secteurAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Assignation de l'adaptateur au secteur Spinner
        spinnerSecteur.setAdapter(secteurAdapter);

    }
}




