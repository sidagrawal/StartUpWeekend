package com.brydenfogelman.startupweekend;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Trail_List extends ActionBarActivity {
    String region;
    String difficulty;
    String hike_time;
    String drive_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail__list);
        region = getIntent().getExtras().getString("region");
        difficulty = getIntent().getExtras().getString("difficulty");
        hike_time = getIntent().getExtras().getString("hike_time");
        drive_time = getIntent().getExtras().getString("drive_time");
        Log.d("hello", "Hello");
        String trails = "";
        try {
            trails = new phpGetTrails(this).execute(region, difficulty, hike_time, drive_time).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("Hello", "Hello");

    }
}

class phpGetTrails extends AsyncTask<String, Void, String> {
    private Context context;

    public phpGetTrails(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String region = params[0].trim();
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
