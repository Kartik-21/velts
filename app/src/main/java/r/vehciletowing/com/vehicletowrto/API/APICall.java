package r.vehciletowing.com.vehicletowrto.API;

import android.util.Log;

import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;
import r.vehciletowing.com.vehicletowrto.POJO.AllUser;


public class APICall {

    public String saveuser(String name, String did, String cid, String address, String email, String mobile, String password, String aadhar
            , String vehicalNO) {
        String result = "";
        HTTPCall call = new HTTPCall();

        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("v1", name)
                .add("v2", did)
                .add("v3", cid)
                .add("v4", address)
                .add("v5", email)
                .add("v6", mobile)
                .add("v7", password)
                .add("vehicalno", vehicalNO)
                .add("v8", aadhar);

        String url = APIResource.REGISTER_URL;

        RequestBody postData = formBuilder.build();
        result = call.POST(url, postData);
//        Log.d("Result", result);
        return result;
    }

    public String GetUserWallet(String uid) {
        String result = null;
        HTTPCall httpCall = new HTTPCall();

        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("v1", uid);

        String url = APIResource.GET_USER_WALLET_URL;

        RequestBody postData = formBuilder.build();
        result = httpCall.POST(url, postData);
//        Log.d("Result", result);


        return result;
    }

    public String PostWalletAmount(String uid, String amount, String date, String Time, String stype, String ssdate) {
        String result = null;
        HTTPCall call = new HTTPCall();

        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("v1", uid)
                .add("v2", amount)
                .add("v3", date)
                .add("v4", Time)
                .add("v5", stype)
                .add("v6", ssdate);

        String url = APIResource.POST_PAYMENT_AMOUNT_URL;

        RequestBody postData = formBuilder.build();
        result = call.POST(url, postData);
//        Log.d("Result", result);

        return result;
    }

    public AllUser getDistrictList() {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();
        String reqURL = APIResource.GET_DISTRICT_URL;
        String output = httpCall.GET(reqURL);
        result = new Gson().fromJson(output, AllUser.class);
        return result;
    }

    public AllUser getCityList(String did) {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();
        String reqURL = APIResource.GET_CITY_URL + "?did=" + did.trim();
        String output = httpCall.GET(reqURL);
        result = new Gson().fromJson(output, AllUser.class);
        return result;
    }

    public AllUser getUserDetail(String email) {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();
        String reqURL = APIResource.GET_USERDETAIL_URL + "?email=" + email.trim();
        String output = httpCall.GET(reqURL);
        Log.d("Rittz", output);
        result = new Gson().fromJson(output, AllUser.class);
        return result;
    }

    public String PostLogin(String username, String password) {


        String result = "";
        HTTPCall call = new HTTPCall();

        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("v1", username)
                .add("v2", password);

        String url = APIResource.LOGIN_URL;

        RequestBody postData = formBuilder.build();
        result = call.POST(url, postData);
//        Log.d("Result", result);
        return result;

    }

    public String PostLLR(String fname, String lname, String aadhar, String bdate, String did, String cid, String address, String pincode, String vtype, String bgrp, String uid) {
        String result = null;

        HTTPCall call = new HTTPCall();

        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("v1", fname)
                .add("v2", lname)
                .add("v3", aadhar)
                .add("v4", bdate)
                .add("v5", did)
                .add("v6", cid)
                .add("v7", address)
                .add("v8", pincode)
                .add("v9", vtype)
                .add("v10", bgrp)

                .add("v11", uid);

        String url = APIResource.POST_APPLYLLR_URL;

        RequestBody postData = formBuilder.build();
        result = call.POST(url, postData);
//        Log.d("Result", result);


        return result;
    }


    public String PostForgotPassword(String email) {
        String result = null;

        HTTPCall call = new HTTPCall();

        FormBody.Builder formBuilder = new FormBody.Builder()

                .add("v1", email);

        String url = APIResource.GET_FORGOT_PASSWORD_URL;

        RequestBody postData = formBuilder.build();
        result = call.POST(url, postData);
//        Log.d("Result", result);


        return result;
    }

    public String PostDeleteDocument(String did) {
        String result = null;


        HTTPCall call = new HTTPCall();

        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("v1", did);

        String url = APIResource.POST_DELETE_USER_DOCUMENT_URL;

        RequestBody postData = formBuilder.build();
        result = call.POST(url, postData);
//        Log.d("Result", result);


        return result;
    }

    public AllUser getUploadList(String uid) {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();
        String reqURL = APIResource.GET_UPLOADS_URL + "?uid=" + uid.trim();
        String output = httpCall.GET(reqURL);
        Log.d("Rittz", output);
        result = new Gson().fromJson(output, AllUser.class);
        return result;
    }


    public AllUser getLLRList(String uid) {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();
        String reqURL = APIResource.GET_LLR_URL + "?uid=" + uid.trim();
        String output = httpCall.GET(reqURL);

        result = new Gson().fromJson(output, AllUser.class);
        return result;
    }

    public String GetFineItemTotal(String uid, String date) {
        String result = null;


        HTTPCall call = new HTTPCall();

        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("v1", uid)
                .add("v2", date);

        String url = APIResource.POST_PANELTY_TFINE_URL;

        RequestBody postData = formBuilder.build();
        result = call.POST(url, postData);


        return result;
    }

    public String GetUserDocs(String snum) {
        String result = null;


        HTTPCall httpCall = new HTTPCall();

        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("v1", snum);

        String url = APIResource.GET_USER_DOCS_URL;

        RequestBody postData = formBuilder.build();
        result = httpCall.POST(url, postData);
//        Log.d("Result", result);


        return result;
    }

    public AllUser getTPListList(String uid, String utype, String sstype) {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();
        String reqURL = APIResource.GET_PANELTY_TPLIST_URL + "?uid=" + uid.trim() + "&utype=" + utype.trim() + "&sstype=" + sstype;
        Log.d("Rittz", reqURL);
        String output = httpCall.GET(reqURL);
        Log.d("Rittz", output);
        result = new Gson().fromJson(output, AllUser.class);
        return result;
    }

    public AllUser getUTPList(String uid, String date) {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();
        String reqURL = APIResource.GET_PANELTY_UTPLIST_URL + "?uid=" + uid.trim() + "&date=" + date.trim();
        String output = httpCall.GET(reqURL);
        Log.d("Rittz", output);
        result = new Gson().fromJson(output, AllUser.class);
        return result;
    }

    public AllUser getPenaltyList() {
        AllUser result = new AllUser();
        HTTPCall httpCall = new HTTPCall();
        String reqURL = APIResource.GET_PANELTY_URL;
        String output = httpCall.GET(reqURL);
        Log.d("Rittz", output);
        result = new Gson().fromJson(output, AllUser.class);
        return result;
    }

    public String PostFineItem(String uid, String pid, String fine, String date, String rid) {
        String result = null;

        HTTPCall call = new HTTPCall();

        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("v1", uid)
                .add("v5", rid)
                .add("v2", pid)
                .add("v3", fine)
                .add("v4", date);


        String url = APIResource.POST_PANELTY_FINE_URL;

        RequestBody postData = formBuilder.build();
        result = call.POST(url, postData);


        return result;
    }
}
