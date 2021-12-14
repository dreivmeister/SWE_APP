package de.fhbielefeld.swe.swe_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity<adapter> extends AppCompatActivity {
    static List<Raum> raumListe;



    @SuppressLint("WrongThread")
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

        //aufruf der überschriebenen Application-Klasse
        RaumApplication app = (RaumApplication)getApplication();
        AppDatabase db = app.getDatabase();
        RaumDao raumDao = app.getRaumDao();

        db.clearAllTables();
        //kann jetzt mit den Funktionen in RaumDao verwendet werden
        //erstellen der raumListe für die Darstellung in MainActivity
        Raum testRaum = new Raum("F", 222, 10,30,10,39,"Beamer,Auto",1);
        Raum testRaum1 = new Raum("F", 223, 10,30,10,39,"Beamer,Auto",1);
        Raum testRaum2 = new Raum("D", 224, 10,30,10,39,"Beamer,Auto",1);

        raumDao.insertRoom(testRaum);
        raumDao.insertRoom(testRaum1);
        raumDao.insertRoom(testRaum2);

        raumListe = raumDao.getAll();

        //verknüpfung von ListView und raumListe
        ArrayAdapter<Raum> adapter = new ArrayAdapter<Raum>(this, android.R.layout.simple_list_item_1, raumListe);
        ListView listView = (ListView) findViewById(R.id.Ergebnisse);
        listView.setAdapter(adapter);

        //change to EditActivity when item is clicked (with item values inserted)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Item clicked");
                Intent itemZuEdit = new Intent(MainActivity.this, EditActivity.class);
                Raum item = (Raum) parent.getAdapter().getItem(position);
                itemZuEdit.putExtra("Raumnummer", item.getRaumnummer());
                itemZuEdit.putExtra("Gebaeudeteil", item.getGebaeudeteil());
                startActivity(itemZuEdit);
            }
        });


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