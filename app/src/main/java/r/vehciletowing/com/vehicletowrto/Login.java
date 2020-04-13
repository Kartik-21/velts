package r.vehciletowing.com.vehicletowrto;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import r.vehciletowing.com.vehicletowrto.API.APICall;
import r.vehciletowing.com.vehicletowrto.POJO.AllUser;
import r.vehciletowing.com.vehicletowrto.POJO.User;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmail;
    private EditText editPassword;
    private Button btnLogin;
    private TextView txtSignUp;
    private TextView txtforgot;

    private String semail, spassword;

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Email = "emailKey";
    public static final String Login = "loginKey";
    public static final String User_ID = "uidKey";
    public static final String User_Type = "utypeKey";
    private SharedPreferences sharedpreferences;

    private ArrayList<User> ResultList;
    public String uid;
    public String srole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = (EditText) findViewById(R.id.EtLEmail);
        editPassword = (EditText) findViewById(R.id.EtLPass);

        btnLogin = (Button) findViewById(R.id.btnLLogin);
        btnLogin.setOnClickListener(this);

        txtSignUp = (TextView) findViewById(R.id.tvLSignup);
        txtSignUp.setOnClickListener(this);

        txtforgot = (TextView) findViewById(R.id.tvLforgot);
        txtforgot.setOnClickListener(this);

        ResultList = new ArrayList<User>();

        SharedPreferences splogin = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        String slogin = splogin.getString(Login, "");
        String stype = splogin.getString(User_Type, "");

        //   Toast.makeText(LoginActivity.this, slogin, Toast.LENGTH_LONG).show();


        if (slogin.contains("Yes")) {
            if (stype.contains("User")) {
                Intent go = new Intent(Login.this, HomeActivity.class);
                startActivity(go);
                finish();
            } else if (stype.contains("Employee")) {
                Intent go = new Intent(Login.this, RTOhomeActivity.class);
                startActivity(go);
                finish();
            }
        }

        if (Build.VERSION.SDK_INT >= 23) {
            //do your check here
            isStoragePermissionGranted();
        }

    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //     Log.v(TAG,"Permission is granted");
                return true;
            } else {

                //       Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //     Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //       Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

    @Override
    public void onClick(View v) {
        Validation valid =new Validation();

        if (v == txtSignUp) {
            Intent i = new Intent(Login.this, Register.class);
            startActivity(i);
            finish();
        }

        if (v == txtforgot) {
            Intent i = new Intent(Login.this, ForgotPass.class);
            startActivity(i);
            finish();
        }

        if (v == btnLogin) {
            semail = editEmail.getText().toString().trim();
            spassword = editPassword.getText().toString().trim();

            if (semail.equals("")) {
                editEmail.setError("Enter Email");
                return;
            }   else if(!valid.isValidEmail(semail))
            {
                editEmail.setError("Enter Valid Email");
                return;
            }

            else if (spassword.equals("")) {
                editPassword.setError("Enter Password");
                return;
            } else {
                new PostLogin().execute();
            }
        }

    }


    public class PostLogin extends AsyncTask<Void, Void, String> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            dialog = new ProgressDialog(Login.this);
            dialog.setMessage("Please Wait..!");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            String result;
            APICall api = new APICall();
            result = api.PostLogin(semail, spassword);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub

            if (result != "") {
                if (result.contains("Error")) {
                    Toast.makeText(Login.this, "Invalid Email/Password", Toast.LENGTH_SHORT).show();
                } else {
                    sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(Email, semail.trim());
                    editor.putString(Login, "Yes");
                    editor.putString(User_Type, result.trim());
                    editor.commit();

                    new GetUserDetail().execute();
                    if (result.contains("User")) {
                        srole = "User";
                        Intent i = new Intent(Login.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    } else if (result.contains("Employee")) {
                        srole = "Employee";
                        Intent i = new Intent(Login.this, RTOhomeActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            } else {
                Toast.makeText(Login.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            super.onPostExecute(result);
        }

    }

    public class GetUserDetail extends AsyncTask<Void, Void, AllUser> {
//        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

	/*            dialog = new ProgressDialog(LoginActivity.this);
	            dialog.setMessage("Loading...");
	            dialog.show();
	*/
            super.onPreExecute();
        }


        @Override
        protected AllUser doInBackground(Void... params) {
            // TODO Auto-generated method stub

            APICall api = new APICall();
            AllUser result = api.getUserDetail(semail);
            return result;
        }

        @Override
        protected void onPostExecute(AllUser result) {
            // TODO Auto-generated method stub
            if (result != null) {
                ResultList.addAll(result.getData());

                if (srole.contains("User")) {
                    uid = ResultList.get(0).getUser_ID().toString().trim();
                } else {
                    uid = ResultList.get(0).getEmployee_ID().toString().trim();
                }
                if (uid != "") {
                    //     Toast.makeText(LoginActivity.this,"Uid=" +uid, Toast.LENGTH_LONG).show();

                    sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(User_ID, uid.trim());
                    editor.commit();

                } else {
                    return;
                }
            } else {

            }
            //            dialog.dismiss();
            super.onPostExecute(result);
        }


    }

}
