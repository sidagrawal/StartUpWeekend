package com.brydenfogelman.startupweekend;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TrailListCustomAdapter extends RecyclerView.Adapter<TrailListCustomAdapter.ViewHolder>{
    private ArrayList<String> name_region;
    private ArrayList<String> diffculty;
    private ArrayList<String> hike_time;
    private ArrayList<String> driving_time;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView trail_name_and_region_view;
        public TextView difficulty_view;
        public TextView driving_time_view;
        public TextView hike_time_view;
        private final Context context;

        public ViewHolder(View view) {
            super(view);
            trail_name_and_region_view = (TextView)view.findViewById(R.id.textView);
            difficulty_view = (TextView)view.findViewById(R.id.difficulty);
            driving_time_view = (TextView)view.findViewById(R.id.driving_time);
            hike_time_view = (TextView)view.findViewById(R.id.trip_time);
            context = view.getContext();
            view.setClickable(true);
            view.setOnClickListener(this);
        }

        public void onClick(View v){
            Intent intent = new Intent(context, TrailDetail.class);
            context.startActivity(intent);
        }
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public TrailListCustomAdapter(ArrayList<String> name_region, ArrayList<String> difficulty,
                                  ArrayList<String> hike_time, ArrayList<String> driving_time) {
        this.name_region = name_region;
        this. diffculty = difficulty;
        this.hike_time = hike_time;
        this.driving_time = driving_time;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrailListCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset[position]);
        holder.trail_name_and_region_view.setText(name_region.get(position));
        holder.difficulty_view.setText(diffculty.get(position));
        holder.hike_time_view.setText(hike_time.get(position));
        holder.driving_time_view.setText("0");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return name_region.size();
    }

}
