package de.fhbielefeld.swe.swe_app;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RaumDao {

    //liste mit allen Räumen
    @Query("SELECT * FROM raum")
    List<Raum> getAll();

    //raum mit gesuchter Raumnummer
    @Query("SELECT * FROM raum WHERE (raumID = :RaumID)")
    Raum loadById(String RaumID);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoom(Raum raum);

    @Delete
    void deleteRoom(Raum raum);

    @Update
    void updateRoom(Raum raum);
}
