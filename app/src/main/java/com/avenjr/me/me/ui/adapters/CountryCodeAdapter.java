package com.avenjr.me.me.ui.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.avenjr.me.me.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CountryCodeAdapter extends ArrayAdapter<String> {

    private ArrayList<Integer> flags;
    private ArrayList<String> countries;
    private LayoutInflater flater;
    private Activity activity;

    public CountryCodeAdapter(Activity activity, int simple_spinner_dropdown_item, ArrayList<String> countries, ArrayList<Integer> flags) {
        super(activity, simple_spinner_dropdown_item, countries);
        this.flags = flags;
        this.activity = activity;
        this.countries = countries;
        flater = activity.getLayoutInflater();
    }

    @NonNull
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View rowView = flater.inflate(R.layout.country_row_spinner_layout,null,true);
        CircleImageView circleImageView = rowView.findViewById(R.id.circleImageView2);
        circleImageView.setImageDrawable(activity.getResources().getDrawable(flags.get(position)));

        TextView textView = rowView.findViewById(R.id.textView);
        textView.setText(countries.get(position));

        return rowView;
    }
}
