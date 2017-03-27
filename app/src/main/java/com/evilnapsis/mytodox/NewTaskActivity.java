package com.evilnapsis.mytodox;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NewTaskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        final Spinner pr = (Spinner) findViewById(R.id.spinner);

        final EditText ti = (EditText) findViewById(R.id.title1);
        final EditText de = (EditText) findViewById(R.id.description1);
        Button sa =  (Button) findViewById(R.id.savetask);
        final CheckBox ii = (CheckBox) findViewById(R.id.is_important_cb);

        DbMaster db = new DbMaster(this);
        final List<ProjectModel> projects= db.getAllProjects();
        final List<String> strs = new ArrayList<String>();

        for(ProjectModel t: projects) {
            strs.add(t.title);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, strs);
        pr.setAdapter(adapter);




        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ti.getText().toString().length()>0 && pr.getSelectedItem()!=null){
                    int imporant=0;
                    if(ii.isChecked()){imporant=1;}
                    DbMaster db = new DbMaster(v.getContext());
                    TaskModel tm = new TaskModel();
                    tm.title  = ti.getText().toString();
                    tm.description =  de.getText().toString();
                    tm.project_id = projects.get(pr.getSelectedItemPosition()).id;
                    tm.is_important= imporant;
                    db.addTask(tm);
                    finish();
                }
            }
        });
    }
}
