package de.fhbielefeld.swe.swe_app;

public class RaumID {
    private String Teil;
    private int Nummer;
    public RaumID(String Teil, int Nummer) {
        this.Teil = Teil;
        this.Nummer = Nummer;
    }

    public String getTeil() {
        return this.Teil;
    }
    public int getNummer() {
        return this.Nummer;
    }

}
