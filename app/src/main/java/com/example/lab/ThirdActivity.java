package com.example.lab;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Preluăm referințele la TextView-urile din layout
        TextView tvFullName = findViewById(R.id.tvFullName);
        TextView tvStudyLevel = findViewById(R.id.tvStudyLevel);
        TextView tvStudyForm = findViewById(R.id.tvStudyForm);
        TextView tvSpecialty = findViewById(R.id.tvSpecialty);
        TextView tvLanguages = findViewById(R.id.tvLanguages);

        // Preluăm datele din SharedPreferences (datele din MainActivity)
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String firstName = sharedPreferences.getString("firstName", "N/A");
        String lastName = sharedPreferences.getString("lastName", "N/A");
        String fullName = firstName + " " + lastName;

        // Preluăm datele trimise din SecondActivity prin Intent
        String studyLevel = getIntent().getStringExtra("STUDY_LEVEL");
        String studyForm = getIntent().getStringExtra("STUDY_FORM");
        String specialty = getIntent().getStringExtra("SPECIALTY");
        String languages = getIntent().getStringExtra("LANGUAGES");

        // Actualizăm TextView-urile cu datele corespunzătoare
        tvFullName.setText("Nume: " + fullName);
        tvStudyLevel.setText("Nivel de studii: " + studyLevel);
        tvStudyForm.setText("Forma de studii: " + studyForm);
        tvSpecialty.setText("Specialitate: " + specialty);
        tvLanguages.setText("Limbi străine: " + languages);

        // Butonul pentru revenire
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }
}
