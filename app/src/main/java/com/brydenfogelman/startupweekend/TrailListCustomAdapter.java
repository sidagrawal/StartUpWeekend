package com.brydenfogelman.startupweekend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrailListCustomAdapter extends BaseAdapter{
    ArrayList<String> region;
    Context context;
    ArrayList<Integer> imageId;
    private static LayoutInflater inflater=null;
    public TrailListCustomAdapter(Context context, ArrayList<String> prgmNameList, ArrayList<Integer> prgmImages) {
        // TODO Auto-generated constructor stub
        region=prgmNameList;
        this.context=context;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return region.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.friend_item_view, null);
        holder.tv=(TextView) rowView.findViewById(R.id.screenNameView);
        holder.img=(ImageView) rowView.findViewById(R.id.avatarView);
        holder.tv.setText(region.get(position));
        holder.img.setImageResource(imageId.get(position));
        return rowView;
    }

}
