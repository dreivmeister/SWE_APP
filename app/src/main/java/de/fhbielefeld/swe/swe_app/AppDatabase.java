package de.fhbielefeld.swe.swe_app;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Raum.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RaumDao raumDao();
}
