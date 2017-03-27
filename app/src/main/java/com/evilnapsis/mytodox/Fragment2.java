package com.evilnapsis.mytodox;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 19/03/2017.
 */

public class Fragment2 extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        DbMaster db = new DbMaster(getContext());
        final List<TaskModel> tasks = db.getFinishedTasks();
        ListView lv = (ListView) view.findViewById(R.id.list_main_2);


        final MyListAdapter ada= new MyListAdapter(getContext(), R.layout.my_list_item, tasks);

        lv.setAdapter(ada);
        return view;

    }

}
