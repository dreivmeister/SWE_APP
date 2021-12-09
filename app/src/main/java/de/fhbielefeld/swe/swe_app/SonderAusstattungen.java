package de.fhbielefeld.swe.swe_app;

import java.util.List;
//SOURCE: https://stackoverflow.com/questions/44582397/android-room-persistent-library-typeconverter-error-of-error-cannot-figure-ou
public class SonderAusstattungen {
    private List<String> sonderAusstattungen;

    public SonderAusstattungen(List<String> sonderAusstattungen) {
        setSonderAusstattungen(sonderAusstattungen);
    }

    public List<String> getSonderAusstattungen() {
        return sonderAusstattungen;
    }

    public void setSonderAusstattungen(List<String> sonderAusstattungen) {
        this.sonderAusstattungen = sonderAusstattungen;
    }
}
