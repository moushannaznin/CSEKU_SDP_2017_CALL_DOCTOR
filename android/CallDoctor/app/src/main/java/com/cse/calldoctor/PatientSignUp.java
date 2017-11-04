package com.cse.calldoctor;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cse.calldoctor.app.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PatientSignUp extends AppCompatActivity {

    private String insertPatientUrl = AppConfig.URL + "call_doctor_api/Insert_patient.php";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        final EditText edtName = (EditText) findViewById(R.id.name);
        final EditText edtEmail = (EditText) findViewById(R.id.email);
        final EditText edtPassword = (EditText) findViewById(R.id.edtPassword);

        Button sign_up = (Button) findViewById(R.id.sign_up);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    registerPatient(name, email, password);
                } else
                    Toast.makeText(PatientSignUp.this, "Please input every field", Toast.LENGTH_SHORT).show();

            }
        });
    }

    /**
     * Function to store user in MySQL database will post params(name,
     * phone, latitude, longitude) to register url
     */
    private void registerPatient(final String name, final String email, final String password) {

        StringRequest request = new StringRequest(Request.Method.POST, insertPatientUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    final boolean type = jsonObject.getBoolean("type");
                    final String msg = jsonObject.getString("msg");

                    AlertDialog.Builder build = new AlertDialog.Builder(PatientSignUp.this);
                    build.setMessage(msg);
                    build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            if (type) {
                                finish();
                            }
                        }
                    });

                    AlertDialog alert = build.create();
                    alert.show();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Json Error", e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Something went wrong! Please try again.", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // Posting params to register url
                Map<String, String> parameters = new HashMap<>();
                parameters.put("name", name);
                parameters.put("email", email);
                parameters.put("pass", password);

                return parameters;
            }
        };
        // Adding request to request queue
        requestQueue.add(request);

    }

}
