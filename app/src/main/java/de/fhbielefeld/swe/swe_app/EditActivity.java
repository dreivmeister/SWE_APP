package de.fhbielefeld.swe.swe_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        String raumID = intent.getStringExtra("raumID");

        Raum currentRoom = utils.getRoom(AppDatabase.getAppDatabase(this), raumID);

        EditText text1 = (EditText) findViewById(R.id.ID_E);
        text1.setText(currentRoom.getRaumID());

        EditText text2 = (EditText) findViewById(R.id.Groeße_E);
        if(currentRoom.getRaumGroesse() == -1) {
            text2.setText("");
        } else {
            text2.setText(String.valueOf(currentRoom.getRaumGroesse()));
        }

        EditText text3 = (EditText) findViewById(R.id.AnzahlStühle_E);
        if(currentRoom.getAnzahlStuehle() == -1) {
            text3.setText("");
        }else {
            text3.setText(String.valueOf(currentRoom.getAnzahlStuehle()));
        }


        EditText text4 = (EditText) findViewById(R.id.AnzahlTische_E);
        if(currentRoom.getAnzahlTische() == -1) {
            text4.setText("");
        }else {
            text4.setText(String.valueOf(currentRoom.getAnzahlTische()));
        }

        EditText text5 = (EditText) findViewById(R.id.AnzahlPlaetze_E);
        if(currentRoom.getAnzahlPlaetze() == -1) {
            text5.setText("");
        }else {
            text5.setText(String.valueOf(currentRoom.getAnzahlPlaetze()));
        }

        EditText text6 = (EditText) findViewById(R.id.Sonderaussattung_E);
        text6.setText(currentRoom.getSonderAusstattung());

        EditText text7 = (EditText) findViewById(R.id.Maengel_E);
        text7.setText(String.valueOf(currentRoom.getMaengel()));

        Button Speichern = findViewById(R.id.Speichern);
        Speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Speichern Button clicked");
                //Toast als Prozessinformation

                //Raum Objekt erstellen aus gegebenen Werten
                Bundle b = utils.getData(text1, text2, text3, text4, text5, text6, text7);

                Intent editZuMain = new Intent(EditActivity.this, MainActivity.class);
                editZuMain.putExtra("editedRoom", b);
                startActivity(editZuMain);
            }
        });

        Button Verlassen = findViewById(R.id.Verlassen_N);
        Verlassen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("verlassen Button clicked");
                Intent editZuMain = new Intent(EditActivity.this, MainActivity.class);
                startActivity(editZuMain);
            }
        });

        Button Loeschen = findViewById(R.id.Löschen);
        Loeschen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Löschen-Button clicked");

                Bundle b = new Bundle();
                b.putString("raumID", String.valueOf(text1.getText()));

                Intent editZuMain = new Intent(EditActivity.this, MainActivity.class);
                editZuMain.putExtra("deletedRoom", b);
                startActivity(editZuMain);
            }
        });

    }
}