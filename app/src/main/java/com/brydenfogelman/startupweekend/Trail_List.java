package com.brydenfogelman.startupweekend;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Trail_List extends ListActivity {
    String region;
    String difficulty;
    String hike_time;
    String drive_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail__list);
        region = getIntent().getBundleExtra("bundle").getString("region");
        difficulty = getIntent().getBundleExtra("bundle").getString("difficulty");
        hike_time = getIntent().getBundleExtra("bundle").getString("hike_time");
        drive_time = getIntent().getBundleExtra("bundle").getString("drive_time");
        String trails = "";
        try {
            trails = new phpGetTrails(this).execute(region, difficulty, hike_time, drive_time).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList<String> listOfRegion = new ArrayList();
        ArrayList<String> listOfDifficulty = new ArrayList();
        ArrayList<String> listOfHikeTime = new ArrayList();
        ArrayList<String> listOfDriveTime = new ArrayList();
        ArrayList<String> listOfName = new ArrayList();

        JSONObject object=null;

        try {
            object = new JSONObject(trails);
            JSONArray trailsArray = object.getJSONArray("trails");
            for (int i=0; i<trailsArray.length(); i++) {
                JSONObject trailObject = trailsArray.getJSONObject(i);
                String region = trailObject.getString("region");
                listOfRegion.add(region);
                String difficulty = trailObject.getString("diff");
                listOfDifficulty.add(difficulty);
                String hikeTime = trailObject.getString("time");
                listOfHikeTime.add(hikeTime);
                String name = trailObject.getString("name");
                listOfName.add(name);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        int [] prgmImages={R.drawable.blank_profile_photo,R.drawable.blank_profile_photo,R.drawable.blank_profile_photo,R.drawable.blank_profile_photo};
        //this.setListAdapter(new ArrayAdapter<String>(this, R.layout.mylist, R.id.Itemname, listOfRegion));
        this.setListAdapter( new CustomAdapter(this, listOfRegion, prgmImages));



    }
}

class phpGetTrails extends AsyncTask<String, Void, String> {
    private Context context;

    public phpGetTrails(Context context) {
        this.context = context;
    }

    private String makeNetworkFriendly(String str){
        return str.replace(" ", "%20");
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String region = params[0].trim().replace(" ", "%20");
            String difficulty = params[1].trim();
            String hike_time = params[2].trim();
            String drive_time = params[3].trim();

            String link = "http://159.203.66.71/Startup%20Weekend/get_trails.php?region=" +
                    region + "&difficulty="+ difficulty + "&hike_time=" + hike_time  + "&drive_time=" +
                    drive_time;
            Log.d("link", link);

            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer sb = new StringBuffer("");
            String line = "";
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
            Log.d("result", sb.toString());
            return sb.toString();
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }
}
