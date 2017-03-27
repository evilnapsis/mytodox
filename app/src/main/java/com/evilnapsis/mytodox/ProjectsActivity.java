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

public class ProjectsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        final DbMaster db = new DbMaster(this);
        final List<ProjectModel> tasks = db.getAllProjects();
        ListView lv = (ListView) findViewById(R.id.projects_list);

        final List<String> strs = new ArrayList<String>();

        for(ProjectModel t: tasks) {
            strs.add(t.title);
        }

//        final ArrayAdapter<String> ada = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);
//final CustomAdapter ada = new CustomAdapter(this, tasks);
        final ArrayAdapter<String> ada= new ArrayAdapter(this, android.R.layout.simple_list_item_1, strs);

        lv.setAdapter(ada);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final ProjectModel t = tasks.get(position);


                AlertDialog alert = new AlertDialog.Builder(ProjectsActivity.this).create();
                alert.setTitle(t.id+" "+t.title);
                alert.setMessage(t.description);

                alert.setButton(AlertDialog.BUTTON_NEGATIVE,"Eliminar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface d, int which){

//                        DbMaster db = new DbMaster(getContext());
                        db.delProject(t.id);
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

    }
}
