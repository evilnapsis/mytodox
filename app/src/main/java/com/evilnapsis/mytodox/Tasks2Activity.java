package com.evilnapsis.mytodox;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tasks2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks2);
        DbMaster db = new DbMaster(this);
        final List<TaskModel> tasks = db.getFinishedTasks();
        ListView lv = (ListView) findViewById(R.id.list2);

        final List<String> strs = new ArrayList<String>();

        for(TaskModel t: tasks) {
            strs.add(t.title);
        }

        final ArrayAdapter<String> ada = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);

        lv.setAdapter(ada);


    }
}
