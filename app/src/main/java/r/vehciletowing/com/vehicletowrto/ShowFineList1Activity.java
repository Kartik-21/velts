package r.vehciletowing.com.vehicletowrto;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import r.vehciletowing.com.vehicletowrto.API.APICall;
import r.vehciletowing.com.vehicletowrto.POJO.AllUser;
import r.vehciletowing.com.vehicletowrto.POJO.User;

public class ShowFineList1Activity extends AppCompatActivity {

    private String uid;
    private Bundle extra;
    private String suid;
    private String sdate;
    private ListView list;
    private ArrayList<User> ResultList;
    private ResultAdapter adpaterList;
    private String rid;
    private TextView txttotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fine_list1);

        SharedPreferences splogin = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        uid = splogin.getString(LoginActivity.Email, "");
        rid = splogin.getString(LoginActivity.User_ID, "");

        extra = getIntent().getExtras();
        suid = extra.getString("suid").toString().trim();
        sdate = extra.getString("sdate").toString().trim();


        list = (ListView)findViewById(R.id.listView1);

        ResultList = new ArrayList<User>();

        adpaterList = new ResultAdapter();
        list.setAdapter(adpaterList);

        if(uid.trim()!="")
        {
            new GetAllPenalty().execute();
            new GetTotal().execute();
        }

        txttotal = (TextView)findViewById(R.id.textView6);

    }

    public class GetAllPenalty extends AsyncTask<Void,Void,AllUser>
    {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(ShowFineList1Activity.this);
            dialog.setMessage("Loading...");
            dialog.show();
            super.onPreExecute();
        }


        @Override
        protected AllUser doInBackground(Void... params) {
            // TODO Auto-generated method stub

            APICall api = new APICall();
            AllUser result = api.getUTPList(suid.trim(), sdate.trim());
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
                convertView = getLayoutInflater().inflate(R.layout.item_penalty2_layout,parent,false);

                Holder = new ViewHolder();
                Holder.section = (TextView)convertView.findViewById(R.id.textView6);
                Holder.offence = (TextView)convertView.findViewById(R.id.textView9);
                Holder.fine = (TextView)convertView.findViewById(R.id.textView10);

                convertView.setTag(Holder);
            }
            else
            {
                Holder = (ViewHolder)convertView.getTag();
            }

            Holder.section.setText(ResultList.get(position).getSection().toString());
            Holder.offence.setText(ResultList.get(position).getOffence().toString());
            Holder.fine.setText(ResultList.get(position).getFine().toString());

            return convertView;
        }
    }


    private static class ViewHolder
    {
        TextView offence,section,fine;
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
            String result = api.GetFineItemTotal(suid.trim(),sdate.trim());
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

}
