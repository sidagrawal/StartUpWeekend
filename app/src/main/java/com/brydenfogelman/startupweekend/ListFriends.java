package com.brydenfogelman.startupweekend;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ListFriends extends ActionBarActivity {

    ListView lv;
    Context context;

    ArrayList prgmName;
    public static int [] avatars={R.drawable.blank_profile_photo,R.drawable.blank_profile_photo,R.drawable.blank_profile_photo,R.drawable.blank_profile_photo,};
    public static String [] screenNames={"Bryden","Jay","Sid","Tunir"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, screenNames, avatars));

    }

}
