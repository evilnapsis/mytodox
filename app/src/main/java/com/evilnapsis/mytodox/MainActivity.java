package com.evilnapsis.mytodox;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        final TabLayout tablay = (TabLayout)findViewById(R.id.tab_layout);
        tablay.addTab(tablay.newTab().setText("Pendientes"));
        tablay.addTab(tablay.newTab().setText("Finalizadas"));
        tablay.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        final MyPageAdapter pa = new MyPageAdapter(getSupportFragmentManager(), tablay.getTabCount());
        pager.setAdapter(pa);

        pager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener(tablay){

        });

        tablay.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                final MyPageAdapter pa = new MyPageAdapter(getSupportFragmentManager(), tablay.getTabCount());
                pager.setAdapter(pa);
                pager.setCurrentItem(tab.getPosition());
                pa.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                final MyPageAdapter pa = new MyPageAdapter(getSupportFragmentManager(), tablay.getTabCount());
                pager.setAdapter(pa);
                pager.setCurrentItem(tab.getPosition());
                pa.notifyDataSetChanged();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.credits_menu) {
            Intent i = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(i);
        }
        else if (id == R.id.new_task_im) {
            Intent i = new Intent(MainActivity.this, NewTaskActivity.class);
            startActivity(i);
        }
        else if (id == R.id.new_project) {
            Intent i = new Intent(MainActivity.this, NewProjectActivity.class);
            startActivity(i);
        }
        else if (id == R.id.projects) {
            Intent i = new Intent(MainActivity.this, ProjectsActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

}
