package r.vehciletowing.com.vehicletowrto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import r.vehciletowing.com.vehicletowrto.API.APICall;

public class UserPenalty2Activity extends AppCompatActivity implements View.OnClickListener {

    private Bundle extra;
    private String name;
    private String address;
    private String uid;
    private String total;
    private TextView txttotal;
    private TextView txtwamount;
    private Button btnplace;
    private TextView txtName;
    private TextView txtAddress;
    private RadioGroup rbpayment;
    protected int checkedid;
    protected RadioButton checkedradio;
    public String wamount;
    private String poption;
    private String sdate;
    private String fdate;
    private String ftime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_penalty2);

        extra = getIntent().getExtras();
        name = extra.getString("name");
        address = extra.getString("address");
        uid = extra.getString("uid");
        total= extra.getString("total");
        sdate= extra.getString("date");

        txttotal = (TextView)findViewById(R.id.textView3);
        txtwamount = (TextView)findViewById(R.id.textView5);
        txttotal.setText(total);

        btnplace = (Button)findViewById(R.id.button1);
        btnplace.setOnClickListener(this);

        new GetWallet().execute();

        txtName = (TextView)findViewById(R.id.editText1);
        txtAddress = (TextView)findViewById(R.id.editText3);

        txtName.setText(name);
        txtAddress.setText(address);

        rbpayment = (RadioGroup) findViewById(R.id.radioGroup1);
        rbpayment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub

                checkedid = rbpayment.getCheckedRadioButtonId();
                checkedradio=(RadioButton)findViewById(checkedid);

                if(checkedid != (-1) )
                {

                }
            }
        });

    }

    @Override
    public void onClick(View v) {

        if(v==btnplace)
        {
            checkedid = rbpayment.getCheckedRadioButtonId();
            checkedradio=(RadioButton)findViewById(checkedid);

            if(checkedid == (-1) )
            {
                Toast.makeText(UserPenalty2Activity.this,"Select payment option ",Toast.LENGTH_SHORT).show();
                return;

            }
            else
            {
                poption = checkedradio.getText().toString();

                if(poption.contains("Wallet"))
                {
                    int aawallet = Integer.valueOf(wamount.trim());
                    int pamount = Integer.valueOf(total);

                    if(pamount <= aawallet)
                    {
                        Date cdate = new Date();
                        fdate = new SimpleDateFormat("dd/MM/yyyy").format(cdate);

                        Date cdate1 = new Date();
                        ftime = new SimpleDateFormat("hh:mm").format(cdate1);

                        new PostPayment().execute();

                    }
                    else
                    {
                        Toast.makeText(UserPenalty2Activity.this,"Not enough balance in Wallet",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                else
                {
                    Intent go = new Intent(UserPenalty2Activity.this,PaymentActivity.class);
                    go.putExtra("amount",total);
                    go.putExtra("type","Payment");
                    go.putExtra("date",sdate);
                    startActivity(go);
                    finish();

                }
            }

        }


    }

    private class GetWallet extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            APICall api = new APICall();
            String result = api.GetUserWallet(uid.trim());
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            if (result !=null)
            {
                wamount= result;
                txtwamount.setText("Available Wallet Balance = "+result.trim());
            }

            super.onPostExecute(result);

        }

    }

    private class PostPayment extends AsyncTask<Void, Void, String> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(UserPenalty2Activity.this);
            dialog.setMessage("Please Wait...!");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            APICall api = new APICall();
            String result =api.PostWalletAmount(uid,total, fdate, ftime,"WP",sdate);
            return result;

        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            if (result != null)
            {
            //    Toast.makeText(UserPenalty2Activity.this, result, Toast.LENGTH_LONG).show();

                if(result.contains("Done"))
                {
                    Toast.makeText(UserPenalty2Activity.this, "Payment Done", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
            dialog.dismiss();
            super.onPostExecute(result);

        }
    }


}
