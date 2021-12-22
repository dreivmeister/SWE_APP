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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity<adapter> extends AppCompatActivity {
    static List<Raum> raumListe = new ArrayList<>();


    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //verknüpfung von ListView und raumListe
        raumListe = utils.getAllRooms(AppDatabase.getAppDatabase(MainActivity.this));
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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Raum item = (Raum) parent.getAdapter().getItem(position);
                utils.deleteRoom(AppDatabase.getAppDatabase(MainActivity.this), item);

                raumListe = utils.getAllRooms(AppDatabase.getAppDatabase(MainActivity.this));

                ArrayAdapter<Raum> adapter = new ArrayAdapter<Raum>(MainActivity.this,android.R.layout.simple_list_item_1, raumListe);
                ListView listView = findViewById(R.id.Ergebnisse);
                listView.setAdapter(adapter);

                return true;
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

        Button neuerRaum = findViewById(R.id.Neu);
        neuerRaum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Neu Button clicked");
                Intent mainZuNeu = new Intent(MainActivity.this, NewActivity.class);
                startActivity(mainZuNeu);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("editedRoom");
        Raum updatedRoom = new Raum();
        if (b != null) {
            updatedRoom = new Raum(b.getChar("gebT"), b.getInt("raumN"), b.getInt("raumG"),
                    b.getInt("anzS"), b.getInt("anzT"), b.getInt("anzP"), b.getString("sonderA"), b.getString("maengel"));
            utils.updateRoom(AppDatabase.getAppDatabase(this), updatedRoom);

            raumListe = utils.getAllRooms(AppDatabase.getAppDatabase(MainActivity.this));

            ArrayAdapter<Raum> adapter = new ArrayAdapter<Raum>(MainActivity.this,android.R.layout.simple_list_item_1, raumListe);
            ListView listView = findViewById(R.id.Ergebnisse);
            listView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
            return;
        }

        Intent i_del = getIntent();
        Bundle b_del = i_del.getBundleExtra("deletedRoom");
        if (b_del != null) {
            utils.deleteRoom(AppDatabase.getAppDatabase(MainActivity.this), utils.getRoom(AppDatabase.getAppDatabase(MainActivity.this),
                                                                                            b_del.getChar("gebT"),
                                                                                            b_del.getInt("raumN")));

            raumListe = utils.getAllRooms(AppDatabase.getAppDatabase(MainActivity.this));

            ArrayAdapter<Raum> adapter = new ArrayAdapter<Raum>(MainActivity.this,android.R.layout.simple_list_item_1, raumListe);
            ListView listView = findViewById(R.id.Ergebnisse);
            listView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
            return;
        }

        Intent i_new = getIntent();
        Bundle b_new = i_new.getBundleExtra("newRoom");
        Raum newRoom = new Raum();
        if(b_new != null) {
            newRoom = new Raum(b_new.getChar("gebT"), b_new.getInt("raumN"), b_new.getInt("raumG"),
                    b_new.getInt("anzS"), b_new.getInt("anzT"), b_new.getInt("anzP"), b_new.getString("sonderA"), b_new.getString("maengel"));
            utils.addRoom(AppDatabase.getAppDatabase(this), newRoom);

            raumListe = utils.getAllRooms(AppDatabase.getAppDatabase(MainActivity.this));

            ArrayAdapter<Raum> adapter = new ArrayAdapter<Raum>(MainActivity.this,android.R.layout.simple_list_item_1, raumListe);
            ListView listView = findViewById(R.id.Ergebnisse);
            listView.setAdapter(adapter);

            adapter.notifyDataSetChanged();
            return;
        }

    }


    public static List<Raum> getRaumListe() {
        return raumListe;
    }
}