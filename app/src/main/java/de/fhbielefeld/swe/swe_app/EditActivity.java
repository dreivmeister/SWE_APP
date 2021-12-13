package de.fhbielefeld.swe.swe_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        String ID = intent.getStringExtra("ID");

        //datenbank initialisieren
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "raumDatenbank").allowMainThreadQueries().build();
        RaumDao raumDao = db.raumDao();

        //übergebene daten
        //Raum givenRaum = raumDao.loadById(ID.charAt(0), ID.substring(1));


        Button Speichern = findViewById(R.id.Speichern_E);
        Speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Speichern Button clicked");
                Intent editZuMain = new Intent(EditActivity.this, MainActivity.class);
                startActivity(editZuMain);
            }
        });

        Button Verlassen = findViewById(R.id.Verlassen_E);
        Verlassen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("verlassen Button clicked");
                Intent editZuMain = new Intent(EditActivity.this, MainActivity.class);
                startActivity(editZuMain);
            }
        });


    }
}