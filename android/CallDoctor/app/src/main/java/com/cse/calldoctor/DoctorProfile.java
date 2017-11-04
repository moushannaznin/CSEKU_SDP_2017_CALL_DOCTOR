package com.cse.calldoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cse.calldoctor.AppointmentList.AppointmentList;
import com.cse.calldoctor.app.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DoctorProfile extends AppCompatActivity {

    private String URL = AppConfig.URL + "call_doctor_api/doctor_absent.php";
    private String STATUS_URL = AppConfig.URL + "call_doctor_api/doctor_available_status.php";

    private Spinner spinnerWeek, spinnerDay;
    private TextView tvWeek, tvDays, tvRange;
    private Button btnMonthDelete, btnDayDelete, btnRangeDelete;
    private EditText edtRange;
    private Button btnSave, btnShowList;

    private String strWeekly, strDay, strRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

//        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        spinnerWeek = (Spinner) findViewById(R.id.spinnerWeek);
        spinnerDay = (Spinner) findViewById(R.id.spinnerDay);
        tvWeek = (TextView) findViewById(R.id.tvWeek);
        tvDays = (TextView) findViewById(R.id.tvDays);
        tvRange = (TextView) findViewById(R.id.tvRange);
        btnMonthDelete = (Button) findViewById(R.id.btnMonthDelete);
        btnDayDelete = (Button) findViewById(R.id.btnDayDelete);
        btnRangeDelete = (Button) findViewById(R.id.btnRangeDelete);
        edtRange = (EditText) findViewById(R.id.edtRange);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnShowList = (Button) findViewById(R.id.btnShowList);

        // Load doctor status
        loadDataFromWeb(getIntent().getStringExtra("doc_id"));

//        // perform setOnDateChangeListener event on CalendarView
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//                // display the selected date by using a toast
//                Toast.makeText(getApplicationContext(), "Selected date: " + dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_SHORT).show();
//            }
//        });

        btnMonthDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strWeekly = "0";
                spinnerWeek.setSelection(0);
                tvWeek.setText("Available all days");
            }
        });

        btnDayDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strDay = "0";
                spinnerDay.setSelection(0);
                tvDays.setText("Available all date");
            }
        });

        btnRangeDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strRange = "0";
                edtRange.setText("");
                tvRange.setText("No date range is set");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spinnerWeek.getSelectedItem().equals("Choose a day...")) {
                    strWeekly = "0";
                    tvWeek.setText("Available all days");
                } else {
                    strWeekly = (String) spinnerWeek.getSelectedItem();
                    tvWeek.setText("Not available on " + strWeekly);
                }

                if (spinnerDay.getSelectedItem().equals("Choose a date...")) {
                    strDay = "0";
                    tvDays.setText("Available all date");
                } else {
                    strDay = (String) spinnerDay.getSelectedItem();
                    tvDays.setText("Not available on " + strDay);
                }

                if (edtRange.getText().toString().equals("")) {
                    strRange = "0";
                    tvRange.setText("No date range is set");
                } else {
                    strRange = edtRange.getText().toString();
                    tvRange.setText("Not available from " + strRange);
                }

                String doc_id = getIntent().getStringExtra("doc_id");
                // Post data to server
                submitData(doc_id, strWeekly, strDay, strRange);

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AppointmentList.class);
                intent.putExtra("doc_id", getIntent().getStringExtra("doc_id"));
                startActivity(intent);
            }
        });
    }

    // Update Status
    private void submitData(final String doc_id, final String days, final String date, final String range) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    boolean error = jsonObject.getBoolean("error");
                    String msg = jsonObject.getString("msg");

                    if (!error) {
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
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
                parameters.put("doc_id", doc_id);
                parameters.put("days", days);
                parameters.put("dates", date);
                parameters.put("range", range);

                return parameters;
            }
        };
        // Adding request to request queue
        requestQueue.add(request);
    }

    private void loadDataFromWeb(final String doc_id) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, STATUS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Response", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    // Handel days
                    if (jsonObject.getString("days").equals("0"))
                        tvWeek.setText("Available all days");
                    else if (jsonObject.getString("days").equals("-1"))
                        tvWeek.setText("No day is set");
                    else
                        tvWeek.setText("Not available on " + jsonObject.getString("days"));

                    // Handel dates
                    if (jsonObject.getString("dates").equals("0"))
                        tvDays.setText("Available all date");
                    else if (jsonObject.getString("dates").equals("-1"))
                        tvDays.setText("No date is set");
                    else
                        tvDays.setText("Not available on " + jsonObject.getString("dates"));

                    //Handel date range
                    if (jsonObject.getString("range").equals("0"))
                        tvRange.setText("No date range is set");
                    else if (jsonObject.getString("range").equals("-1"))
                        tvRange.setText("No date range is set");
                    else
                        tvRange.setText("Not available from " + jsonObject.getString("range"));

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
                parameters.put("doc_id", doc_id);

                return parameters;
            }
        };
        // Adding request to request queue
        requestQueue.add(request);
    }
}
