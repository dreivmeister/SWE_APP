package de.fhbielefeld.swe.swe_app;

import androidx.room.TypeConverter;
//SOURCE: https://stackoverflow.com/questions/44582397/android-room-persistent-library-typeconverter-error-of-error-cannot-figure-ou
import java.util.Arrays;
import java.util.List;

public class Converter {
    @TypeConverter
    public SonderAusstattungen storedStringToSonderAusstattungen(String value) {
        List<String> ausstattungen = Arrays.asList(value.split("\\s*,\\s*"));
        return new SonderAusstattungen(ausstattungen);
    }

    @TypeConverter
    public String SonderAusstattungenToStoredString(SonderAusstattungen sa) {
        String value = "";

        for (String ausst : sa.getSonderAusstattungen()) {
            value += ausst + ",";
        }
        return value;
    }

}
