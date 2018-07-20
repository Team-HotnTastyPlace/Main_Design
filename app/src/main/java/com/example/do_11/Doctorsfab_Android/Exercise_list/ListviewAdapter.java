package com.example.do_11.Doctorsfab_Android.Exercise_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.do_11.R;

import java.util.ArrayList;

public class ListviewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Listviewitem> data;
    private int layout;


    public ListviewAdapter(Context context, int layout, ArrayList<Listviewitem> data) {
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i).getName();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {

        if(convertview==null){
            convertview= inflater.inflate(layout,parent,false);
        }

        Listviewitem listviewitem = data.get(position);

        ImageView icon = (ImageView) convertview.findViewById(R.id.imageView_exercise);
        icon.setImageResource(listviewitem.getIcon());

        TextView name = convertview.findViewById(R.id.textView_exercise);
        name.setText(listviewitem.getName());

        return convertview;
    }

}
