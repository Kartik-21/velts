package r.vehciletowing.com.vehicletowrto;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import r.vehciletowing.com.vehicletowrto.API.APICall;
import r.vehciletowing.com.vehicletowrto.API.APIResource;
import r.vehciletowing.com.vehicletowrto.POJO.AllUser;
import r.vehciletowing.com.vehicletowrto.POJO.User;

public class MyUploadsActivity extends AppCompatActivity {
    private String uid;
    private ListView list;
    private ArrayList<User> ResultList;
    private ResultAdapter adpaterList;
    private String did;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_uploads);

        SharedPreferences splogin = getSharedPreferences(LoginActivity.MyPREFERENCES, MODE_PRIVATE);
        uid = splogin.getString(LoginActivity.Email, "");


        list = (ListView) findViewById(R.id.listView1);

        ResultList = new ArrayList<User>();

        adpaterList = new ResultAdapter();
        list.setAdapter(adpaterList);

        if (uid.trim() != "") {
            new GetAllUploads().execute();
        }
    }

    public class GetAllUploads extends AsyncTask<Void, Void, AllUser> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

            dialog = new ProgressDialog(MyUploadsActivity.this);
            dialog.setMessage("Loading...");
            dialog.show();
            super.onPreExecute();
        }


        @Override
        protected AllUser doInBackground(Void... params) {
            // TODO Auto-generated method stub

            APICall api = new APICall();
            AllUser result = api.getUploadList(uid.trim());
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
                convertView = getLayoutInflater().inflate(R.layout.item_show_card_user, parent, false);

                Holder = new ViewHolder();
                Holder.title = (TextView) convertView.findViewById(R.id.textView2);
                Holder.document = (TextView) convertView.findViewById(R.id.textView4);
                Holder.img = (ImageView) convertView.findViewById(R.id.imageView1);
                Holder.dele = (Button) convertView.findViewById(R.id.btnDele);


                convertView.setTag(Holder);
            } else {
                Holder = (ViewHolder) convertView.getTag();
            }

            Holder.title.setText(ResultList.get(position).getTitle().toString());
            Holder.document.setText(ResultList.get(position).getUType().toString());

            String path = ResultList.get(position).getImage().toString().trim();

            if (path != "") {
                // Picasso.with(getApplicationContext()).load(APIResource.BASE_URL+path).into(Holder.img);


                Picasso.get()
                        .load(APIResource.BASE_URL + path)
                        .fit()
                        .into(Holder.img);
            }


            builder = new AlertDialog.Builder(MyUploadsActivity.this);
            Holder.dele.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    //Uncomment the below code to Set the message and title from the strings.xml file
         //           builder.setMessage("Do you want to delete ?") .setTitle("");

                    //Setting message manually and performing action on button click
                    builder.setMessage("Do you want to delete ?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    did = ResultList.get(position).getUpload_ID().toString().trim();

                                    new  PostDeleteDocument().execute();

                                    ResultList.remove(position);
                                    adpaterList.notifyDataSetChanged();

                                    dialog.cancel();
                                    /*finish();
                                    Toast.makeText(getApplicationContext(),"you choose yes action for alertbox",
                                            Toast.LENGTH_SHORT).show();*/
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                              /*      Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                            Toast.LENGTH_SHORT).show();
                              */  }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    //alert.setTitle("AlertDialogExample");
                    alert.show();
/*

*/

        }
    });
            return convertView;
}
    }


private static class ViewHolder {
    TextView title, document;
    ImageView img;
    Button dele;
}

public class PostDeleteDocument extends AsyncTask<Void, Void, String> {

    private ProgressDialog dialog;

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        dialog = new ProgressDialog(MyUploadsActivity.this);
        dialog.setMessage("Please Wait..!");
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        // TODO Auto-generated method stub
        String result;
        APICall api = new APICall();
        result = api.PostDeleteDocument(did);
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub

        Toast.makeText(MyUploadsActivity.this, result, Toast.LENGTH_SHORT).show();

        if (result != "") {
            //Toast.makeText(MyUploadsActivity.this,result,Toast.LENGTH_SHORT).show();

            if (result.contains("Deleted")) {
                Toast.makeText(MyUploadsActivity.this, "Document Deleted", Toast.LENGTH_SHORT).show();
                did = "";
            } else {
                Toast.makeText(MyUploadsActivity.this, result, Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(MyUploadsActivity.this, "Check your Internet Connection", Toast.LENGTH_SHORT).show();
        }
        dialog.dismiss();
        super.onPostExecute(result);
    }

}

}
