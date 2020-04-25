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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import r.vehciletowing.com.vehicletowrto.API.APICall;
import r.vehciletowing.com.vehicletowrto.POJO.AllUser;
import r.vehciletowing.com.vehicletowrto.POJO.User;

public class UserPenaltyActivity extends AppCompatActivity {

    private String uid;
    private ListView list;
    private ArrayList<User> ResultList;
    private ResultAdapter adpaterList;
    protected String suid;
    protected String sdate;
    protected String sname;
    protected String saddress;
    private Spinner Spintype;
    private ArrayList<String> types;
    private ArrayAdapter<String> spinadapter;
    protected String selecteditem = "Pending";
    protected String sstatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_penalty);

        SharedPreferences splogin = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);

        uid = splogin.getString(LoginActivity.User_ID, "");

//        Spintype = (Spinner) findViewById(R.id.spinner1);

        types = new ArrayList<String>();
        types.add("All");
        types.add("Pending");
        types.add("Paid");

        list = (ListView) findViewById(R.id.listView1);

        ResultList = new ArrayList<User>();

        adpaterList = new ResultAdapter();

        list.setAdapter(adpaterList);


     /*   spinadapter = new ArrayAdapter<String>(UserPenaltyActivity.this, android.R.layout.simple_spinner_dropdown_item, types);
        Spintype.setAdapter(spinadapter);

        Spintype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                selecteditem = Spintype.getSelectedItem().toString();

                ResultList.clear();

                new GetAllFList().execute();

            }
        });

*/
        if (uid.trim() != "") {
            new GetAllFList().execute();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                suid = ResultList.get(position).getUser_ID().toString().trim();
                sdate = ResultList.get(position).getDate().toString().trim();
                sname = ResultList.get(position).getName().toString().trim();
                saddress = ResultList.get(position).getAddress().toString().trim();


                Intent i = new Intent(UserPenaltyActivity.this, UserPenalty1Activity.class);
                i.putExtra("suid", suid.trim());
                i.putExtra("sdate", sdate.trim());
                i.putExtra("sname", sname.trim());
                i.putExtra("saddress", saddress.trim());
                startActivity(i);
                //      finish();

            }
        });

    }

    public class GetAllFList extends AsyncTask<Void, Void, AllUser> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(UserPenaltyActivity.this);
            dialog.setMessage("Loading...");
            dialog.show();
            super.onPreExecute();
        }


        @Override
        protected AllUser doInBackground(Void... params) {
            // TODO Auto-generated method stub

            APICall api = new APICall();
            AllUser result = api.getTPListList(uid, "User", selecteditem.trim());
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

                convertView.setTag(Holder);
            } else {
                Holder = (ViewHolder) convertView.getTag();
            }

            Holder.name.setText(ResultList.get(position).getName().toString());
            Holder.address.setText(ResultList.get(position).getAddress().toString());
            Holder.date.setText(ResultList.get(position).getDate().toString());
            //   Holder.fine.setText(ResultList.get(position).getFine().toString());
            Holder.fine.setText("");

            return convertView;
        }
    }


    private static class ViewHolder {
        TextView name, address, date, fine;
    }


}
