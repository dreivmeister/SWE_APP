package de.fhbielefeld.swe.swe_app;

import android.app.Application;

import androidx.room.Room;

public class RaumApplication extends Application {
    private AppDatabase db;
    private RaumDao raumDao;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "raumDatenbank").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        raumDao = db.raumDao();
    }

    public AppDatabase getDatabase() {
        return db;
    }
    public RaumDao getRaumDao() {
        return raumDao;
    }

}
