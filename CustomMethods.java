package com.interview.intercomsubmission;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class CustomMethods {

    final double radiusToCheck = 100;

    public Context mContext;
    public CustomMethods(Context mContext) {
        this.mContext = mContext;
    }

    public Set<Map.Entry<String, String>> sortHashMap(Map mMap){
        TreeMap<String, String> sortedTree = new TreeMap<>(mMap);
        Set<Map.Entry<String, String>> sortedMap = sortedTree.entrySet();
        System.out.println(sortedMap);

        return sortedMap;
    }

    public double stringToDouble(String str){
        return Double.parseDouble(str);
    }

    public double roundOffDouble(double doubleValue){

        NumberFormat nf = NumberFormat.getNumberInstance();
        // Maximum Digits after decimal point
        nf.setMaximumFractionDigits(0);

        nf.setRoundingMode(RoundingMode.HALF_UP);
        String rounded = nf.format(doubleValue).trim();

        return  stringToDouble(rounded);
    }

    public boolean isLocationInRadius(double distance){
        if(distance<radiusToCheck) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean haveNetwork(){
        boolean have_WIFI= false;
        boolean have_MobileData = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)mContext.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for(NetworkInfo info:networkInfos){
            if (info.getTypeName().equalsIgnoreCase("WIFI"))
                if (info.isConnected())
                    have_WIFI=true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE DATA"))
                if (info.isConnected())
                    have_MobileData=true;
        }
        return have_WIFI||have_MobileData;
    }
}
