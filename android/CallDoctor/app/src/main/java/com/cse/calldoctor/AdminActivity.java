package com.cse.calldoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cse.calldoctor.AddOptionHandler.AddOptionHandler;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button hospitalAdd = (Button) findViewById(R.id.btnHospitalAdd);
        Button ambulanceAdd = (Button) findViewById(R.id.btnAmbulanceAdd);
        Button diagnosticAdd = (Button) findViewById(R.id.btnDiagnosticAdd);

        hospitalAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddOptionHandler.class);
                intent.putExtra("Title", "Add New Hospital");
                intent.putExtra("req_type", "1");
                intent.putExtra("Table Name", "hospital");
                startActivity(intent);
            }
        });

        ambulanceAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddOptionHandler.class);
                intent.putExtra("Title", "Add New Ambulance");
                intent.putExtra("req_type", "2");
                intent.putExtra("Table Name", "ambulance");
                startActivity(intent);
            }
        });

        diagnosticAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddOptionHandler.class);
                intent.putExtra("Title", "Add New Diagnostic Center");
                intent.putExtra("req_type", "3");
                intent.putExtra("Table Name", "diagnostic_center");
                startActivity(intent);
            }
        });
    }
}
