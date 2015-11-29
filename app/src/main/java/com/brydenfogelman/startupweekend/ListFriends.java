package com.brydenfogelman.startupweekend;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;

public class ListFriends extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_friends);
        ArrayList<String> friends = new ArrayList<String>();
        friends.add("Sid");
        friends.add("Jay");
        friends.add("Brydon");
        friends.add("Tunir");
        int [] prgmImages={R.drawable.blank_profile_photo,R.drawable.blank_profile_photo,R.drawable.blank_profile_photo,R.drawable.blank_profile_photo};
        this.setListAdapter(new CustomAdapter(this, friends, prgmImages));
    }

}
