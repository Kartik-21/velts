package r.vehciletowing.com.vehicletowrto;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Register extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<User> AllDistrict;
    private ArrayList<String> districts;
    private Spinner spindistrict;
    private ArrayAdapter<String> DistrictAdapter;
    protected int selecteddistrict;

    private ArrayList<User> AllCity;
    private Spinner spincity;
    private ArrayList<String> cities;
    private ArrayAdapter<String> CityAdapter;
    protected String did;
    private String cid1;
    private int selectedcid;
    private EditText editname;
    private EditText editaddress;
    private EditText editaadhar;
    private EditText et_vehicalno;
    private EditText editemail;
    private EditText editmobile;
    private EditText editpassword;
    private EditText editcpassword;
    private Button btnsignup;
    private String sname;
    private String saddress;
    private String semail;
    private String smobile;
    private String spassword;
    private String scpassword;
    private String saadhar;
    private String vehicalNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        spindistrict = (Spinner) findViewById(R.id.spinDistrict);
        spincity = (Spinner) findViewById(R.id.spinCity);
        AllDistrict = new ArrayList<User>();
        AllCity = new ArrayList<User>();

        districts = new ArrayList<String>();
        cities = new ArrayList<String>();

        districts.add("Select District");
        cities.add("Select City");

        DistrictAdapter = new ArrayAdapter<String>(Register.this, android.R.layout.simple_spinner_dropdown_item, districts);
        CityAdapter = new ArrayAdapter<String>(Register.this, android.R.layout.simple_spinner_dropdown_item, cities);

        spindistrict.setAdapter(DistrictAdapter);
        spincity.setAdapter(CityAdapter);

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

                if (selecteddistrict == 0) {
                    cities.clear();
                    cities.add("Select City");
                } else {
                    did = AllDistrict.get(selecteddistrict - 1).getDistrict_ID().toString().trim();
                    new getCity().execute();
                }
                CityAdapter.notifyDataSetChanged();
            }
        });
        editname = (EditText) findViewById(R.id.EtName);
        editaddress = (EditText) findViewById(R.id.EtAddress);
        editaadhar = (EditText) findViewById(R.id.EtAdharNo);
        et_vehicalno = (EditText) findViewById(R.id.et_vehicalno);
        editemail = (EditText) findViewById(R.id.EtEmail);
        editmobile = (EditText) findViewById(R.id.EtMobileNo);
        editpassword = (EditText) findViewById(R.id.EtPassword);
        editcpassword = (EditText) findViewById(R.id.EtConPassword);
        btnsignup = (Button) findViewById(R.id.btnRegister);
        btnsignup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Validation valid =new Validation();

        if (v == btnsignup) {
            sname = editname.getText().toString().trim();
            saddress = editaddress.getText().toString().trim();
            saadhar = editaadhar.getText().toString().trim();
            semail = editemail.getText().toString().trim();
            smobile = editmobile.getText().toString().trim();
            spassword = editpassword.getText().toString().trim();
            scpassword = editcpassword.getText().toString().trim();
            selectedcid = spincity.getSelectedItemPosition();
            vehicalNo = et_vehicalno.getText().toString().trim();


            if (selectedcid != 0) {
                cid1 = AllCity.get(selectedcid - 1).getCity_ID().toString().trim();
            }
            if (sname.equals("")) {
                editname.setError("Enter Name");
                return;
            }
            else if(!valid.isValidOnlyChar(sname))
            {
                editname.setError("Enter Valid Name");
                return;
            }


            else if (selecteddistrict == 0) {
                Toast.makeText(Register.this, "Select District", Toast.LENGTH_SHORT).show();
                return;
            }
           /* else if(selectedcid==0)
            {
                Toast.makeText(Register.this,"Select City",Toast.LENGTH_SHORT).show();
                return;
            }*/

            else if (saddress.equals("")) {
                editaddress.setError("Enter Address");
                return;
            } else if (saadhar.equals("")) {
                editaadhar.setError("Enter Aadhar no");
                return;
            }
            else if(!valid.isValidAadhar(saadhar))
            {
                editaadhar.setError("Enter Valid Adhar No");
                return;
            }



            else if (vehicalNo.equals("")) {
                et_vehicalno.setError("Enter Vehical no");
                return;
            } else if (semail.equals("")) {
                editemail.setError("Enter Email");
                return;
            }

            else if(!valid.isValidEmail(semail))
            {
                editemail.setError("Enter Valid Email");
                return;
            }

            else if (smobile.equals("")) {
                editmobile.setError("Enter Mobile");
                return;
            }
            else if(!valid.isValidMobile(smobile))
            {
                editmobile.setError("Enter Valid Mobile No");
                return;
            }





            else if (spassword.equals("")) {
                editpassword.setError("Enter Password");
                return;
            } else if (scpassword.equals("")) {
                editcpassword.setError("Enter Password Again");
                return;
            } else {
                if (spassword.matches(scpassword)) {
                    //Toast.makeText(Register.this,"APICall",Toast.LENGTH_SHORT).show();
                    new Postregister().execute();
                    // Toast.makeText(this, "i m here 2", Toast.LENGTH_SHORT).show();
                } else {
                    editcpassword.setText("");
                    Toast.makeText(Register.this, "Enter same password", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        }

    }


    private class Postregister extends AsyncTask<Void, Void, String> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(Register.this);
            dialog.setMessage("Please Wait..!");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result;
            APICall api = new APICall();
            result = api.saveuser(sname, did, cid1, saddress, semail, smobile, scpassword, saadhar,vehicalNo);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
          /*if(result!="")
          {
              dialog.dismiss();

              if(result.contains("Registration Successful..!"))
              {
                //  Toast.makeText(Register.this, result, Toast.LENGTH_SHORT).show();
                   *//* Intent i = new Intent(Register.this,LoginActivity.class);
                    startActivity(i);
                    finish();*//*
              }
              else
              {
                  Toast.makeText(Register.this, "Registration Success", Toast.LENGTH_SHORT).show();
                  return;
              }
          }
          else
          {
              Toast.makeText(Register.this,"Check your Internet Connection",Toast.LENGTH_SHORT).show();
          }
          super.onPostExecute(result);*/
            if (result != "") {
                dialog.dismiss();
                Toast.makeText(Register.this, result, Toast.LENGTH_SHORT).show();
                if (result.contains("Registration Successful..!")) {
                    Intent i = new Intent(Register.this, Login.class);
                    startActivity(i);
                    finish();
                } else {
                    return;
                }
            } else {
                Toast.makeText(Register.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(result);

        }
    }

    public class getDistrict extends AsyncTask<Void, Void, AllUser> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(Register.this);
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

            if (result != null) {
                AllDistrict.addAll(result.getData());
                for (User s : result.getData()) {
                    districts.add(s.getDistrict_Name());
                }

            } else {

            }
            DistrictAdapter.notifyDataSetChanged();
            dialog.dismiss();
            super.onPostExecute(result);
        }
    }

    public class getCity extends AsyncTask<Void, Void, AllUser> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(Register.this);
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

            if (result != null) {
                AllCity.addAll(result.getData());
                for (User s : result.getData()) {
                    cities.add(s.getCity_Name());
                }

            } else {

            }
            CityAdapter.notifyDataSetChanged();
            dialog.dismiss();
            super.onPostExecute(result);
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(Register.this, Login.class));
        finish();
        super.onBackPressed();
    }
}