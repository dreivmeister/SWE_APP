package de.fhbielefeld.swe.swe_app;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.ArrayList;
import java.util.List;

@Entity(primaryKeys = {"gebaeudeteil", "raumnummer"})
public class Raum {
    @NonNull public String gebaeudeteil;
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
    public String sonderAusstattung;

    @ColumnInfo(name = "Maengel")
    public int maengel;

    public Raum (String gebaeudeteil, int raumnummer, int raumGroesse, int anzahlStuehle, int anzahlTische, int anzahlPlaetze, String sonderAusstattung, int maengel) {
        this.gebaeudeteil = gebaeudeteil;
        this.raumnummer = raumnummer;
        this.raumGroesse = raumGroesse;
        this.anzahlStuehle = anzahlStuehle;
        this.anzahlTische = anzahlTische;
        this.anzahlPlaetze = anzahlPlaetze;
        this.sonderAusstattung = sonderAusstattung;
        this.maengel = maengel;
    }


    @Override
    public String toString() {
        return gebaeudeteil + String.valueOf(raumnummer);
    }

    @NonNull
    public String getGebaeudeteil() {
        return gebaeudeteil;
    }

    public int getRaumnummer() {
        return raumnummer;
    }

    public int getRaumGroesse() {
        return raumGroesse;
    }

    public int getAnzahlStuehle() {
        return anzahlStuehle;
    }

    public int getAnzahlPlaetze() {
        return anzahlPlaetze;
    }

    public int getAnzahlTische() {
        return anzahlTische;
    }

    public int getMaengel() {
        return maengel;
    }

    public String getSonderAusstattung() {
        return sonderAusstattung;
    }
}
