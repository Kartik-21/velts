package r.vehciletowing.com.vehicletowrto;

import android.app.UiAutomation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RTOhomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCheck;
    private Button btnPenalty;
    private Button btnfinelist;
    private ImageView btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtohome);

        btnCheck = (Button)findViewById(R.id.button1);
        btnPenalty = (Button)findViewById(R.id.button2);
        btnfinelist = (Button)findViewById(R.id.button3);
        btnLogout = (ImageView)findViewById(R.id.imageView1);

        btnCheck.setOnClickListener(this);
        btnPenalty.setOnClickListener(this);
        btnfinelist.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnCheck)
        {
            Intent i = new Intent(RTOhomeActivity.this,CheckDocumentActivity.class);
            startActivity(i);
        }

        if(v==btnPenalty)
        {
            Intent i = new Intent(RTOhomeActivity.this,ApplyPenaltyActivity.class);
            startActivity(i);
        }

        if(v==btnfinelist)
        {
            Intent i = new Intent(RTOhomeActivity.this,ShowFineListActivity.class);
            startActivity(i);
        }

        if(v==btnLogout)
        {
            SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.commit();

            Intent go = new Intent( RTOhomeActivity.this,Login.class);
            startActivity(go);
            finish();
        }
    }
}
