package de.fhbielefeld.swe.swe_app;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.ArrayList;

@Entity
public class Raum {
    @PrimaryKey
    public int raumnummer;

    @ColumnInfo(name = "Raumgroeße")
    public int raumGroesse;

    @ColumnInfo(name = "AnzahlStühle")
    public int anzahlStuehle;

    @ColumnInfo(name = "AnzahlTische")
    public int anzahlTische;

    @ColumnInfo(name = "AnzahlPlaetze")
    public int anzahlPlaetze;

    @ColumnInfo(name = "Sonderausstattung")
    public ArrayList<String> sonderAusstattung;

    @ColumnInfo(name = "Maengel")
    public int maengel;

    @Override
    public String toString() {
        return String.valueOf(raumnummer);
    }
}
