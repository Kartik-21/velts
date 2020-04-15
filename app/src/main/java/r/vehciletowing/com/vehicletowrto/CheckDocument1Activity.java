package r.vehciletowing.com.vehicletowrto;

import android.app.ProgressDialog;
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

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import r.vehciletowing.com.vehicletowrto.API.APIResource;
import r.vehciletowing.com.vehicletowrto.POJO.AllUser;
import r.vehciletowing.com.vehicletowrto.POJO.User;

public class CheckDocument1Activity extends AppCompatActivity implements View.OnClickListener {

    private Bundle extra;
    private String result1;
    private ListView list;
    private ArrayList<User> ResultList;
    private ResultAdapter adpaterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_document1);


        extra = getIntent().getExtras();
        result1 = extra.getString("result").toString().trim();

        list = (ListView) findViewById(R.id.listView1);

        ResultList = new ArrayList<User>();

        adpaterList = new ResultAdapter();
        list.setAdapter(adpaterList);


        if (!result1.equals("")) {
            new GetAllDocs().execute();
        }
    }

    public class GetAllDocs extends AsyncTask<Void, Void, AllUser> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(CheckDocument1Activity.this);
            dialog.setMessage("Loading...");
            dialog.show();
            super.onPreExecute();
        }


        @Override
        protected AllUser doInBackground(Void... params) {
            // TODO Auto-generated method stub

            AllUser result = new Gson().fromJson(result1, AllUser.class);
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
                convertView = getLayoutInflater().inflate(R.layout.item_upload_card_inspector_layout, parent, false);

                Holder = new ViewHolder();
                Holder.title = (TextView) convertView.findViewById(R.id.textView2);
                Holder.document = (TextView) convertView.findViewById(R.id.textView4);
                Holder.img = (ImageView) convertView.findViewById(R.id.imageView1);

       //         Holder.btn_Delete = (Button) convertView.findViewById(R.id.btnDele);

                convertView.setTag(Holder);
            } else {
                Holder = (ViewHolder) convertView.getTag();
            }

            Holder.title.setText(ResultList.get(position).getTitle().toString());
            Holder.document.setText(ResultList.get(position).getUType().toString());

//            Holder.btn_Delete.setVisibility(View.GONE);

            String path = ResultList.get(position).getImage().toString().trim();

            if (path != "") {

                Picasso.get()
                        .load(APIResource.BASE_URL + path)
                        .fit()
                        .into(Holder.img);

                //   Picasso.with(getApplicationContext()).load(APIResource.BASE_URL+path).into(Holder.img);

            }
            return convertView;
        }
    }


    private static class ViewHolder {
        TextView title, document;
        ImageView img;
       // Button btn_Delete;
    }


    @Override
    public void onClick(View v) {

    }


}
