package com.evilnapsis.mytodox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lenovo on 18/03/2017.
 */

public class MyListAdapter extends ArrayAdapter<TaskModel> {

    public MyListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public MyListAdapter(Context context, int resource, List<TaskModel> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.my_list_item, null);
        }

        TaskModel p = getItem(position);

        if (p != null) {
            ImageView img = (ImageView) v.findViewById(R.id.icon_li);
            TextView tt1 = (TextView) v.findViewById(R.id.task_title_li);
            TextView tt2 = (TextView) v.findViewById(R.id.task_status_li);
//            TextView tt3 = (TextView) v.findViewById(R.id.description);

            if(img!=null){
                if(p.is_important==1) {
                    img.setImageResource(android.R.drawable.star_on);
                }
                else {
                    img.setImageResource(android.R.drawable.star_off);
                }
            }

            if (tt1 != null) {
                tt1.setText(p.title);
            }

            if (tt2 != null) {

                tt2.setText(p.project_name);
            }

        }

        return v;
    }

}