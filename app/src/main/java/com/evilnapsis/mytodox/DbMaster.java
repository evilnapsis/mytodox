package com.evilnapsis.mytodox;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 18/03/2017.
 */

public class DbMaster extends SQLiteOpenHelper {

    public  DbMaster(Context context){
        super(context, "mytodox", null,2 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table task (id integer primary key, title text, description text, is_finished int default 0, created_at timestamp default current_timestamp, is_important int default 0, project_id int, date_at timestamp)");
        db.execSQL("create table project (id integer primary key, title text, description text, created_at timestamp default current_timestamp)");
        ContentValues vs = new ContentValues();
        vs.put("title","Inventio Max");
        db.insert("project",null,vs);
         vs = new ContentValues();
        vs.put("title","Xoolar Max");
        db.insert("project",null,vs);
         vs = new ContentValues();
        vs.put("title","Thunder Max");
        db.insert("project",null,vs);
         vs = new ContentValues();
        vs.put("title","Katana Pro");
        db.insert("project",null,vs);
        vs.put("title","BookMedik Pro");
        db.insert("project",null,vs);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists project");
        db.execSQL("drop table if exists task");
    }

    public void addTask(TaskModel task){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("title",task.title);
        vs.put("description",task.description);
        vs.put("project_id",task.description);
        vs.put("is_important",task.description);
        db.insert("task",null,vs);
        db.close();
    }
    public void addProject(ProjectModel pro){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("title",pro.title);
        vs.put("description",pro.description);
        db.insert("project",null,vs);
        db.close();
    }

    public TaskModel getTask(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c =   db.rawQuery("select * from task where id="+id,null);

        if(c!=null){
            c.moveToFirst();
        }

        TaskModel t = new TaskModel();
        t.id = c.getInt(0);
        t.title = c.getString(1);
        t.description = c.getString(2);
        t.is_finished = c.getInt(3);
        t.created_at = c.getString(4);
        t.is_important= c.getInt(5);
        t.project_id= c.getInt(6);
        db.close();

        return t;
    }

    public ProjectModel getProject(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c =   db.rawQuery("select * from project where id="+id,null);

        if(c!=null){
            c.moveToFirst();
        }

        ProjectModel t = new ProjectModel();
        t.id = c.getInt(0);
        t.title = c.getString(1);
        t.description = c.getString(2);
        t.created_at = c.getString(3);
        db.close();

        return t;
    }


    public void finishTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.rawQuery("update task set is_finished=1 where id="+id,null);

        ContentValues values = new ContentValues();
        values.put("is_finished",1);

        db.update("task", values, "id" + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public void delTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.rawQuery("delete from task where id="+id,null);
        db.delete("task", "id"+ " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    public void delProject(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.rawQuery("delete from task where id="+id,null);
        db.delete("project", "id"+ " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    public List<TaskModel> getAllTasks(){
        SQLiteDatabase db = getReadableDatabase();
        List<TaskModel> tasks = new ArrayList<TaskModel>();
        Cursor c = db.rawQuery("select * from task where is_finished=0",null);
        if(c.moveToFirst()){
            do{
                TaskModel t = new TaskModel();
                t.id = c.getInt(0);
                t.title = c.getString(1);
                t.description = c.getString(2);
                t.is_finished = c.getInt(3);
                t.created_at = c.getString(4);
                t.is_important= c.getInt(5);
                t.project_id= c.getInt(6);

                tasks.add(t);
            }while (c.moveToNext());
        }
        db.close();

        return tasks;
    }

    public List<ProjectModel> getAllProjects(){
        SQLiteDatabase db = getReadableDatabase();
        List<ProjectModel> tasks = new ArrayList<ProjectModel>();
        Cursor c = db.rawQuery("select * from project",null);
        if(c.moveToFirst()){
            do{
                ProjectModel t = new ProjectModel();
                t.id = c.getInt(0);
                t.title = c.getString(1);
                t.description = c.getString(2);
                t.created_at = c.getString(3);
                tasks.add(t);
            }while (c.moveToNext());
        }
        db.close();
        return tasks;
    }


    public List<TaskModel> getFinishedTasks(){
        SQLiteDatabase db = getReadableDatabase();
        List<TaskModel> tasks = new ArrayList<TaskModel>();
        Cursor c = db.rawQuery("select * from task where is_finished=1",null);
        if(c.moveToFirst()){
            do{
                TaskModel t = new TaskModel();
                t.id = c.getInt(0);
                t.title = c.getString(1);
                t.description = c.getString(2);
                t.is_finished = c.getInt(3);
                t.created_at = c.getString(4);
                t.is_important= c.getInt(5);
                t.project_id= c.getInt(6);
                //ProjectModel p = getProject(t.project_id);
                //t.project_name = p.title;
                tasks.add(t);
            }while (c.moveToNext());
        }
        db.close();
        return tasks;
    }
}
