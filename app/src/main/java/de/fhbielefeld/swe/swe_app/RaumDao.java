package de.fhbielefeld.swe.swe_app;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RaumDao {
    @Query("SELECT * FROM raum")
    List<Raum> getAll();

    @Query("SELECT * FROM raum WHERE raumnummer IN (:raumNummern)")
    List<Raum> loadAllByIds(int[] raumNummern);

//    @Query("SELECT * FROM raum WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    Raum findByName(String first, String last);

    @Insert
    void insertAll(Raum... raeume);

    @Delete
    void delete(Raum raum);
}
