package com.cse.calldoctor.SearchResult;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class SearchResult extends AppCompatActivity {

    private ProgressDialog dialog;
    private String url = AppConfig.URL + "call_doctor_api/";

    private List<ResultData> dataList = new ArrayList<>();
    private ArrayList<String> result = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        url += getIntent().getExtras().getString("URL");

        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                AlertDialog.Builder build = new AlertDialog.Builder(SearchResult.this);
                build.setTitle("Information");
                build.setMessage("Name: " + dataList.get(position).getName() + "\n\n" +
                        "Phone: " + dataList.get(position).getPhone() + "\n\n" + "Address: " + dataList.get(position).getAddress());
                build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = build.create();
                alert.show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideDialog();
                Log.e("Response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.getBoolean("type")) {
                        JSONArray jsonArray = jsonObject.getJSONArray("result");

                        //parsing json
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject obj = jsonArray.getJSONObject(i);

                                ResultData data = new ResultData();

                                data.setName(obj.getString("name"));
                                data.setPhone(obj.getString("phone"));
                                data.setAddress(obj.getString("address"));
                                dataList.add(data);
                                //add to array
                                result.add(obj.getString("name"));

                            } catch (JSONException ignored) {
                            }
                        }
                        // Spinner adapter
                        listView.setAdapter(new ArrayAdapter<>(SearchResult.this, android.R.layout.simple_list_item_1, result));

                    } else {
                        final boolean type = jsonObject.getBoolean("type");
                        final String msg = jsonObject.getString("msg");

                        AlertDialog.Builder build = new AlertDialog.Builder(SearchResult.this);
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
                parameters.put("dist", getIntent().getExtras().getString("district_id"));
                parameters.put("divi", getIntent().getExtras().getString("division_id"));

                return parameters;
            }
        };
        // Adding request to request queue
        requestQueue.add(request);

    }

    // Hide progress dialog
    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
