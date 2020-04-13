package r.vehciletowing.com.vehicletowrto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyIotAcitvity extends AppCompatActivity {

    private WebView myweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_iot_acitvity);

        myweb =(WebView)findViewById(R.id.webview1);

        WebSettings webSettings = myweb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myweb.loadUrl("http://www.sk-infosoft.top/vehicletracking/login.php");
        myweb.setWebViewClient(new WebViewClient());

    }
}
