package com.interview.intercomsubmission;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    CustomMethods methods = new CustomMethods(this);
    final double companyLatitude = 53.339428;
    final double companyLongitude = -6.257664;

    // 1 NM = 1.151 Miles
    // 1 M = 1.609344Km
    double constantValue = 60 * 1.1515* 1.609344;
    Map idsHashMap = new HashMap();
    List<JSONObject> customersList= new ArrayList<>();
    private String dataUrl = "https://s3.amazonaws.com/intercom-take-home-test/customers.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(methods.haveNetwork()) {
            new ReadFileAsyncTask(this).execute(dataUrl);
        }
        else{
            Toast.makeText(this, "No Internet Access", Toast.LENGTH_SHORT).show();
        }
    }

    public void setCustomersList(List<JSONObject> customersList){
        this.customersList = customersList;
    }

    public double calculateDistance(double latitude1, double longitude1){

        // The great circle formula
        double absoluteDistance = companyLongitude - longitude1;
        double centralAngle = Math.sin(Math.toRadians(companyLatitude)) * Math.sin(Math.toRadians(latitude1))
                + Math.cos(Math.toRadians(companyLatitude)) * Math.cos(Math.toRadians(latitude1))
                * Math.cos(Math.toRadians(absoluteDistance));
        centralAngle = Math.acos(centralAngle);
        //change radians to degrees
        centralAngle = Math.toDegrees(centralAngle);
        //dist = dist * 60 * 1.151;

        double distanceInDouble = centralAngle* constantValue;

        // round off the value
        distanceInDouble = methods.roundOffDouble(distanceInDouble);
        return distanceInDouble;
    }

    public Map extractData(){

        try{
            if(null!=customersList && customersList.size()>0) {

                for (int i = 0; i < customersList.size(); i++) {

                    //extract JSON Objects from list
                    JSONObject json = customersList.get(i);
                    int userId = json.getInt("user_id");
                    String userName = json.getString("name");
                    String userLatitude = json.getString("latitude");
                    String userLongitude = json.getString("longitude");

                    //change string to double
                    double latitude1 = methods.stringToDouble(userLatitude);
                    double longitude1 = methods.stringToDouble(userLongitude);

                    // calculate great circle distance from Company coordinates
                    double calculatedDistance = calculateDistance(latitude1, longitude1);

                    // check if distance is less than given radius (100KM)
                    if (methods.isLocationInRadius(calculatedDistance)) {
                        idsHashMap.put(userId, userName);
                    } else {

                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSON ERROR", e.getMessage());
        }
        return idsHashMap;
    }

}

