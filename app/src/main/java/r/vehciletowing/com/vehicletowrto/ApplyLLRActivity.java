package r.vehciletowing.com.vehicletowrto;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import r.vehciletowing.com.vehicletowrto.API.APICall;
import r.vehciletowing.com.vehicletowrto.POJO.AllUser;
import r.vehciletowing.com.vehicletowrto.POJO.User;

public class ApplyLLRActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spindistrict;
    private Spinner spincity;
    private ArrayList<User> AllDistrict;
    private ArrayList<User> AllCity;
    private ArrayList<String> districts;
    private ArrayList<String> cities;
    private ArrayAdapter<String> DistrictAdapter;
    private ArrayAdapter<String> CityAdapter;
    protected int selecteddistrict;
    protected String did;
    private ArrayList<String> vtypes;
    private ArrayList<String> bgroups;
    private Spinner spinvtype;
    private Spinner spinbgroup;
    private ArrayAdapter<String> VTypeAdapter;
    private ArrayAdapter<String> BGroupAdapter;
    private EditText editfname;
    private EditText editaadhar;
    private EditText editlname;
    private EditText editdob;
    private EditText editaddress;
    private EditText editpincode;
    private Button btnApply;
    private String fname;
    private String lname;
    private String aadhar;
    private String dob;
    private String address;
    private String pincode;
    private String blood;
    private String vehical;
    private int selectedcity;
    private String scity;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_llr);

        SharedPreferences splogin = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        uid = splogin.getString(LoginActivity.User_ID, "");

        spindistrict = (Spinner)findViewById(R.id.spinner1);
        spincity = (Spinner)findViewById(R.id.spinner2);
        spinvtype = (Spinner)findViewById(R.id.spinner3);
        spinbgroup = (Spinner)findViewById(R.id.spinner4);

        AllDistrict = new ArrayList<User>();
        AllCity = new ArrayList<User>();

        districts = new ArrayList<String>();
        cities = new ArrayList<String>();
        vtypes = new ArrayList<String>();
        bgroups = new ArrayList<String>();

        districts.add("Select District");
        cities.add("Select City");


        vtypes.add("Select Vehical Type");
        vtypes.add("MOTOR CYCLE WITHOUT GEAR");
        vtypes.add("MOTOR CYCLE WITH GEAR");
        vtypes.add("LMV -3 WHEELER NT");
        vtypes.add("LMV-NT-CAR-(LMV)");
        vtypes.add("LMV-TRACTOR-NT");
        vtypes.add("LMV -3 WHEELER CAB");
        vtypes.add("LMV -3 WHEELER TRANSPORT");

        bgroups.add("Select Bloodgroup");
        bgroups.add("O Positive");
        bgroups.add("O Negative");
        bgroups.add("A Positive");
        bgroups.add("A Negative");
        bgroups.add("B Positive");
        bgroups.add("B Negative");
        bgroups.add("AB Positive");
        bgroups.add("AB Negative");

        DistrictAdapter = new ArrayAdapter<String>(ApplyLLRActivity.this,android.R.layout.simple_spinner_dropdown_item,districts);
        CityAdapter = new ArrayAdapter<String>(ApplyLLRActivity.this,android.R.layout.simple_spinner_dropdown_item,cities);

        VTypeAdapter = new ArrayAdapter<String>(ApplyLLRActivity.this,android.R.layout.simple_spinner_dropdown_item,vtypes);
        BGroupAdapter = new ArrayAdapter<String>(ApplyLLRActivity.this,android.R.layout.simple_spinner_dropdown_item,bgroups);

        spindistrict.setAdapter(DistrictAdapter);
        spincity.setAdapter(CityAdapter);
        spinvtype.setAdapter(VTypeAdapter);
        spinbgroup.setAdapter(BGroupAdapter);

        new getDistrict().execute();
        spindistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                selecteddistrict = spindistrict.getSelectedItemPosition();

                if(selecteddistrict==0)
                {
                    cities.clear();
                    cities.add("Select City");
                }
                else
                {
                    did = AllDistrict.get(selecteddistrict-1).getDistrict_ID().toString().trim();
                    new getCity().execute();
                }
                CityAdapter.notifyDataSetChanged();
            }
        });

        editfname= (EditText)findViewById(R.id.editText1);
        editlname= (EditText)findViewById(R.id.editText2);
        editaadhar= (EditText)findViewById(R.id.editText3);
        editdob= (EditText)findViewById(R.id.editText4);
        editaddress= (EditText)findViewById(R.id.editText5);
        editpincode= (EditText)findViewById(R.id.editText6);

        btnApply = (Button)findViewById(R.id.button1);
        btnApply.setOnClickListener(this);

    }
    public class getDistrict extends AsyncTask<Void, Void,AllUser>
    {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(ApplyLLRActivity.this);
            dialog.setMessage("Please wait");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected AllUser doInBackground(Void... params) {
            // TODO Auto-generated method stub

            APICall api = new APICall();
            AllUser result = api.getDistrictList();
            return result;
        }

        @Override
        protected void onPostExecute(AllUser result) {
            // TODO Auto-generated method stub

            if(result != null)
            {
                AllDistrict.addAll(result.getData());
                for(User s : result.getData())
                {
                    districts.add(s.getDistrict_Name());
                }

            }
            else
            {

            }
            DistrictAdapter.notifyDataSetChanged();
            dialog.dismiss();
            super.onPostExecute(result);
        }
    }

    public class getCity extends AsyncTask<Void, Void,AllUser>
    {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(ApplyLLRActivity.this);
            dialog.setMessage("Please wait");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected AllUser doInBackground(Void... params) {
            // TODO Auto-generated method stub

            APICall api = new APICall();
            AllUser result = api.getCityList(did);
            return result;
        }

        @Override
        protected void onPostExecute(AllUser result) {
            // TODO Auto-generated method stub

            if(result != null)
            {
                AllCity.addAll(result.getData());
                for(User s : result.getData())
                {
                    cities.add(s.getCity_Name());
                }

            }
            else
            {

            }
            CityAdapter.notifyDataSetChanged();
            dialog.dismiss();
            super.onPostExecute(result);
        }
    }

    @Override
    public void onClick(View v) {

        if(v==btnApply)
        {
            fname= editfname.getText().toString();
            lname= editlname.getText().toString();
            aadhar= editaadhar.getText().toString();
            dob= editdob.getText().toString();
            address= editaddress.getText().toString();
            pincode= editpincode.getText().toString();
            blood = spinbgroup.getSelectedItem().toString();
            vehical=spinvtype.getSelectedItem().toString();

            selectedcity = spincity.getSelectedItemPosition();

            if(selectedcity!=0)
            {
                scity = AllCity.get(selectedcity-1).getCity_ID().toString().trim();
            }


            if(fname.trim().equals(""))
            {
                editfname.setError("Enter First Name");
                return;
            }
            else if(lname.trim().equals(""))
            {
                editlname.setError("Enter Last Name");
                return;
            }
            else if(aadhar.trim().equals(""))
            {
                editaadhar.setError("Enter Aadhar ID");
                return;
            }
            else if(selecteddistrict==0)
            {
                Toast.makeText(ApplyLLRActivity.this,"Select District",Toast.LENGTH_SHORT).show();
                return;
            }
            else if(selectedcity==0)
            {
                Toast.makeText(ApplyLLRActivity.this,"Select City",Toast.LENGTH_SHORT).show();
                return;
            }
            else if(address.trim().equals(""))
            {
                editaddress.setError("Enter Address");
                return;
            }
            else if(pincode.trim().equals(""))
            {
                editpincode.setError("Enter Pincode");
                return;
            }
            else if(dob.trim().equals(""))
            {
                editdob.setError("Enter Birthdate");
                return;
            }
            else if(vehical.trim().equals("Select Vehical Type"))
            {
                Toast.makeText(ApplyLLRActivity.this,"Select Vehical Type",Toast.LENGTH_SHORT).show();
                return;
            }
            else if(blood.trim().equals("Select Bloodgroup"))
            {
                Toast.makeText(ApplyLLRActivity.this,"Select Bloodgroup",Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {
                new PostLLReg().execute();
            }

        }

    }

    private class PostLLReg extends AsyncTask<Void, Void, String> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(ApplyLLRActivity.this);
            dialog.setMessage("Registering on Server...!");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            APICall api = new APICall();
            String result =api.PostLLR(fname, lname, aadhar,dob , did, scity, address, pincode, vehical, blood,uid);
            return result;

        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            if (result != null)
            {
                Toast.makeText(ApplyLLRActivity.this, result, Toast.LENGTH_LONG).show();

                if(result.contains("Added"))
                {
                    finish();
                }

            }
            dialog.dismiss();
            super.onPostExecute(result);

        }
    }
}
