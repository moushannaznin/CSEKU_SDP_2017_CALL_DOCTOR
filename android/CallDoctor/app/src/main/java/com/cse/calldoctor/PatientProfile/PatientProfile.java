package com.cse.calldoctor.PatientProfile;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cse.calldoctor.R;
import com.cse.calldoctor.Shared;
import com.cse.calldoctor.app.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientProfile extends AppCompatActivity {

    private String URL = AppConfig.URL + "call_doctor_api/doctor_apponment.php";

    private ProgressDialog dialog;

    private ListView listView;
    private List<DataAdapter> adapterArrayList = new ArrayList<>();;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomAdapter(PatientProfile.this, adapterArrayList);
        listView.setAdapter(adapter);

        // Load Json Data from Web
        loadDataFromWeb();
    }

    private void loadDataFromWeb() {

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideDialog();

                try {
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
                        adapter.notifyDataSetChanged();

                    } else {
                        final boolean type = jsonObject.getBoolean("type");
                        final String msg = jsonObject.getString("msg");

                        AlertDialog.Builder build = new AlertDialog.Builder(PatientProfile.this);
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
                parameters.put("req_type", String.valueOf(4));
                parameters.put("user_id", ((Shared) PatientProfile.this.getApplication()).readData("user_id"));

                return parameters;
            }
        };
        // Adding request to request queue
        requestQueue.add(request);
    }

    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
