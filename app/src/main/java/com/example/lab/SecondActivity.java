package com.example.lab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private String selectedStudyLevel = "";
    private String selectedSpecialty = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnCollege = findViewById(R.id.btnCollege);
        Button btnBachelor = findViewById(R.id.btnBachelor);
        Button btnMaster = findViewById(R.id.btnMaster);
        Button btnDoctorate = findViewById(R.id.btnDoctorate);
        RadioGroup radioGroup = findViewById(R.id.radioGroupStudyForm);
        Button btnChooseSpecialty = findViewById(R.id.btnChooseSpecialty);
        CheckBox checkEnglish = findViewById(R.id.checkEnglish);
        CheckBox checkFrench = findViewById(R.id.checkFrench);
        EditText etOtherLanguage = findViewById(R.id.etOtherLanguage);
        Button btnOk = findViewById(R.id.btnOkSecond);
        Button btnCancel = findViewById(R.id.btnCancelSecond);

        // Selectare nivel studii
        View.OnClickListener studyLevelListener = v -> {
            selectedStudyLevel = ((Button) v).getText().toString();
            btnCollege.setBackgroundColor(0xFFD3D3D3);
            btnBachelor.setBackgroundColor(0xFFD3D3D3);
            btnMaster.setBackgroundColor(0xFFD3D3D3);
            btnDoctorate.setBackgroundColor(0xFFD3D3D3);
            v.setBackgroundColor(0xFF00FF00);
        };

        btnCollege.setOnClickListener(studyLevelListener);
        btnBachelor.setOnClickListener(studyLevelListener);
        btnMaster.setOnClickListener(studyLevelListener);
        btnDoctorate.setOnClickListener(studyLevelListener);

        // Selectare specialitate
        btnChooseSpecialty.setOnClickListener(v -> {
            String[] specialities = {"Informatica", "Design", "Drept", "Medicina", "Limbi străine"};
            new AlertDialog.Builder(this)
                    .setTitle("Alege specialitatea")
                    .setItems(specialities, (dialog, which) -> {
                        selectedSpecialty = specialities[which];
                        // Actualizează textul butonului cu specialitatea selectată
                        btnChooseSpecialty.setText(selectedSpecialty);
                        // Setează culoarea de fundal verde pentru buton
                        btnChooseSpecialty.setBackgroundColor(0xFF00FF00);
                    })
                    .show();
        });

        // Buton OK -> Mergem la ThirdActivity
        btnOk.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            intent.putExtra("STUDY_LEVEL", selectedStudyLevel);

            int selectedId = radioGroup.getCheckedRadioButtonId();
            String studyForm = selectedId == R.id.radioDay ? "Zi" : "FR";
            intent.putExtra("STUDY_FORM", studyForm);

            intent.putExtra("SPECIALTY", selectedSpecialty);

            StringBuilder languages = new StringBuilder();
            if (checkEnglish.isChecked()) languages.append("Engleză, ");
            if (checkFrench.isChecked()) languages.append("Franceză, ");
            if (!etOtherLanguage.getText().toString().trim().isEmpty()) {
                languages.append(etOtherLanguage.getText().toString().trim()).append(", ");
            }
            if (languages.length() > 0) {
                languages.setLength(languages.length() - 2); // Eliminăm ultima virgulă
            }

            intent.putExtra("LANGUAGES", languages.toString());

            startActivity(intent);
        });

        // Buton Cancel -> Resetare
        btnCancel.setOnClickListener(v -> finish());
    }
}
