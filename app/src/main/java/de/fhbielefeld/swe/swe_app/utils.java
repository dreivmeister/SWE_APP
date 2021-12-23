package de.fhbielefeld.swe.swe_app;

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


}
