package com.cse.calldoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.cse.calldoctor.PatientProfile.PatientProfile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDoctor = (Button) findViewById(R.id.btnDoctor);
        Button btnPatient = (Button) findViewById(R.id.btnPatient);
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        Button btnAdmin = (Button) findViewById(R.id.btnAdmin);

        btnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DoctorLogin.class));
            }
        });

        btnPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((Shared) MainActivity.this.getApplication()).readData("session").equals("true"))
                startActivity(new Intent(MainActivity.this, PatientProfile.class));
                else startActivity(new Intent(MainActivity.this, PatientLogin.class));
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchOption.class));
            }
        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdminLogin.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            startActivity(new Intent(MainActivity.this, About.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
