package de.fhbielefeld.swe.swe_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        EditText text1 = findViewById(R.id.ID_E);
        EditText text2 = findViewById(R.id.Groeße_E);
        EditText text3 = findViewById(R.id.AnzahlStühle_E);
        EditText text4 = findViewById(R.id.AnzahlTische_E);
        EditText text5 = findViewById(R.id.AnzahlPlaetze_E);
        EditText text6 = findViewById(R.id.Sonderaussattung_E);
        EditText text7 = findViewById(R.id.Maengel_E);


        Button Speichern = findViewById(R.id.Speichern);
        Speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Speichern Button clicked");

                Bundle b = utils.getData(text1, text2, text3, text4, text5, text6, text7);

                Intent neuZuMain = new Intent(NewActivity.this, MainActivity.class);
                neuZuMain.putExtra("newRoom", b);
                startActivity(neuZuMain);
            }
        });

        Button Verlassen = findViewById(R.id.Verlassen_N);
        Verlassen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Verlassen Button clicked");
                Intent neuZuMain = new Intent(NewActivity.this, MainActivity.class);
                startActivity(neuZuMain);
            }
        });
    }
}