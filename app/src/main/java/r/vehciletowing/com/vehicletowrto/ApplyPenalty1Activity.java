package r.vehciletowing.com.vehicletowrto;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import r.vehciletowing.com.vehicletowrto.API.APICall;
import r.vehciletowing.com.vehicletowrto.POJO.AllUser;
import r.vehciletowing.com.vehicletowrto.POJO.User;

public class ApplyPenalty1Activity extends AppCompatActivity implements View.OnClickListener {

    private String uid;
    private ListView list;
    private ArrayList<User> ResultList;
    private ResultAdapter adpaterList;
    private TextView txtname;
    private TextView txtaddress;
    private Bundle extra;
    private String sname;
    private String saddress;
    private String schoice;
    private String snumber;
    protected String spid;
    protected String sfine;
    protected String fdate;
    private TextView txttotal;
    private Button btnApply;
    private String rid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_penalty1);

        SharedPreferences splogin = getSharedPreferences(Login.MyPREFERENCES, MODE_PRIVATE);
        uid = splogin.getString(Login.Email, "");
        rid = splogin.getString(Login.User_ID, "");

        extra = getIntent().getExtras();
        sname = extra.getString("name").toString().trim();
        saddress = extra.getString("address").toString().trim();
        schoice = extra.getString("ntype").toString().trim();
        snumber = extra.getString("number").toString().trim();

        txtname = (TextView)findViewById(R.id.textView2);
        txtaddress = (TextView)findViewById(R.id.textView4);
        txttotal = (TextView)findViewById(R.id.textView6);

        txtname.setText(sname);
        txtaddress.setText(saddress);

        list = (ListView)findViewById(R.id.listView1);

        ResultList = new ArrayList<User>();

        adpaterList = new ResultAdapter();
        list.setAdapter(adpaterList);

        if(uid.trim()!="")
        {
            new GetAllPenalty().execute();
        }

        btnApply = (Button)findViewById(R.id.button1);
        btnApply.setOnClickListener(this);
    }


    public class GetAllPenalty extends AsyncTask<Void,Void,AllUser>
    {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(ApplyPenalty1Activity.this);
            dialog.setMessage("Loading...");
            dialog.show();
            super.onPreExecute();
        }


        @Override
        protected AllUser doInBackground(Void... params) {
            // TODO Auto-generated method stub

            APICall api = new APICall();
            AllUser result = api.getPenaltyList();
            return result;
        }

        @Override
        protected void onPostExecute(AllUser result) {
            // TODO Auto-generated method stub
            if(result !=null)
            {
                ResultList.clear();
                ResultList.addAll(result.getData());
            }
            else
            {

            }
            adpaterList.notifyDataSetChanged();
            dialog.dismiss();
            super.onPostExecute(result);
        }


    }

    public class ResultAdapter extends BaseAdapter
    {
        private ViewHolder Holder;


        @Override
        public int getCount()
        {
            // TODO Auto-generated method stub
            return ResultList.size() ;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return ResultList.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            if(convertView==null)
            {
                convertView = getLayoutInflater().inflate(R.layout.item_penalty_layout,parent,false);

                Holder = new ViewHolder();
                Holder.section = (TextView)convertView.findViewById(R.id.textView6);
                Holder.offence = (TextView)convertView.findViewById(R.id.textView9);
                Holder.fine = (TextView)convertView.findViewById(R.id.textView10);
                Holder.cb = (ImageView)convertView.findViewById(R.id.checkBox1);

                convertView.setTag(Holder);
            }
            else
            {
                Holder = (ViewHolder)convertView.getTag();
            }

            Holder.section.setText(ResultList.get(position).getSection().toString());
            Holder.offence.setText(ResultList.get(position).getOffence().toString());
            Holder.fine.setText(ResultList.get(position).getFine().toString());

            Holder.cb.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    spid = ResultList.get(position).getP_ID().toString().trim();
                    sfine = ResultList.get(position).getFine().toString().trim();

                    Date cdate = new Date();
                    fdate = new SimpleDateFormat("dd/MM/yyyy").format(cdate);

                    new PostPITem().execute();
                }
            });

            return convertView;
        }
    }


    private static class ViewHolder
    {
        TextView offence,section,fine;
        ImageView cb;
    }

    private class PostPITem extends AsyncTask<Void, Void, String> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(ApplyPenalty1Activity.this);
            dialog.setMessage("Adding..!");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            APICall api = new APICall();
            String result = api.PostFineItem(snumber, spid, sfine, fdate,rid);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            if (result !=null)
            {
                Toast.makeText(ApplyPenalty1Activity.this, result, Toast.LENGTH_LONG).show();

                if(result.contains("Added.."))
                {
                    new GetTotal().execute();
                }
            }

            dialog.dismiss();
            super.onPostExecute(result);

        }


    }

    private class GetTotal extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            APICall api = new APICall();
            String result = api.GetFineItemTotal(snumber.trim(),fdate.trim());
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            if (result !=null)
            {
                txttotal.setText(result.trim());
            }

            super.onPostExecute(result);

        }


    }

    @Override
    public void onClick(View v) {
        if(v==btnApply)
        {
            finish();
        }
    }


}
