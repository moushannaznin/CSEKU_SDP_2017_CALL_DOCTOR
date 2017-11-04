package com.cse.calldoctor;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DoctorDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        TextView name = (TextView)findViewById(R.id.edt_name);
        TextView phone = (TextView)findViewById(R.id.edt_phone);
        TextView degree = (TextView)findViewById(R.id.edt_degree);
        TextView specialist = (TextView)findViewById(R.id.edt_specialist);
        TextView address = (TextView)findViewById(R.id.edt_address);

        name.setText(getIntent().getExtras().getString("name"));
        phone.setText(getIntent().getExtras().getString("phone"));
        degree.setText(getIntent().getExtras().getString("degree"));
        specialist.setText(getIntent().getExtras().getString("specialist"));
        address.setText(getIntent().getExtras().getString("address"));

        findViewById(R.id.edt_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set the data
                String uri = "tel:" +  getIntent().getExtras().getString("phone");
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(uri));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    return;
                }
                startActivity(callIntent);
            }
        });

        findViewById(R.id.edt_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("Lat", getIntent().getExtras().getDouble("latitude"));
                intent.putExtra("Lng", getIntent().getExtras().getDouble("longitude"));
                intent.putExtra("name", getIntent().getExtras().getString("name"));
                startActivity(intent);
            }
        });

        findViewById(R.id.btnAppointment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (((Shared) DoctorDetails.this.getApplication()).readData("session").equals("true")) {
                    Intent intent = new Intent(getApplicationContext(), DoctorAbsent.class);
                    intent.putExtra("id", getIntent().getExtras().getInt("id"));
                    startActivity(intent);
                    finish();

                } else {
                    AlertDialog.Builder build = new AlertDialog.Builder(DoctorDetails.this);
                    build.setTitle("Not Login!");
                    build.setMessage("Please Login for appointment");
                    build.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert = build.create();
                    alert.show();
                }
            }
        });

        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
