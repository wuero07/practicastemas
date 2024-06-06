package com.example.spinner;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "ThemePrefs";
    private static final String PREF_THEME_KEY = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String theme = prefs.getString(PREF_THEME_KEY, "AppTheme1");
        int resId = getResources().getIdentifier(theme, "style", getPackageName());
        setTheme(resId);

        setContentView(R.layout.activity_main);

        Spinner themeSpinner = findViewById(R.id.themeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.theme_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinner.setAdapter(adapter);


        String[] themes = getResources().getStringArray(R.array.theme_styles);
        for (int i = 0; i < themes.length; i++) {
            if (themes[i].equals(theme)) {
                themeSpinner.setSelection(i);
                break;
            }
        }

        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedStyle = themes[position];
                if (!selectedStyle.equals(theme)) {
                    prefs.edit().putString(PREF_THEME_KEY, selectedStyle).apply();
                    recreate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
