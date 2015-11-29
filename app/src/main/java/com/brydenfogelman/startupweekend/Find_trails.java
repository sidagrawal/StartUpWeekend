package com.brydenfogelman.startupweekend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class Find_trails extends Activity {

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

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                hike_time_text.setText(Integer.toString(progress));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    public void go(View view){
        Intent intent = new Intent(this, Trail_List.class);
        Bundle bundle = new Bundle();
        bundle.putString("region", other_locations.getSelectedItem().toString());
        bundle.putString("difficulty", difficulty.getSelectedItem().toString());
        bundle.putString("hike_time", hike_time_text.getText().toString());
        bundle.putString("drive_time", driving_time_text.getText().toString());
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }
}