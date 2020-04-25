package r.vehciletowing.com.vehicletowrto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import r.vehciletowing.com.vehicletowrto.API.APICall;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener {

    private Bundle extra;
    private String ssamount;
    private TextView txtamount;
    private Spinner spintype;
    private Spinner spinmonth;
    private Spinner spinyear;
    private EditText editcno1;
    private EditText editcno2;
    private EditText editcno3;
    private EditText editcno4;
    private EditText editcvv;
    private ArrayList<String> types;
    private ArrayList<String> months;
    private ArrayList<String> years;
    private ArrayAdapter<String> adpater1;
    private ArrayAdapter<String> adpater2;
    private ArrayAdapter<String> adpater3;
    private Button btnPay;
    private String scno1;
    private String scno2;
    private String scno3;
    private String scno4;
    private String scvv;
    private String smm;
    private String syy;
    private String scno;
    private String fdate;
    private String ftime;
    private String uid;
    private String sstype;
    private String ssdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        SharedPreferences splogin = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        uid = splogin.getString(LoginActivity.User_ID, "");

        extra = getIntent().getExtras();
        ssamount = extra.getString("amount").toString().trim();
        sstype = extra.getString("type").toString().trim();
        ssdate = extra.getString("date").toString().trim();

        txtamount = (TextView) findViewById(R.id.textView6);
        txtamount.setText("Pay " + ssamount + " Rs");

        spintype = (Spinner) findViewById(R.id.spinner1);
        spinmonth = (Spinner) findViewById(R.id.spinner2);
        spinyear = (Spinner) findViewById(R.id.spinner3);

        editcno1 = (EditText) findViewById(R.id.editText1);
        editcno2 = (EditText) findViewById(R.id.editText3);
        editcno3 = (EditText) findViewById(R.id.editText4);
        editcno4 = (EditText) findViewById(R.id.editText5);
        editcvv = (EditText) findViewById(R.id.editText2);

        types = new ArrayList<String>();
        months = new ArrayList<String>();
        years = new ArrayList<String>();

        types.add("Credit Card");
        types.add("Debit Card");

        months.add("MM");
        months.add("01");
        months.add("02");
        months.add("03");
        months.add("04");
        months.add("05");
        months.add("06");
        months.add("07");
        months.add("08");
        months.add("09");
        months.add("10");
        months.add("11");
        months.add("12");

        years.add("YY");
        //  years.add("2017");
        // years.add("2018");
        //years.add("2019");
        years.add("2020");
        years.add("2021");
        years.add("2022");
        years.add("2023");
        years.add("2024");
        years.add("2025");
        years.add("2026");
        years.add("2027");
        years.add("2028");

        adpater1 = new ArrayAdapter<String>(PaymentActivity.this, android.R.layout.simple_spinner_dropdown_item, types);
        adpater2 = new ArrayAdapter<String>(PaymentActivity.this, android.R.layout.simple_spinner_dropdown_item, months);
        adpater3 = new ArrayAdapter<String>(PaymentActivity.this, android.R.layout.simple_spinner_dropdown_item, years);

        spintype.setAdapter(adpater1);
        spinmonth.setAdapter(adpater2);
        spinyear.setAdapter(adpater3);

        editcno1.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editcno1.getText().toString().length() == 4)     //size as per your requirement
                {
                    editcno2.requestFocus();
                }
            }


            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }


        });

        editcno2.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editcno2.getText().toString().length() == 4)     //size as per your requirement
                {
                    editcno3.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });


        editcno3.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (editcno3.getText().toString().length() == 4)     //size as per your requirement
                {
                    editcno4.requestFocus();
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

        });

        //	 editcno1.addTextChangedListener(new FourDigitCardFormatWatcher());
        //	 editcno2.addTextChangedListener(new FourDigitCardFormatWatcher());

        btnPay = (Button) findViewById(R.id.button1);
        btnPay.setOnClickListener(this);
    }

    public static class FourDigitCardFormatWatcher implements TextWatcher {

        // Change this to what you want... ' ', '-' etc..
        private static final char space = ' ';

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Remove spacing char
            if (s.length() > 0 && (s.length() % 5) == 0) {
                final char c = s.charAt(s.length() - 1);
                if (space == c) {
                    s.delete(s.length() - 1, s.length());
                }
            }
            // Insert char where needed.
            if (s.length() > 0 && (s.length() % 5) == 0) {
                char c = s.charAt(s.length() - 1);
                // Only if its a digit where there should be a space we insert a space
                if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(space)).length <= 3) {
                    s.insert(s.length() - 1, String.valueOf(space));
                }
            }
        }

    }

    @Override
    public void onClick(View v) {

        if (v == btnPay) {
            scno1 = editcno1.getText().toString().trim();
            scno2 = editcno2.getText().toString().trim();
            scno3 = editcno3.getText().toString().trim();
            scno4 = editcno4.getText().toString().trim();
            scvv = editcvv.getText().toString().trim();

            smm = spinmonth.getSelectedItem().toString().trim();
            syy = spinyear.getSelectedItem().toString().trim();

            scno = (new StringBuilder()).append(scno1).append(scno2).append(scno3).append(scno4).toString();

            if (scno1.equals("") || scno2.equals("") || scno3.equals("") || scno4.equals("")) {
                Toast.makeText(PaymentActivity.this, "Enter Valid Card Number", Toast.LENGTH_SHORT).show();
                return;
            } else if (scno.length() == 16) {
                if (smm.contains("MM") || syy.contains("YY")) {
                    Toast.makeText(PaymentActivity.this, "Select Valid Expiry Date", Toast.LENGTH_SHORT).show();
                    return;
                } else if (scvv.equals("")) {
                    Toast.makeText(PaymentActivity.this, "Enter CVV", Toast.LENGTH_SHORT).show();
                    return;
                } else if (scvv.length() == 3) {
                    Date cdate = new Date();
                    fdate = new SimpleDateFormat("dd/MM/yyyy").format(cdate);

                    Date cdate1 = new Date();
                    ftime = new SimpleDateFormat("hh:mm").format(cdate1);

                    new PostPayment().execute();
                } else {
                    Toast.makeText(PaymentActivity.this, "Enter Valid CVV", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {
                Toast.makeText(PaymentActivity.this, "Enter Valid Card Number", Toast.LENGTH_SHORT).show();
                return;
            }
        }


    }

    private class PostPayment extends AsyncTask<Void, Void, String> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(PaymentActivity.this);
            dialog.setMessage("Please Wait...!");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            APICall api = new APICall();
            String result = api.PostWalletAmount(uid, ssamount, fdate, ftime, sstype, ssdate);
            return result;

        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            if (result != null) {
                Toast.makeText(PaymentActivity.this, result, Toast.LENGTH_LONG).show();

                if (result.contains("Added..!")) {
                    startActivity(new Intent(PaymentActivity.this, HomeUserActivity.class));
                    finish();
                }

                if (result.contains("Done")) {
                    finish();
                }

            }
            dialog.dismiss();
            super.onPostExecute(result);

        }
    }
}
