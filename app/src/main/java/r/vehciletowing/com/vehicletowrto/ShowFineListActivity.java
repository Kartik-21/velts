package r.vehciletowing.com.vehicletowrto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import r.vehciletowing.com.vehicletowrto.API.APICall;
import r.vehciletowing.com.vehicletowrto.POJO.AllUser;
import r.vehciletowing.com.vehicletowrto.POJO.User;

public class ShowFineListActivity extends AppCompatActivity {

    private String uid;
    private ListView list;
    private ArrayList<User> ResultList;
    private ResultAdapter adpaterList;
    protected String suid;
    protected String sdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fine_list);

        SharedPreferences splogin = getSharedPreferences(Login.MyPREFERENCES, MODE_PRIVATE);
        uid = splogin.getString(Login.User_ID, "");

        list = (ListView) findViewById(R.id.listView1);

        ResultList = new ArrayList<User>();

        adpaterList = new ResultAdapter();
        list.setAdapter(adpaterList);

        if (uid.trim() != "") {
            new GetAllFList().execute();
        }

/*
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                suid = ResultList.get(position).getUser_ID().toString().trim();
                sdate = ResultList.get(position).getDate().toString().trim();


                Intent i = new Intent(ShowFineListActivity.this, ShowFineList1Activity.class);
                i.putExtra("suid", suid.trim());
                i.putExtra("sdate", sdate.trim());
                startActivity(i);
                finish();

            }
        });
*/

    }

    public class GetAllFList extends AsyncTask<Void, Void, AllUser> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(ShowFineListActivity.this);
            dialog.setMessage("Loading...");
            dialog.show();
            super.onPreExecute();
        }


        @Override
        protected AllUser doInBackground(Void... params) {
            // TODO Auto-generated method stub

            APICall api = new APICall();
            AllUser result = api.getTPListList(uid.trim(), "Employee", "All");
            return result;
        }

        @Override
        protected void onPostExecute(AllUser result) {
            // TODO Auto-generated method stub
            if (result != null) {
                ResultList.clear();
                ResultList.addAll(result.getData());
            } else {

            }
            adpaterList.notifyDataSetChanged();
            dialog.dismiss();
            super.onPostExecute(result);
        }


    }

    public class ResultAdapter extends BaseAdapter {

        private ViewHolder Holder;

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return ResultList.size();
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

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_penalty1_layout, parent, false);

                Holder = new ViewHolder();
                Holder.name = (TextView) convertView.findViewById(R.id.textView6);
                Holder.address = (TextView) convertView.findViewById(R.id.textView1);
                Holder.date = (TextView) convertView.findViewById(R.id.textView9);
                Holder.fine = (TextView) convertView.findViewById(R.id.textView10);
                Holder.relativeLayout1 = (RelativeLayout) convertView.findViewById(R.id.relativeLayout1);

                convertView.setTag(Holder);
            } else {
                Holder = (ViewHolder) convertView.getTag();
            }

            Holder.name.setText(ResultList.get(position).getName().toString());
            Holder.address.setText(ResultList.get(position).getAddress().toString());
            Holder.date.setText(ResultList.get(position).getDate().toString());
            Holder.fine.setText("\u20B9 "+ResultList.get(position).getFine()+" /-");
           // Holder.fine.setText("");

            String paymentStatus = ResultList.get(position).getStatus();

            if (paymentStatus.equals("Paid")) {
                Holder.relativeLayout1.setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                Holder.relativeLayout1.setBackgroundColor(getResources().getColor(R.color.red));
            }

            return convertView;
        }
    }


    private static class ViewHolder {
        TextView name, address, date, fine;
        RelativeLayout relativeLayout1;
    }

}
