package com.example.sharedpreferences;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup rg_language, rg_size;
    Button btn_save;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        rg_language = findViewById(R.id.rg_language);
        rg_size = findViewById(R.id.rg_size);
        btn_save = findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = rg_language.getCheckedRadioButtonId();
                int size_id = rg_size.getCheckedRadioButtonId();
                if (id != -1 && size_id != -1) {
                    RadioButton select_language = findViewById(id);
                    RadioButton select_size = findViewById(size_id);

                    String language = select_language.getText().toString();
                    String textSize = select_size.getText().toString();

                    sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("language", language);
                    editor.putString("textSize", textSize);
                    editor.apply();

                    Toast.makeText(SettingsActivity.this,"Settings Saved", Toast.LENGTH_LONG).show();
                    Intent i_setthing = new Intent(SettingsActivity.this , MainActivity.class);
                    startActivity(i_setthing);
                    finish();
                }
                else {
                    Toast.makeText(SettingsActivity.this,"Please select language and text size",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}