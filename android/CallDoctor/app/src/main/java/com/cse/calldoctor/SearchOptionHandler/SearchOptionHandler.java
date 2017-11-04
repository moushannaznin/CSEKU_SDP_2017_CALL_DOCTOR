package com.cse.calldoctor.SearchOptionHandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.cse.calldoctor.R;
import com.cse.calldoctor.SearchResult.SearchResult;
import com.cse.calldoctor.spinnerData.DistrictData;
import com.cse.calldoctor.spinnerData.DivisionData;

public class SearchOptionHandler extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DivisionData divisionData;
    private DistrictData districtData;

    private Spinner spinnerDivision, spinnerDistrict;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_handler);

        spinnerDivision = (Spinner) findViewById(R.id.spinnerDivision);
        spinnerDistrict = (Spinner) findViewById(R.id.spinnerDistrict);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        ArrayAdapter<DivisionData> spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, new DivisionData[]{
                new DivisionData(0, "Select..."),
                new DivisionData(1, "Dhaka"),
                new DivisionData(2, "Chittagong"),
                new DivisionData(3, "Khulna"),
                new DivisionData(4, "Barisal"),
                new DivisionData(5, "Mymenshing"),
                new DivisionData(6, "Rajshahi"),
                new DivisionData(7, "Sylhet"),
                new DivisionData(8, "Rangpur"),
                new DivisionData(0, "All Division")
        });

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDivision.setAdapter(spinnerArrayAdapter);

        spinnerDivision.setOnItemSelectedListener(this);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                divisionData = (DivisionData) spinnerDivision.getSelectedItem();
                districtData = (DistrictData) spinnerDistrict.getSelectedItem();

                if (!divisionData.name.equals("Select...") && !districtData.name.equals("Select...")) {

                    Intent i = new Intent(getApplicationContext(), SearchResult.class);
                    i.putExtra("URL", getIntent().getExtras().getString("URL"));
                    i.putExtra("district_id", String.valueOf(districtData.id));
                    i.putExtra("division_id", String.valueOf(divisionData.id));
                    startActivity(i);

                } else
                    Toast.makeText(SearchOptionHandler.this, "Select any division and district", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    Spinner Listener
    */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        // Get the currently selected State object from the spinner
        divisionData = (DivisionData) spinnerDivision.getSelectedItem();

        switch (divisionData.name) {
            case "Select...": {
                ArrayAdapter<DistrictData> spinnerArrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, new DistrictData[]{
                        new DistrictData(0, "Select...")
                });
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDistrict.setAdapter(spinnerArrayAdapter);

                break;
            }
            case "Dhaka": {
                ArrayAdapter<DistrictData> spinnerArrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, new DistrictData[]{
                        new DistrictData(0, "Select..."),
                        new DistrictData(1, "Dhaka"),
                        new DistrictData(2, "Faridpur"),
                        new DistrictData(3, "Gazipur"),
                        new DistrictData(4, "Gopalganj"),
                        new DistrictData(5, "Kishoregonj"),
                        new DistrictData(6, "Madaripur"),
                        new DistrictData(7, "Manikganj"),
                        new DistrictData(8, "Munshiganj"),
                        new DistrictData(9, "Narayanganj"),
                        new DistrictData(10, "Narsingdi"),
                        new DistrictData(11, "Rajbari"),
                        new DistrictData(12, "Shariatpur"),
                        new DistrictData(13, "Tangail"),
                        new DistrictData(0, "All District")
                });
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDistrict.setAdapter(spinnerArrayAdapter);

                break;
            }
            case "Chittagong": {
                ArrayAdapter<DistrictData> spinnerArrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, new DistrictData[]{
                        new DistrictData(0, "Select..."),
                        new DistrictData(14, "Bandarban"),
                        new DistrictData(15, "Brahmanbaria"),
                        new DistrictData(16, "Chandpur"),
                        new DistrictData(17, "Chittagong"),
                        new DistrictData(18, "Comilla"),
                        new DistrictData(19, "Coxbazar"),
                        new DistrictData(20, "Feni"),
                        new DistrictData(21, "Khagrachhari"),
                        new DistrictData(22, "Lakshmipur"),
                        new DistrictData(23, "Noakhali"),
                        new DistrictData(24, "Rangamati"),
                        new DistrictData(0, "All District")
                });
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDistrict.setAdapter(spinnerArrayAdapter);

                break;
            }
            case "Khulna": {
                ArrayAdapter<DistrictData> spinnerArrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, new DistrictData[]{
                        new DistrictData(0, "Select..."),
                        new DistrictData(25, "Bagerhat"),
                        new DistrictData(26, "Chuadanga"),
                        new DistrictData(27, "Jessore"),
                        new DistrictData(28, "Jhenaidah"),
                        new DistrictData(29, "Khulna"),
                        new DistrictData(30, "Kushtia"),
                        new DistrictData(31, "Magura"),
                        new DistrictData(32, "Meherpur"),
                        new DistrictData(33, "Narail"),
                        new DistrictData(34, "Satkhira"),
                        new DistrictData(0, "All District")
                });
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDistrict.setAdapter(spinnerArrayAdapter);

                break;
            }
            case "Barisal": {
                ArrayAdapter<DistrictData> spinnerArrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, new DistrictData[]{
                        new DistrictData(0, "Select..."),
                        new DistrictData(35, "Barisal"),
                        new DistrictData(36, "Bhola"),
                        new DistrictData(37, "Jhalokati"),
                        new DistrictData(38, "Patuakhali"),
                        new DistrictData(39, "Pirojpur"),
                        new DistrictData(40, "Barguna"),
                        new DistrictData(0, "All District")
                });
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDistrict.setAdapter(spinnerArrayAdapter);

                break;
            }
            case "Mymenshing": {
                ArrayAdapter<DistrictData> spinnerArrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, new DistrictData[]{
                        new DistrictData(0, "Select..."),
                        new DistrictData(41, "Mymensingh"),
                        new DistrictData(42, "Jamalpur"),
                        new DistrictData(43, "Netrakona"),
                        new DistrictData(44, "Sherpur"),
                        new DistrictData(0, "All District")
                });
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDistrict.setAdapter(spinnerArrayAdapter);

                break;
            }
            case "Rajshahi": {
                ArrayAdapter<DistrictData> spinnerArrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, new DistrictData[]{
                        new DistrictData(0, "Select..."),
                        new DistrictData(45, "Sirajganj"),
                        new DistrictData(46, "Rajshahi"),
                        new DistrictData(47, "Natore"),
                        new DistrictData(48, "Naogaon"),
                        new DistrictData(49, "Pabna"),
                        new DistrictData(50, "Joypurhat"),
                        new DistrictData(51, "Chapainababganj"),
                        new DistrictData(52, "Bogra"),
                        new DistrictData(0, "All District")
                });
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDistrict.setAdapter(spinnerArrayAdapter);

                break;
            }
            case "Sylhet": {
                ArrayAdapter<DistrictData> spinnerArrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, new DistrictData[]{
                        new DistrictData(0, "Select..."),
                        new DistrictData(53, "Sylhet"),
                        new DistrictData(54, "Sunamganj"),
                        new DistrictData(55, "Maulavybazar"),
                        new DistrictData(56, "Habiganj"),
                        new DistrictData(0, "All District")
                });
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDistrict.setAdapter(spinnerArrayAdapter);

                break;
            }
            case "Rangpur": {
                ArrayAdapter<DistrictData> spinnerArrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, new DistrictData[]{
                        new DistrictData(0, "Select..."),
                        new DistrictData(57, "Thakurgaon"),
                        new DistrictData(58, "Rangpur"),
                        new DistrictData(59, "Panchagarh"),
                        new DistrictData(60, "Nilphamari"),
                        new DistrictData(61, "Lalmonirhat"),
                        new DistrictData(62, "Kurigram"),
                        new DistrictData(63, "Gaibandha"),
                        new DistrictData(64, "Dinajpur"),
                        new DistrictData(0, "All District")
                });
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDistrict.setAdapter(spinnerArrayAdapter);

                break;
            }
            case "All Division": {
                ArrayAdapter<DistrictData> spinnerArrayAdapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, new DistrictData[]{
                        new DistrictData(0, "Select..."),
                        new DistrictData(1, "Dhaka"),
                        new DistrictData(2, "Faridpur"),
                        new DistrictData(3, "Gazipur"),
                        new DistrictData(4, "Gopalganj"),
                        new DistrictData(5, "Kishoregonj"),
                        new DistrictData(6, "Madaripur"),
                        new DistrictData(7, "Manikganj"),
                        new DistrictData(8, "Munshiganj"),
                        new DistrictData(9, "Narayanganj"),
                        new DistrictData(10, "Narsingdi"),
                        new DistrictData(11, "Rajbari"),
                        new DistrictData(12, "Shariatpur"),
                        new DistrictData(13, "Tangail"),
                        new DistrictData(14, "Bandarban"),
                        new DistrictData(15, "Brahmanbaria"),
                        new DistrictData(16, "Chandpur"),
                        new DistrictData(17, "Chittagong"),
                        new DistrictData(18, "Comilla"),
                        new DistrictData(19, "Coxbazar"),
                        new DistrictData(20, "Feni"),
                        new DistrictData(21, "Khagrachhari"),
                        new DistrictData(22, "Lakshmipur"),
                        new DistrictData(23, "Noakhali"),
                        new DistrictData(24, "Rangamati"),
                        new DistrictData(25, "Bagerhat"),
                        new DistrictData(26, "Chuadanga"),
                        new DistrictData(27, "Jessore"),
                        new DistrictData(28, "Jhenaidah"),
                        new DistrictData(29, "Khulna"),
                        new DistrictData(30, "Kushtia"),
                        new DistrictData(31, "Magura"),
                        new DistrictData(32, "Meherpur"),
                        new DistrictData(33, "Narail"),
                        new DistrictData(34, "Satkhira"),
                        new DistrictData(35, "Barisal"),
                        new DistrictData(36, "Bhola"),
                        new DistrictData(37, "Jhalokati"),
                        new DistrictData(38, "Patuakhali"),
                        new DistrictData(39, "Pirojpur"),
                        new DistrictData(40, "Barguna"),
                        new DistrictData(41, "Mymensingh"),
                        new DistrictData(42, "Jamalpur"),
                        new DistrictData(43, "Netrakona"),
                        new DistrictData(44, "Sherpur"),
                        new DistrictData(45, "Sirajganj"),
                        new DistrictData(46, "Rajshahi"),
                        new DistrictData(47, "Natore"),
                        new DistrictData(48, "Naogaon"),
                        new DistrictData(49, "Pabna"),
                        new DistrictData(50, "Joypurhat"),
                        new DistrictData(51, "Chapainababganj"),
                        new DistrictData(52, "Bogra"),
                        new DistrictData(53, "Sylhet"),
                        new DistrictData(54, "Sunamganj"),
                        new DistrictData(55, "Maulavybazar"),
                        new DistrictData(56, "Habiganj"),
                        new DistrictData(57, "Thakurgaon"),
                        new DistrictData(58, "Rangpur"),
                        new DistrictData(59, "Panchagarh"),
                        new DistrictData(60, "Nilphamari"),
                        new DistrictData(61, "Lalmonirhat"),
                        new DistrictData(62, "Kurigram"),
                        new DistrictData(63, "Gaibandha"),
                        new DistrictData(64, "Dinajpur"),
                        new DistrictData(0, "All District")
                });
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDistrict.setAdapter(spinnerArrayAdapter);
                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
