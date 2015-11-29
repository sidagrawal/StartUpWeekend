package com.brydenfogelman.startupweekend;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.MenuItem;
import android.widget.Toolbar;

public class FindTrails extends ActionBarActivity {

    SeekBar driving_time;
    SeekBar hike_time;
    TextView driving_time_text;
    TextView hike_time_text;
    Spinner difficulty;
    Spinner other_locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_trails);

        driving_time = (SeekBar)findViewById(R.id.driveTimeBar);
        hike_time = (SeekBar)findViewById(R.id.trailTimeBar);
        driving_time_text = (TextView)findViewById(R.id.driving_time);
        hike_time_text = (TextView)findViewById(R.id.hike_time);
        difficulty = (Spinner)findViewById(R.id.difficulty);
        other_locations = (Spinner)findViewById(R.id.other_locations);

        driving_time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                driving_time_text.setText(Integer.toString(progress));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });




        hike_time.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                hike_time_text.setText(Integer.toString(progress));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
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

    public void go(View view){
        Intent intent = new Intent(this, TrailList.class);
        Bundle bundle = new Bundle();
        bundle.putString("region", other_locations.getSelectedItem().toString());
        bundle.putString("difficulty", difficulty.getSelectedItem().toString());
        bundle.putString("hike_time", hike_time_text.getText().toString());
        bundle.putString("drive_time", driving_time_text.getText().toString());
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }
}