package r.vehciletowing.com.vehicletowrto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeUserActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnUpload;
    private Button btnmyUpload;
    private Button btnapplyllr;
    private Button btnmyllr;
    private Button btnpenalty;
    private Button btnWallet;
    private  Button btntrack;
    private ImageView btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);

        btnUpload = (Button)findViewById(R.id.button1);
        btnmyUpload = (Button)findViewById(R.id.button2);
        btnapplyllr = (Button)findViewById(R.id.button3);
        btnmyllr = (Button)findViewById(R.id.button4);
        btnpenalty = (Button)findViewById(R.id.button5);
        btnWallet = (Button)findViewById(R.id.button6);
        btntrack = (Button)findViewById(R.id.button7);
        btnLogout = (ImageView)findViewById(R.id.imageView1);

        btnUpload.setOnClickListener(this);
        btnmyUpload.setOnClickListener(this);
        btnapplyllr.setOnClickListener(this);
        btnmyllr.setOnClickListener(this);
        btnpenalty.setOnClickListener(this);
        btnWallet.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btntrack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v==btnUpload)
        {
            Intent i = new Intent(HomeUserActivity.this,UploadDocumentActivity.class);
            startActivity(i);
        }

        if(v==btnmyUpload)
        {
            Intent i = new Intent(HomeUserActivity.this,MyUploadsActivity.class);
            startActivity(i);
        }
        if(v==btnapplyllr)
        {
            Intent i = new Intent(HomeUserActivity.this,ApplyLLRActivity.class);
            startActivity(i);
        }

        if(v==btnmyllr)
        {
            Intent i = new Intent(HomeUserActivity.this,MyLLRActivity.class);
            startActivity(i);
        }

        if(v==btnpenalty)
        {
            Intent i = new Intent(HomeUserActivity.this,UserPenaltyActivity.class);
            startActivity(i);
        }

        if(v==btnWallet)
        {
            Intent i = new Intent(HomeUserActivity.this,WalletActivity.class);
            startActivity(i);
        }
        if(v==btntrack)
        {
            Intent i = new Intent(HomeUserActivity.this, IotActivity.class);
            startActivity(i);
        }
        if(v==btnLogout)
        {
            SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();

            Intent go = new Intent( HomeUserActivity.this, LoginActivity.class);
            startActivity(go);
            finish();
        }

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(HomeUserActivity.this, LoginActivity.class));
        finish();
        super.onBackPressed();
    }
}
