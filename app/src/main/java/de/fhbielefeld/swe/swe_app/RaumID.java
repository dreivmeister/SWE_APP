package de.fhbielefeld.swe.swe_app;

public class RaumID {
    private char Teil;
    private int Nummer;
    public RaumID(char Teil, int Nummer) {
        this.Teil = Teil;
        this.Nummer = Nummer;
    }

    public char getTeil() {
        return this.Teil;
    }
    public int getNummer() {
        return this.Nummer;
    }

}
