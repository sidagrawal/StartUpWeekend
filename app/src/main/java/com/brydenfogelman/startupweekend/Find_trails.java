package com.brydenfogelman.startupweekend;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Find_trails extends ActionBarActivity {

    SeekBar driving_time;
    SeekBar hike_time;
    TextView driving_time_text;
    TextView hike_time_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_trails);
        driving_time = (SeekBar)findViewById(R.id.driveTimeBar);
        hike_time = (SeekBar)findViewById(R.id.trailTimeBar);
        driving_time_text = (TextView)findViewById(R.id.driving_time);
        hike_time_text = (TextView)findViewById(R.id.hike_time);

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
}