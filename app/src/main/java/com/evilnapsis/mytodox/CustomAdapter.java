package com.evilnapsis.mytodox;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lenovo on 18/03/2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<TaskModel> tasks;

    CustomAdapter(Context ctx, List<TaskModel> tl){
        this.context=ctx;
        this.tasks=tl;
    }


    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tasks.indexOf(getItemId(position));
    }

    private class VH{
        TextView title;
        TextView project;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH v = null;
        LayoutInflater linfl = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
//            v = LayoutInflater.from(getContext());
            convertView = linfl.inflate(R.layout.my_list_item,null);
            v = new VH();
            v.title  = (TextView)convertView.findViewById(R.id.task_title_li);
            v.project  = (TextView)convertView.findViewById(R.id.task_status_li);

        }else{
            v = (VH)convertView.getTag();
        }
        TaskModel task = tasks.get(position);
        v.title.setText(task.title);
        v.project.setText("Principal");

        return convertView;
    }
}
