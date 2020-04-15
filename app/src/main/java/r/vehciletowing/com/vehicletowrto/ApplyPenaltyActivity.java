package r.vehciletowing.com.vehicletowrto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import r.vehciletowing.com.vehicletowrto.API.APICall;
import r.vehciletowing.com.vehicletowrto.POJO.AllUser;
import r.vehciletowing.com.vehicletowrto.POJO.User;

public class ApplyPenaltyActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtname;
    private TextView txtname1;
    private TextView txtaddress;
    private TextView txtaddress1;
    private EditText editnumber;
    private RadioGroup rgtype;
    private Button btnShow;
    private Button btnApply;
    private RadioButton rbchoice;
    private String schoice;
    private String snumber;
    private ArrayList<User> ResultList;
    public String saddress;
    private LinearLayout llayout;
    public String uid;
    private String sname;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_penalty);

        editnumber = (EditText)findViewById(R.id.editText1);
        rgtype = (RadioGroup)findViewById(R.id.radioGroup1);

        llayout = (LinearLayout)findViewById(R.id.listView1);


        txtname = (TextView)findViewById(R.id.textView2);
        txtname1 = (TextView)findViewById(R.id.textView3);
        txtaddress = (TextView)findViewById(R.id.textView4);
        txtaddress1 = (TextView)findViewById(R.id.textView5);

        btnShow = (Button)findViewById(R.id.button1);
        btnShow.setOnClickListener(this);

        btnApply = (Button)findViewById(R.id.button2);
        btnApply.setOnClickListener(this);

        txtname.setVisibility(View.INVISIBLE);
        txtname1.setVisibility(View.INVISIBLE);
        txtaddress.setVisibility(View.INVISIBLE);
        txtaddress1.setVisibility(View.INVISIBLE);
        btnApply.setVisibility(View.INVISIBLE);
        llayout.setVisibility(View.INVISIBLE);

        ResultList = new ArrayList<User>();
    }

    @Override
    public void onClick(View v) {

        if(v==btnShow)
        {
            Validation valid = new Validation();

            int selectedchoice = rgtype.getCheckedRadioButtonId();

            rbchoice = (RadioButton)findViewById(selectedchoice);

            schoice = rbchoice.getText().toString().trim();
            snumber = editnumber.getText().toString().trim();

            if(snumber.equals(""))
            {
                editnumber.setError("Enter Number");
                return;
            }

            if(schoice.contains(("Mobile Number")))
            {
                if(!valid.isValidMobile(snumber.trim()))
                {
                    editnumber.setError("Enter Valid Mobile No");
                    return;
                }
                else
                {
                    new GetUserDetail().execute();
                }
            }
            else if(schoice.contains("Aadhar ID"))
            {
                if(!valid.isValidAadhar(snumber.trim()))
                {
                    editnumber.setError("Enter Valid Aadhar No");
                    return;
                }
                else
                {
                    new GetUserDetail().execute();
                }
            }else if (schoice.contains("Vehical Number")) {
                //           if (!valid.isValidAadhar(snumber.trim())) {
                //             editnumber.setError("Enter Valid Aadhar No");
                //               return;
                //         } else {
                new GetUserDetail().execute();
                //       }
            }

            else
            {

            }
        }

        if(v==btnApply)
        {
            Intent go = new Intent(ApplyPenaltyActivity.this, ApplyPenalty1Activity.class);
            go.putExtra("name",sname.trim());
            go.putExtra("address",saddress.trim());
            go.putExtra("ntype",schoice.trim());
            go.putExtra("number",snumber.trim());
            startActivity(go);
            finish();
        }

    }

    public class GetUserDetail extends AsyncTask<Void,Void,AllUser>
    {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(ApplyPenaltyActivity.this);
            dialog.setMessage("Loading...");
            dialog.show();
            super.onPreExecute();
        }


        @Override
        protected AllUser doInBackground(Void... params) {
            // TODO Auto-generated method stub

            APICall api = new APICall();
            AllUser result = api.getUserDetail(snumber);
            return result;
        }

        @Override
        protected void onPostExecute(AllUser result) {
            // TODO Auto-generated method stub
            if(result !=null)
            {
                ResultList.addAll(result.getData());

                uid = ResultList.get(0).getUser_ID().toString().trim();
                sname = ResultList.get(0).getName().toString().trim();
                saddress = ResultList.get(0).getAddress().toString().trim();

                llayout.setVisibility(View.VISIBLE);
                txtname.setVisibility(View.VISIBLE);
                txtname1.setVisibility(View.VISIBLE);
                txtaddress.setVisibility(View.VISIBLE);
                txtaddress1.setVisibility(View.VISIBLE);
                btnApply.setVisibility(View.VISIBLE);

                txtname1.setText(sname);
                txtaddress1.setText(saddress);

            }
            else
            {
                txtname.setVisibility(View.INVISIBLE);
                txtname1.setVisibility(View.INVISIBLE);
                txtaddress.setVisibility(View.INVISIBLE);
                txtaddress1.setVisibility(View.INVISIBLE);
                btnApply.setVisibility(View.INVISIBLE);
                llayout.setVisibility(View.INVISIBLE);
                Toast.makeText(ApplyPenaltyActivity.this,"No Data", Toast.LENGTH_LONG).show();

            }
            dialog.dismiss();
            super.onPostExecute(result);
        }


    }
}
