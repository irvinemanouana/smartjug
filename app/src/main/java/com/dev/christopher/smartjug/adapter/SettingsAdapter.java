package com.dev.christopher.smartjug.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dev.christopher.smartjug.R;


/**
 * Created by Christopher on 21/07/16.
 */
public class SettingsAdapter  extends ArrayAdapter<String>{

    private String[] strings;

    public SettingsAdapter(Context context, String[] resources) {
        super(context, 0,resources);
        this.strings =resources;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String s = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cardsettigs,null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.libelle);
        textView.setText(s);
        return convertView;
    }
}
