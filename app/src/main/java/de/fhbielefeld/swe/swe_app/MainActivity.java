package de.fhbielefeld.swe.swe_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity<adapter> extends AppCompatActivity {
    static List<Raum> raumListe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button neuerRaum = findViewById(R.id.Neu);
        neuerRaum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Neu Button clicked");
                Intent mainZuNeu = new Intent(MainActivity.this, NewActivity.class);
                startActivity(mainZuNeu);
            }
        });

        Button filter = findViewById(R.id.Filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Filter Button clicked");
                Intent mainZuFilter = new Intent(MainActivity.this, FilterActivity.class);
                startActivity(mainZuFilter);
            }
        });

        //Datenbank intialisieren
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "raumDatenbank").allowMainThreadQueries().build();

        RaumDao raumDao = db.raumDao();
        //kann jetzt mit den Funktionen in RaumDao verwendet werden
        //erstellen der raumListe für die Darstellung in MainActivity
        raumListe = raumDao.getAll();

        //verknüpfung von ListView und raumListe
        ArrayAdapter<Raum> adapter = new ArrayAdapter<Raum>(this, android.R.layout.simple_list_item_1, raumListe);
        ListView listView = (ListView) findViewById(R.id.Ergebnisse);
        listView.setAdapter(adapter);

        EditText inputSearch = (EditText) findViewById(R.id.Suche);
        //i dont know if it belongs here
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        



    }

    static List<Raum> getRaumListe() {
        return raumListe;
    }
}