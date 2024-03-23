package com.example.projet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    private TextView textViewAnnouncementCount;
    private AnnouncementDatabaseHelper announcementDBHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        textViewAnnouncementCount = findViewById(R.id.textViewAnnouncementCount);
        announcementDBHelper = new AnnouncementDatabaseHelper(this);

        String selectedCity = getIntent().getStringExtra("selected_city");
        updateAnnouncementCount(selectedCity);
    }

    private void updateAnnouncementCount(String city) {
        int announcementCount = announcementDBHelper.getAnnouncementCountForCity(city);
        textViewAnnouncementCount.setText("Il y a actuellement " + announcementCount + " annonces pour " + city + ".");
    }
}
