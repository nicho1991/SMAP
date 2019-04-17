package com.example.uidemos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<ViewOfDemos> {
    public UsersAdapter(Context context, ArrayList<ViewOfDemos> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final ViewOfDemos demos = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_view_of_demos, parent, false);
        }
        // Lookup view for data population
        TextView VDName = (TextView) convertView.findViewById(R.id.VDName);
        final Button VDButton = (Button) convertView.findViewById(R.id.VDButton);
        // TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        VDName.setText(demos.Name);
        VDButton.setText(demos.Name);
        VDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(demos.intent);
            }
        });
        // tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }
}
