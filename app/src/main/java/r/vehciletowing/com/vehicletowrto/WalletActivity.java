package r.vehciletowing.com.vehicletowrto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import r.vehciletowing.com.vehicletowrto.API.APICall;

public class WalletActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAdd;
    private TextView txtbalance;
    private String uid;
    private TextView txt1;
    private TextView txt3;
    private EditText editmount;
    private String btntext;
    private TextView txt4;
    private String samount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        SharedPreferences splogin = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        uid = splogin.getString(LoginActivity.User_ID, "");

        txt1 = (TextView)findViewById(R.id.textView1);
        txt3 = (TextView)findViewById(R.id.textView3);
        txt4 = (TextView)findViewById(R.id.textView4);
        txtbalance = (TextView) findViewById(R.id.textView2);

        editmount = (EditText)findViewById(R.id.editText1);
        editmount.setVisibility(View.INVISIBLE);
        txt4.setVisibility(View.INVISIBLE);

        btnAdd = (Button) findViewById(R.id.button1);
        btnAdd.setOnClickListener(this);

        new GetWallet().execute();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        if(v==btnAdd)
        {
            btntext =btnAdd.getText().toString().trim();

            if(btntext.contains("Add Money to Wallet"))
            {
                editmount.setVisibility(View.VISIBLE);
                txt1.setVisibility(View.INVISIBLE);
                txtbalance.setVisibility(View.INVISIBLE);
                txt3.setVisibility(View.INVISIBLE);
                txt4.setVisibility(View.INVISIBLE);

                btnAdd.setText("Add Money");
            }

            if(btntext.equals("Add Money"))
            {
                samount = editmount.getText().toString().trim();

                if(samount.equals(""))
                {
                    editmount.setError("Enter Amount");
                    return;
                }
                else
                {
                    Intent go = new Intent(WalletActivity.this,PaymentActivity.class);
                    go.putExtra("amount",samount);
                    go.putExtra("type","Wallet");
                    go.putExtra("date","NA");
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
                txtbalance.setText(result.trim());
            }

            super.onPostExecute(result);

        }

    }
}
