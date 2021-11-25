package de.fhbielefeld.swe.swe_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        Button Anwenden = findViewById(R.id.Anwenden);
        Anwenden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Anwenden Button clicked");
                Intent filterZuFiltered = new Intent(FilterActivity.this, FilteredActivity.class);
                startActivity(filterZuFiltered);
            }
        });

        Button Verlassen_F = findViewById(R.id.Verlassen_F);
        Verlassen_F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Verlassen Button clicked");
                Intent filterZuMain = new Intent(FilterActivity.this, MainActivity.class);
                startActivity(filterZuMain);
            }
        });
    }
}