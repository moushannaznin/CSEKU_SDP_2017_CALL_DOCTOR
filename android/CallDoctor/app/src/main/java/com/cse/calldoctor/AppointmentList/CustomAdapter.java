package com.cse.calldoctor.AppointmentList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.cse.calldoctor.R;

import java.util.List;


public class CustomAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Activity activity;
    private List<DataAdapter> dataAdapters;

    CustomButtonListener positiveButtonListener, negativeButtonListener;

    // for edit button
    public interface CustomButtonListener {
        void onPositiveButtonClickListener(int position, String value);
        void onNegativeButtonClickListener(int position, String value);
    }

    public void setCustomButtonListener(CustomButtonListener listener) {
        this.positiveButtonListener = listener;
        this.negativeButtonListener = listener;
    }

    public CustomAdapter(Activity activity, List<DataAdapter> dataAdapters) {
        this.activity = activity;
        this.dataAdapters = dataAdapters;
    }

    @Override
    public int getCount() {
        return dataAdapters.size();
    }

    @Override
    public Object getItem(int position) {
        return dataAdapters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.appointment_custom_list_view, null);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvEmail);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.tvStatus);
        Button btnAccept = (Button) convertView.findViewById(R.id.btnAccept);
        Button btnReject = (Button) convertView.findViewById(R.id.btnCancel);

        //getting data for row
        final DataAdapter dataAdapter = dataAdapters.get(position);

        //Name & Email
        tvName.setText(dataAdapter.getName());
        tvDate.setText(dataAdapter.getDate());

        if (dataAdapter.getStatus().equals("0")) tvStatus.setText("Status: Pending");
        else if (dataAdapter.getStatus().equals("1")) tvStatus.setText("Status: Accepted");
        else if (dataAdapter.getStatus().equals("2")) tvStatus.setText("Status: Rejected");

        final String pos = String.valueOf(getItem(position));

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (positiveButtonListener != null) {
                    positiveButtonListener.onPositiveButtonClickListener(position, pos);
                    notifyDataSetChanged();
                }
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (negativeButtonListener != null) {
                    negativeButtonListener.onNegativeButtonClickListener(position, pos);
                    notifyDataSetChanged();
                }

            }
        });

        return convertView;
    }
}
