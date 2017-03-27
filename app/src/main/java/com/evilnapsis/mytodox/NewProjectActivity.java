package com.evilnapsis.mytodox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        final EditText ti = (EditText) findViewById(R.id.titlep1);
        final EditText de = (EditText) findViewById(R.id.descriptionp1);
        Button sa =  (Button) findViewById(R.id.saveproject);

        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ti.getText().toString().length()>0 ){
                    int imporant=0;
                    DbMaster db = new DbMaster(v.getContext());
                    ProjectModel tm = new ProjectModel();
                    tm.title  = ti.getText().toString();
                    tm.description =  de.getText().toString();
                    db.addProject(tm);
                    finish();

                }
            }
        });

    }
}
