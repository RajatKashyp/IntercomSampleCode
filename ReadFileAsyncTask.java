package com.interview.intercomsubmission;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadFileAsyncTask extends AsyncTask<String, String, List<JSONObject>> {

    List<JSONObject> customersList= new ArrayList<>();
    MainActivity mActivity;
    CustomMethods methods;

    public ReadFileAsyncTask(MainActivity mActivity) {
        this.mActivity = mActivity;
        this.methods = new CustomMethods(mActivity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<JSONObject> doInBackground(String... urls) {

        try {
            // set URL of data
            URL url = new URL(urls[0]);

            // open connection port
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();

            // time out in 60 seconds
            conn.setConnectTimeout(60000);

            //initialize buffered reader to read text file
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            //iterate through data line by line
            String str;
            while ((str = in.readLine()) != null) {
                JSONObject obj = new JSONObject(str);
                customersList.add(obj);
            }

            // close connection port
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("I/O EXCEPTION",e.toString());
        } catch (JSONException e){
            e.printStackTrace();
            Log.e("JSON EXCEPTION",e.toString());
        } catch (Exception e){
            e.printStackTrace();
            Log.e("SOME EXCEPTION",e.toString());
        }
        return customersList;
    }

    @Override
    protected void onPostExecute(List<JSONObject> s) {
        super.onPostExecute(s);

        if(null!=customersList && customersList.size()>0) {

            //set customer list in main activity
            mActivity.setCustomersList(customersList);

            //extract required data from list
            Map customerIdsMap = mActivity.extractData();

            //sort in ascending order
            methods.sortHashMap(customerIdsMap);

        }
        else{
         // No Data
        }
    }
}
