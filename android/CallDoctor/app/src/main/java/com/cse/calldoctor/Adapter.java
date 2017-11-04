package com.cse.calldoctor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class Adapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Activity activity;
    private List<Specialist> specialists;

    public Adapter(Activity activity, List<Specialist> specialists) {
        this.activity = activity;
        this.specialists = specialists;
    }

    @Override
    public int getCount() {
        return specialists.size();
    }

    @Override
    public Object getItem(int position) {
        return specialists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_list_view, null);
        }

        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        TextView txtSpecialist = (TextView) convertView.findViewById(R.id.txtPhone);

        //getting data for row
        final Specialist specialist = specialists.get(position);

        //Name
        txtName.setText(specialist.getName());

        //Specialist
        String specStr = "";
        for (String str : specialist.getSpecialist()) {
            specStr += str + ", ";
        }
        specStr = specStr.length() > 0 ? specStr.substring(0, specStr.length() - 2) : specStr;
        txtSpecialist.setText(specStr);

        return convertView;
    }
}
