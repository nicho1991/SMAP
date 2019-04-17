package com.example.lab2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {
    Context context;
    public TaskAdapter(Context context, ArrayList<Task> tasks) {

        super(context, 0, tasks);
        context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Task tasks = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_view_of_tasks, parent, false);
        }
        // Lookup view for data population
        TextView VDTask = (TextView) convertView.findViewById(R.id.EditTEXT_TASK);
        TextView VDPlace = (TextView) convertView.findViewById(R.id.EditText_PLACE);
        final Button VDButton = (Button) convertView.findViewById(R.id.deletetaskButton);
        // Populate the data into the template view using the data object
        VDTask.setText(tasks.Task);
        VDPlace.setText(tasks.Place);
        // set click listener
        VDButton.setOnClickListener(tasks.clicker);


        // tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }

}
