package com.evilnapsis.mytodox;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 19/03/2017.
 */

public class Fragment1 extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
  //      ft.detach(this).attach(this).commit();

        View view= inflater.inflate(R.layout.fragment_1, container, false);


        DbMaster db = new DbMaster(getContext());
        final List<TaskModel> tasks = db.getAllTasks();
        ListView lv = (ListView) view.findViewById(R.id.list_main);

        final List<String> strs = new ArrayList<String>();

        for(TaskModel t: tasks) {
            strs.add(t.title);
        }

//        final ArrayAdapter<String> ada = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);
//final CustomAdapter ada = new CustomAdapter(this, tasks);
        final MyListAdapter ada= new MyListAdapter(getContext(), R.layout.my_list_item, tasks);

        lv.setAdapter(ada);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final TaskModel t = tasks.get(position);


                AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
                alert.setTitle(t.id+" "+t.title);
                alert.setMessage(t.description);

                alert.setButton(AlertDialog.BUTTON_POSITIVE,"Finalizar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface d, int which){

                        DbMaster db = new DbMaster(getContext());
                        db.finishTask(t.id);
                        tasks.remove(position);
                        ada.notifyDataSetChanged();
                        d.dismiss();
                    }
                });
                alert.setButton(AlertDialog.BUTTON_NEGATIVE,"Eliminar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface d, int which){

                        DbMaster db = new DbMaster(getContext());
                        db.delTask(t.id);
                        tasks.remove(position);
                        ada.notifyDataSetChanged();
                        d.dismiss();
                    }
                });

                alert.setButton(AlertDialog.BUTTON_NEUTRAL,"Cancelar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface d, int which){
                        d.dismiss();
                    }
                });


                alert.show();
            }
        });

        return view;
    }
}
