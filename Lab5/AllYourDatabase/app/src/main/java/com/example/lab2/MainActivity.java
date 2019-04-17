package com.example.lab2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DemoDatabaseHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText Place = findViewById(R.id.editTextPlace);
        EditText Task = findViewById(R.id.editTextTask);
        dbhelper = new DemoDatabaseHelper(this);
        View v = this.findViewById(R.id.content);


        ListViewFetch(v , dbhelper);

        /*
        // add new task (Works)
        Task newTask = new Task();
        newTask.Task = "New task";
        newTask.Place = "Aarhus";
        addTask(v,dbhelper,newTask);


        // get all task (Works)
        List<Task> list = getAllTasks(dbhelper);
        Log.d("TASK", "All task len: " + list.size());

        // Get task (Works)
        Task task = list.get(0);
        Log.d("TASK", "onCreate: " +  task.ID + task.Place + task.Task);
        Task TaskDB = GetTask(v, task.ID, dbhelper);
        if (TaskDB != null) {
            Log.d("TASK", "first task: " + TaskDB.ID + TaskDB.Place + TaskDB.Task);
        }


        // delete a task (WORKs)
        DeleteTask(v, task.ID, dbhelper);
        list = getAllTasks(dbhelper);
        Log.d("TASK", "All task len after delete: " + list.size());
        */

    }

    public void Add(View v) {
        EditText taskInfo =  findViewById(R.id.editTextTask);
        EditText placeInfo = findViewById(R.id.editTextPlace);

        Task newtask = new Task();
        newtask.Place = placeInfo.getText().toString();
        newtask.Task = taskInfo.getText().toString();

        addTask(v,dbhelper,newtask);
        ListViewFetch(v,dbhelper);
    }

    private void DeleteTask(View v, int ID , DemoDatabaseHelper dbHelper ) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String Table = DemoReaderContract.DemoEntry.TABLE_NAME;
        //this finds the task we want to delete
        String id = String.valueOf(ID);

        db.delete(Table, "_id=?", new String[]{id});
        ListViewFetch(v,dbhelper);
    }
    private Task GetTask(View v, int ID,  DemoDatabaseHelper dbHelper ) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String Table = DemoReaderContract.DemoEntry.TABLE_NAME;

        // query
        String id = String.valueOf(ID);
        Cursor res = db.rawQuery("SELECT * FROM " +Table+ " WHERE _id = " + id, null);
        // end query

        Task demo = new Task();
        if(res.moveToFirst()){
            if(res != null) {
                demo.Place = res.getString(res.getColumnIndex(DemoReaderContract.DemoEntry.COLUMN_NAME_PLACE));
                demo.Task = res.getString(res.getColumnIndex(DemoReaderContract.DemoEntry.COLUMN_NAME_TASK));
                demo.ID = res.getInt(res.getInt(DemoReaderContract.DemoEntry.COLUMN_ID));
            }
        } else {
            demo = null;
        }


        db.close();
        return demo;
    }

    private void addTask (View v, DemoDatabaseHelper dbHelper , Task task) {
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DemoReaderContract.DemoEntry.COLUMN_NAME_PLACE, task.Place);
        values.put(DemoReaderContract.DemoEntry.COLUMN_NAME_TASK, task.Task);

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DemoReaderContract.DemoEntry.TABLE_NAME, null, values);
    }


    private List<Task> getAllTasks(final DemoDatabaseHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList<Task> arrayList=new ArrayList<>();
        String Table = DemoReaderContract.DemoEntry.TABLE_NAME;
        Cursor res =  db.rawQuery( "select * from "+Table, null );
        res.moveToFirst();

        if (res != null)
        {
            while(res.isAfterLast() == false){
                final Task demo = new Task();
                demo.Place = res.getString(res.getColumnIndex(DemoReaderContract.DemoEntry.COLUMN_NAME_PLACE));
                demo.Task = res.getString(res.getColumnIndex(DemoReaderContract.DemoEntry.COLUMN_NAME_TASK));
                demo.ID = res.getInt(res.getInt(DemoReaderContract.DemoEntry.COLUMN_ID));

                demo.clicker = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteTask(v, demo.ID, dbHelper);
                    }
                };
                arrayList.add(demo);
                res.moveToNext();
            }}
            db.close();
        return arrayList;

    }

    private void ListViewFetch(View v, DemoDatabaseHelper dbhelper) {

        // Construct the data source
        ArrayList<Task> arrayOfUsers = new ArrayList<Task>();
        // Create the adapter to convert the array to views
        TaskAdapter adapter = new TaskAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView list = findViewById(R.id.viewOfLists);
        list.setAdapter(adapter);

        List<Task> taskList = getAllTasks(dbhelper);


        // Add item to adapter
        adapter.addAll(taskList);
    }

}
