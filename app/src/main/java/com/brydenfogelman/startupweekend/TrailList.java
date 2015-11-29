package com.brydenfogelman.startupweekend;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

public class TrailList extends Activity {
    String region;
    String difficulty;
    String hike_time;
    String drive_time;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trail__list);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

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
                listOfName.add(name + ", " + region);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        //int [] prgmImages={R.drawable.blank_profile_photo,R.drawable.blank_profile_photo,R.drawable.blank_profile_photo,R.drawable.blank_profile_photo};
        //this.setListAdapter(new ArrayAdapter<String>(this, R.layout.mylist, R.id.Itemname, listOfRegion));
        //this.setListAdapter( new FriendsAdapter(this, listOfRegion, prgmImages));

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        TrailListCustomAdapter myAdapter = new TrailListCustomAdapter(listOfName, listOfDifficulty, listOfHikeTime, listOfDriveTime);
        mRecyclerView.setAdapter(myAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if(id == R.id.action_profile){
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        } else if(id == R.id.action_friends){
            Intent intent = new Intent(this, ListFriends.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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
