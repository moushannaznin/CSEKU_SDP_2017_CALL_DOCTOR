package com.cse.calldoctor;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cse.calldoctor.app.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    private String showSpecialistUrl = AppConfig.URL + "call_doctor_api/doctors.php";

    private ProgressDialog dialog;

    private List<Specialist> specialists = new ArrayList<>();
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new Adapter(this, specialists);
        listView.setAdapter(adapter);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, showSpecialistUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideDialog();
                Log.e("Response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getBoolean("type")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("doc_list");

                        //parsing json
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                Specialist specialist = new Specialist();
                                specialist.setId(obj.getInt("id"));
                                specialist.setName(obj.getString("name"));
                                specialist.setPhone(obj.getString("phone"));
                                specialist.setDegree(obj.getString("degree"));
                                specialist.setLatitude(obj.getDouble("latitude"));
                                specialist.setLongitude(obj.getDouble("longitude"));
                                specialist.setAddress(obj.getString("address"));
                                specialist.setDivision(obj.getString("division"));
                                specialist.setDistrict(obj.getString("district"));

                                //Specialist is json array
                                JSONArray specialistArray = obj.getJSONArray("spec");
                                ArrayList<String> spec = new ArrayList<>();
                                for (int j = 0; j < specialistArray.length(); j++) {
                                    spec.add((String) specialistArray.get(j));
                                }
                                specialist.setSpecialist(spec);

                                //add to array
                                specialists.add(specialist);

                            } catch (JSONException ignored) {
                            }
                        }
                        // Update list view
                        adapter.notifyDataSetChanged();

                    } else {
                        final boolean type = jsonObject.getBoolean("type");
                        final String msg = jsonObject.getString("msg");

                        AlertDialog.Builder build = new AlertDialog.Builder(ResultActivity.this);
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
                parameters.put("spe", getIntent().getExtras().getString("specialists"));
                parameters.put("dist", getIntent().getExtras().getString("district_id"));
                parameters.put("divi", getIntent().getExtras().getString("division_id"));

                return parameters;
            }
        };
        // Adding request to request queue
        requestQueue.add(request);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //getting data for row
                final Specialist specialist = specialists.get(position);

                //Specialist
                String specStr = "";
                for (String str : specialist.getSpecialist()) {
                    specStr += str + ", ";
                }
                specStr = specStr.length() > 0 ? specStr.substring(0, specStr.length() - 2) : specStr;

                Intent i = new Intent(getApplicationContext(), DoctorDetails.class);
                i.putExtra("id", specialist.getId());
                i.putExtra("name", specialist.getName());
                i.putExtra("phone", specialist.getPhone());
                i.putExtra("degree", specialist.getDegree());
                i.putExtra("latitude", specialist.getLatitude());
                i.putExtra("longitude", specialist.getLongitude());
                i.putExtra("address", specialist.getAddress());

                i.putExtra("specialist", specStr);

                startActivity(i);
            }
        });

    }

    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
