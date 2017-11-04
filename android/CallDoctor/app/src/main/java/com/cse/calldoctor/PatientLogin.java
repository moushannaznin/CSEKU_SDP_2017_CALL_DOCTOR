package com.cse.calldoctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cse.calldoctor.PatientProfile.PatientProfile;
import com.cse.calldoctor.app.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PatientLogin extends AppCompatActivity {

    private String loginPatientUrl = AppConfig.URL + "call_doctor_api/patient_login.php";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        final EditText edt_email = (EditText) findViewById(R.id.edt_email);
        final EditText edt_password   = (EditText) findViewById(R.id.edt_password);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        TextView signUp = (TextView) findViewById(R.id.signUp);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    loginPatient(email, password);
                } else
                    Toast.makeText(PatientLogin.this, "Please input every field", Toast.LENGTH_SHORT).show();

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientLogin.this, PatientSignUp.class));
            }
        });
    }

    /**
     * Function to store user in MySQL database will post params(name,
     * phone, latitude, longitude) to register url
     */
    private void loginPatient(final String email, final String password) {

        StringRequest request = new StringRequest(Request.Method.POST, loginPatientUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    final boolean error = jsonObject.getBoolean("error");
                    final String msg = jsonObject.getString("msg");

                    if (!error){
                        startActivity(new Intent(getApplicationContext(), PatientProfile.class));
                        //Store All Data
                        ((Shared) PatientLogin.this.getApplication()).storeData("true", "session", PatientLogin.this);
                        ((Shared) PatientLogin.this.getApplication()).storeData(jsonObject.getString("user_id"), "user_id", PatientLogin.this);

                        finish();

                    } else {
                        AlertDialog.Builder build = new AlertDialog.Builder(PatientLogin.this);
                        build.setMessage(msg);
                        build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog alert = build.create();
                        alert.show();
                    }

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
                parameters.put("email", email);
                parameters.put("password", password);

                return parameters;
            }
        };
        // Adding request to request queue
        requestQueue.add(request);

    }

}
