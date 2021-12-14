package de.fhbielefeld.swe.swe_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        RaumApplication app = (RaumApplication)getApplication();
        AppDatabase db = app.getDatabase();
        RaumDao raumDao = app.getRaumDao();

        Intent intent = getIntent();
        String gebT = intent.getStringExtra("Gebaeudeteil");
        int raumN = intent.getIntExtra("Raumnummer", 0);

        Raum currentRoom = raumDao.loadById(raumN, gebT);

        EditText text1 = (EditText) findViewById(R.id.ID_E);
        text1.setText(currentRoom.toString());




        EditText text2 = (EditText) findViewById(R.id.Groeße_E);
        text2.setText(String.valueOf(currentRoom.getRaumGroesse()));

        EditText text3 = (EditText) findViewById(R.id.AnzahlStühle_E);
        text3.setText(String.valueOf(currentRoom.getAnzahlStuehle()));

        EditText text4 = (EditText) findViewById(R.id.AnzahlTische_E);
        text4.setText(String.valueOf(currentRoom.getAnzahlTische()));

        EditText text5 = (EditText) findViewById(R.id.AnzahlPlaetze_E);
        text5.setText(String.valueOf(currentRoom.getAnzahlPlaetze()));

        EditText text6 = (EditText) findViewById(R.id.Sonderaussattung_E);
        text6.setText(currentRoom.getSonderAusstattung());

        EditText text7 = (EditText) findViewById(R.id.Maengel_E);
        text6.setText(String.valueOf(currentRoom.getMaengel()));







        //receive intent string which is roomID
        //split up in gebäudeteil und raumnummer
        //get corresponding room from db
        //put values in edit text fields as defualt value





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