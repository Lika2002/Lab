package com.example.lab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etFirstName, etLastName, etBirthDate;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etBirthDate = findViewById(R.id.etBirthDate);
        Button btnOk = findViewById(R.id.btnOk);
        Button btnCancel = findViewById(R.id.btnCancel);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        btnOk.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("firstName", etFirstName.getText().toString());
            editor.putString("lastName", etLastName.getText().toString());
            editor.putString("birthDate", etBirthDate.getText().toString());
            editor.apply();

            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        });

        btnCancel.setOnClickListener(v -> {
            etFirstName.setText("");
            etLastName.setText("");
            etBirthDate.setText("");
        });
    }
}

