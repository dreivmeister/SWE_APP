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

    //liste mit Räumen mit gesuchter Raumnummer
    @Query("SELECT * FROM raum WHERE raumnummer IN (:raumNummern) AND gebaeudeteil IN (:gebaeudeTeil)")
    List<Raum> loadAllByIds(int[] raumNummern, char[] gebaeudeTeil);

    //raum mit gesuchter Raumnummer
    @Query("SELECT * FROM raum WHERE (raumnummer = :raumNummer) AND (gebaeudeteil = :gebaudeTeil)")
    Raum loadById(int raumNummer, char gebaudeTeil);

    //querys für filter
    //ignore zero parameters (not every parameter has to be specified)
    @Query("SELECT * FROM raum WHERE ((raumnummer = :raumNummer) OR (raumnummer IS NULL))" +
            " AND ((gebaeudeteil = :gebaudeTeil) OR (gebaeudeteil IS NULL))" +
            " AND ((Raumgroeße = :raumGroesse) OR (Raumgroeße IS NULL)) " +
            " AND ((AnzahlStühle = :anzahlStühle) OR (AnzahlStühle IS NULL))" +
            " AND ((AnzahlTische = :anzahlTische) OR (AnzahlTische IS NULL)) " +
            " AND ((AnzahlPlaetze = :anzahlPlaetze) OR (AnzahlPlaetze IS NULL))" +
            " AND ((Sonderausstattung IN (:sonder)) OR (Sonderausstattung IS NULL))" +
            " AND (Maengel = :maengel)")
    List<Raum> filter(int raumNummer, char gebaudeTeil, int raumGroesse, int anzahlStühle, int anzahlTische, int anzahlPlaetze, List<String> sonder, int maengel);



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoom(Raum raum);

    @Delete
    void deleteRoom(Raum raum);

    @Update
    void updateRoom(Raum raum);
}
