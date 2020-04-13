package r.vehciletowing.com.vehicletowrto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import r.vehciletowing.com.vehicletowrto.API.APICall;

public class CheckDocumentActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editnumber;
    private RadioGroup rgtype;
    private Button btnShow;
    private RadioButton rbchoice;
    private String schoice;
    private String snumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_document);
        editnumber = (EditText) findViewById(R.id.editText1);
        rgtype = (RadioGroup) findViewById(R.id.radioGroup1);

        btnShow = (Button) findViewById(R.id.button1);
        btnShow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnShow) {
            Validation valid = new Validation();

            int selectedchoice = rgtype.getCheckedRadioButtonId();

            rbchoice = (RadioButton) findViewById(selectedchoice);

            schoice = rbchoice.getText().toString().trim();
            snumber = editnumber.getText().toString().trim();

            if (snumber.equals("")) {
                editnumber.setError("Enter Number");
                return;
            }

            if (schoice.contains(("Mobile Number"))) {
                if (!valid.isValidMobile(snumber.trim())) {
                    editnumber.setError("Enter Valid Mobile No");
                    return;
                } else {
                    new CheckDoc().execute();
                }
            } else if (schoice.contains("Aadhar ID")) {
                if (!valid.isValidAadhar(snumber.trim())) {
                    editnumber.setError("Enter Valid Aadhar No");
                    return;
                } else {
                    new CheckDoc().execute();
                }
            } else if (schoice.contains("Vahical Number")) {
                //           if (!valid.isValidAadhar(snumber.trim())) {
                //             editnumber.setError("Enter Valid Aadhar No");
                //               return;
                //         } else {
                new CheckDoc().execute();
                //       }
            }

        }
    }

    private class CheckDoc extends AsyncTask<Void, Void, String> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(CheckDocumentActivity.this);
            dialog.setMessage("Please Wait...!");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            APICall api = new APICall();
            String result = api.GetUserDocs(snumber);
            return result;

        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            if (result != null) {
                //        Toast.makeText(CheckDocumentActivity.this, result, Toast.LENGTH_LONG).show();

                if (result.startsWith("No")) {
                    Toast.makeText(CheckDocumentActivity.this, "No Data", Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent(CheckDocumentActivity.this, CheckDocument1Activity.class);
                    i.putExtra("result", result.trim());
                    startActivity(i);
                    finish();
                }

            }
            dialog.dismiss();
            super.onPostExecute(result);

        }
    }
}
