package r.vehciletowing.com.vehicletowrto;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import r.vehciletowing.com.vehicletowrto.API.APICall;

public class ForgotPass extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmail;
    private Button btnsend;
    private TextView txtlogin;
    private String semail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        editEmail = (EditText) findViewById(R.id.editText1);

        btnsend = (Button) findViewById(R.id.button1);
        btnsend.setOnClickListener(this);

        txtlogin = (TextView) findViewById(R.id.textView1);
        txtlogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btnsend) {
            semail = editEmail.getText().toString().trim();

            if (semail.equals("")) {
                editEmail.setError("Enter Email");
                return;
            } else {
                new PostForgetPass().execute();
            }
        }

        if (v == txtlogin) {
            Intent i = new Intent(ForgotPass.this, Login.class);
            startActivity(i);
            finish();
        }


    }

    public class PostForgetPass extends AsyncTask<Void, Void, String> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            //
            dialog = new ProgressDialog(ForgotPass.this);
            dialog.setMessage("Please Wait..!");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            String result;

            APICall api = new APICall();
            result = api.PostForgotPassword(semail);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub

            if (result != "") {
                //		Toast.makeText(ForgotPasswordActivity.this,result,Toast.LENGTH_SHORT).show();

                if (result.contains("Sent"))
                //if(result.contains("$pass"))
                {
                    Toast.makeText(ForgotPass.this, "Email sent Successfully..!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ForgotPass.this, Login.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(ForgotPass.this, "Please try again..!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ForgotPass.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            super.onPostExecute(result);
        }

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(ForgotPass.this, Login.class));
        finish();
        super.onBackPressed();
    }
}
