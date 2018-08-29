package com.galeforce.quake2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuakeAdapter extends ArrayAdapter<Quake> {
    private Context context;
    private List<Quake> quakeList = new ArrayList<Quake>();

    public QuakeAdapter(@NonNull Context context, int resource, @NonNull List<Quake> quakes) {
        super(context, resource, quakes);

        quakeList = quakes;
        this.context = context;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.quake_listview,parent,false);

        Quake quake = quakeList.get(position);

        TextView title = (TextView) listItem.findViewById(R.id.title);
        title.setText(quake.title);

        TextView url = (TextView) listItem.findViewById(R.id.url);
        url.setText(quake.url);

        return listItem;
    }
}