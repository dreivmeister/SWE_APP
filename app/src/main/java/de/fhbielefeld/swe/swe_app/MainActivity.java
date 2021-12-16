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
import java.util.concurrent.TimeUnit;

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


        Raum tR = new Raum('F',123,1,2,3,4,"Beamer",1);
        Raum tR1 = new Raum('D',321,2,3,4,5,"Autohaus",0);
        Raum tR2 = new Raum('K',321,2,3,4,5,"Autohaus",0);

        AppDatabase.getAppDatabase(this).clearAllTables();

        utils.addRoom(AppDatabase.getAppDatabase(this), tR);
        utils.addRoom(AppDatabase.getAppDatabase(this), tR1);
        utils.addRoom(AppDatabase.getAppDatabase(this), tR2);
        raumListe = utils.getAllRooms(AppDatabase.getAppDatabase(this));

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


                //print db contents
                List<Raum> rL = utils.getAllRooms(AppDatabase.getAppDatabase(MainActivity.this));
                System.out.println("MA vor Wechsel: ");
                for (Raum r : rL) {
                    r.print();
                }

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

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("-------onResume-------");



        Intent i = getIntent();
        Bundle b = i.getBundleExtra("editedRoom");
        Raum updatedRoom = new Raum();
        if (b != null) {
            updatedRoom = new Raum(b.getChar("gebT"), b.getInt("raumN"), b.getInt("raumG"),
                    b.getInt("anzS"), b.getInt("anzT"), b.getInt("anzP"), b.getString("sonderA"), b.getInt("maengel"));
            utils.updateRoom(AppDatabase.getAppDatabase(this), updatedRoom);
        }

        //System.out.println(updatedRoom.getGebaeudeteil() + " " + updatedRoom.getRaumnummer());


        //utils.updateRoom(AppDatabase.getAppDatabase(this), updatedRoom);


        //print db contents
        List<Raum> rL = utils.getAllRooms(AppDatabase.getAppDatabase(MainActivity.this));


        System.out.println("MA nach Wechsel: ");
        for (Raum r : rL) {
            r.print();
        }



        ArrayAdapter<Raum> adapter = new ArrayAdapter<Raum>(MainActivity.this,android.R.layout.simple_list_item_1, rL);
        ListView listView = findViewById(R.id.Ergebnisse);
        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


    }


    static List<Raum> getRaumListe() {
        return raumListe;
    }
}