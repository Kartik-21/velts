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

public class MyLLRActivity extends AppCompatActivity {
    private String uid;
    private ListView list;
    private ArrayList<User> ResultList;
    private ResultAdapter adpaterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_llr);

        SharedPreferences splogin = getSharedPreferences(Login.MyPREFERENCES, MODE_PRIVATE);
        uid = splogin.getString(Login.User_ID, "");


        list = (ListView)findViewById(R.id.listView1);

        ResultList = new ArrayList<User>();

        adpaterList = new ResultAdapter();
        list.setAdapter(adpaterList);

        if(uid.trim()!="")
        {
            new GetAllLLR().execute();
        }

    }

    public class GetAllLLR extends AsyncTask<Void,Void,AllUser>
    {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(MyLLRActivity.this);
            dialog.setMessage("Loading...");
            dialog.show();
            super.onPreExecute();
        }


        @Override
        protected AllUser doInBackground(Void... params) {
            // TODO Auto-generated method stub

            APICall api = new APICall();
            AllUser result = api.getLLRList(uid.trim());
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

                convertView =getLayoutInflater().inflate(R.layout.item_llr_layout,parent,false);


                Holder = new ViewHolder();
                Holder.name = (TextView)convertView.findViewById(R.id.textView2);
                Holder.address = (TextView)convertView.findViewById(R.id.textView4);
                Holder.bdate = (TextView)convertView.findViewById(R.id.textView7);
                Holder.bgroup = (TextView)convertView.findViewById(R.id.textView6);
                Holder.type = (TextView)convertView.findViewById(R.id.textView9);
                Holder.status = (TextView)convertView.findViewById(R.id.textView10);

                convertView.setTag(Holder);
            }
            else
            {
                Holder = (ViewHolder)convertView.getTag();
            }

            Holder.name.setText(ResultList.get(position).getFName().toString()+"  "+ResultList.get(position).getLName().toString());
            Holder.address.setText(ResultList.get(position).getAddress()+" City  "+ResultList.get(position).getCity_Name().toString()+" District "+ResultList.get(position).getDistrict_Name().toString()+"  "+ResultList.get(position).getPincode().toString());
            Holder.bdate.setText(ResultList.get(position).getBDate().toString());
            Holder.bgroup.setText(ResultList.get(position).getBGroup().toString());
            Holder.type.setText(ResultList.get(position).getVType().toString());
            Holder.status.setText(ResultList.get(position).getStatus().toString());

            return convertView;
        }
    }


    private static class ViewHolder
    {
        TextView type,name,address,bdate,bgroup,status;
    }
}
