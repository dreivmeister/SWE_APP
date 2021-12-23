package de.fhbielefeld.swe.swe_app;

import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

public class utils {


    public static Raum addRoom(final AppDatabase db, Raum room) {
        db.raumDao().insertRoom(room);
        return room;
    }

    public static List<Raum> getAllRooms(final AppDatabase db) {
        return db.raumDao().getAll();
    }

    public static Raum getRoom(final AppDatabase db, String raumID) {
        return db.raumDao().loadById(raumID);
    }

    public static void updateRoom(final AppDatabase db, Raum raum) {
        db.raumDao().updateRoom(raum);
    }

    public static void deleteRoom(final AppDatabase db, Raum raum) {
        db.raumDao().deleteRoom(raum);
    }

    public static Bundle getData(EditText t1, EditText t2, EditText t3, EditText t4, EditText t5, EditText t6, EditText t7) {
        Bundle b = new Bundle();
        if(t1.getText().length() == 0) {
            b.putString("raumID", "Z0");
        }else {
            b.putString("raumID", String.valueOf(t1.getText()));
        }

        if(t2.getText().length() == 0) {
            b.putInt("raumG", -1);
        }else {
            b.putInt("raumG",Integer.parseInt(String.valueOf(t2.getText())));
        }

        if(t3.getText().length() == 0) {
            b.putInt("anzS", -1);
        }else {
            b.putInt("anzS" ,Integer.parseInt(String.valueOf(t3.getText())));
        }

        if(t4.getText().length() == 0) {
            b.putInt("anzT", -1);
        }else {
            b.putInt("anzT",Integer.parseInt(String.valueOf(t4.getText())));
        }

        if(t5.getText().length() == 0) {
            b.putInt("anzP", -1);
        }else {
            b.putInt("anzP", Integer.parseInt(String.valueOf(t5.getText())));
        }

        b.putString("sonderA", String.valueOf(t6.getText()));
        b.putString("maengel", String.valueOf(t7.getText()));

        return b;
    }


}
