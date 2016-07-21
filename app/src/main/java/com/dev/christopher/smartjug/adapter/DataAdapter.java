package com.dev.christopher.smartjug.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dev.christopher.smartjug.R;
import com.dev.christopher.smartjug.result.DataResult;

import java.util.ArrayList;

/**
 * Created by Christopher on 17/07/16.
 */
public class DataAdapter extends ArrayAdapter<DataResult> {
    private ArrayList<DataResult> results;

    public DataAdapter(Context context, ArrayList<DataResult>results) {
        super(context, 0,results);
        this.results = results;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataResult dataResult = getItem(position);

        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_smart_data,parent,false);
            holder = new ViewHolder();
            holder.waterTextView = (TextView) convertView.findViewById(R.id.water_drink);
            holder.dateTextView = (TextView) convertView.findViewById(R.id.date_hours);
            convertView.setTag(holder);
        }
        else {
            holder =(ViewHolder)convertView.getTag();
        }
        holder.dateTextView.setText(dataResult.getDate());
        holder.waterTextView.setText(String.valueOf(dataResult.getLiter())+" cl");

        return convertView;
    }

    static class ViewHolder {
        public TextView dateTextView;
        public TextView waterTextView;
    }
}
