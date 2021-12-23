package de.fhbielefeld.swe.swe_app;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Raum {
    @PrimaryKey
    @NonNull
    public String raumID;

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
    public String maengel;

    public Raum() {

    }

    public Raum (String raumID, int raumGroesse, int anzahlStuehle, int anzahlTische, int anzahlPlaetze, String sonderAusstattung, String maengel) {
        this.raumID = raumID;
        this.raumGroesse = raumGroesse;
        this.anzahlStuehle = anzahlStuehle;
        this.anzahlTische = anzahlTische;
        this.anzahlPlaetze = anzahlPlaetze;
        this.sonderAusstattung = sonderAusstattung;
        this.maengel = maengel;
    }


    @Override
    public String toString() {
        return raumID;
    }
    public String getRaumID() { return raumID; }

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

    public String getMaengel() {
        return maengel;
    }

    public String getSonderAusstattung() {
        return sonderAusstattung;
    }

    public void setRaumID(String raumID) { this.raumID = raumID; }

    public void setRaumGroesse(int raumGroesse) {
        this.raumGroesse = raumGroesse;
    }

    public void setAnzahlStuehle(int anzahlStuehle) {
        this.anzahlStuehle = anzahlStuehle;
    }

    public void setAnzahlTische(int anzahlTische) {
        this.anzahlTische = anzahlTische;
    }

    public void setAnzahlPlaetze(int anzahlPlaetze) {
        this.anzahlPlaetze = anzahlPlaetze;
    }

    public void setSonderAusstattung(String sonderAusstattung) { this.sonderAusstattung = sonderAusstattung; }

    public void setMaengel(String maengel) {
        this.maengel = maengel;
    }

    public void print() {
        System.out.println(this.raumID);
    }
}
