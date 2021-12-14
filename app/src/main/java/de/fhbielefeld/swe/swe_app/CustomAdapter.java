package de.fhbielefeld.swe.swe_app;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



//SOURCE: https://stackoverflow.com/questions/14663725/list-view-filter-android

public class CustomAdapter extends BaseAdapter implements Filterable {


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<RaumID> FilteredRaumNummern = new ArrayList<>();

                //tatsächliches filtern
                constraint = constraint.toString().toLowerCase();
                for(int i = 0; i < MainActivity.getRaumListe().size(); i++) {
                    char GebaeudeTeil = MainActivity.getRaumListe().get(i).gebaeudeteil;
                    int RaumNummer = MainActivity.getRaumListe().get(i).raumnummer;

                    //comparison between EditText and ListView content
                    //compare RaumNummer with integer sequence of constraint
                    //compare GebaeudeTeil with char at beginning of constraint
                    //https://docs.oracle.com/javase/8/docs/api/java/lang/CharSequence.html
                    if (GebaeudeTeil == constraint.charAt(0) && constraint.toString().contains(String.valueOf(RaumNummer))) {
                        FilteredRaumNummern.add(new RaumID(GebaeudeTeil, RaumNummer));
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                List<RaumID> arrayListNames = (List<RaumID>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
