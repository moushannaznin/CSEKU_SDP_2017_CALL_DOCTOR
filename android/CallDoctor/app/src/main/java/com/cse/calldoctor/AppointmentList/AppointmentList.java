package com.cse.calldoctor.AppointmentList;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cse.calldoctor.R;
import com.cse.calldoctor.app.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentList extends AppCompatActivity implements CustomAdapter.CustomButtonListener {

    private String URL = AppConfig.URL + "call_doctor_api/doctor_apponment.php";

    private ProgressDialog dialog;

    private ListView listView;
    private List<DataAdapter> adapterArrayList;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);

        listView = (ListView) findViewById(R.id.listView);
        //adapter = new CustomAdapter(this, adapterArrayList);
        //adapter.setCustomButtonListener(this);
        //listView.setAdapter(adapter);

        // Load Json Data from Web
        loadDataFromWeb();

    }

    @Override
    public void onPositiveButtonClickListener(int position, String value) {
        updateStatus(adapterArrayList.get(position).getId(), 1);
        // Reload Json Data
        loadDataFromWeb();
    }

    @Override
    public void onNegativeButtonClickListener(int position, String value) {
       updateStatus(adapterArrayList.get(position).getId(), 2);
        // Reload Json Data
        loadDataFromWeb();
    }

    private void loadDataFromWeb() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    adapterArrayList = new ArrayList<>();

                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getBoolean("type")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("users");

                        //parsing json
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                DataAdapter adapter = new DataAdapter();
                                adapter.setId(obj.getInt("id"));
                                adapter.setName(obj.getString("name"));
                                adapter.setDate(obj.getString("date"));
                                adapter.setStatus(obj.getString("status"));

                                //add to array
                                adapterArrayList.add(adapter);

                            } catch (JSONException ignored) {
                            }
                        }
                        // Update list view
                        adapter = new CustomAdapter(AppointmentList.this, adapterArrayList);
                        adapter.setCustomButtonListener(AppointmentList.this);
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    } else {
                        final boolean type = jsonObject.getBoolean("type");
                        final String msg = jsonObject.getString("msg");

                        AlertDialog.Builder build = new AlertDialog.Builder(AppointmentList.this);
                        build.setMessage(msg);
                        build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                if (!type) {
                                    finish();
                                }
                            }
                        });

                        AlertDialog alert = build.create();
                        alert.show();
                    }

                } catch (JSONException ignored) {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something went wrong! Please try again.", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> parameters = new HashMap<>();
                parameters.put("req_type", String.valueOf(2));
                parameters.put("doc_id", getIntent().getStringExtra("doc_id"));

                return parameters;
            }
        };
        // Adding request to request queue
        requestQueue.add(request);
    }

    // Update Status
    private void updateStatus(final int id, final int status) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response", response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something went wrong! Please try again.", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> parameters = new HashMap<>();
                parameters.put("req_type", String.valueOf(3));
                parameters.put("apponment_id", String.valueOf(id));
                parameters.put("status", String.valueOf(status));

                return parameters;
            }
        };
        // Adding request to request queue
        requestQueue.add(request);
    }
}
