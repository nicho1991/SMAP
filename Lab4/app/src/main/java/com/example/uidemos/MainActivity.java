package com.example.uidemos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Bundle extras;
    String Message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (null != intent) {
            this.extras = intent.getExtras();
            if (this.extras != null ) {
                Toast.makeText(this, ToastBuilder(), Toast.LENGTH_SHORT).show();
            }
        }

        ListViewFetch();
    }

    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;
    }

    // menu selection
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Search:
                searching();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void Picker(View v) {
        startActivity(new Intent(MainActivity.this, Picker.class
        ));
    }

    public void Slider(View v) {
        startActivity(new Intent(MainActivity.this, Slider.class
        ));
    }

    public void EditText(View v) {
        startActivity(new Intent(MainActivity.this, EditText.class
        ));
    }

    private String ToastBuilder() {
        String Text = "";
        String Slide = (extras.getInt("Picker") != 0) ? Text += "Slide: " + Integer.toString(extras.getInt("Picker"))+ "\n" : null;
        String Email = (extras.getString("Email") != null) ?   Text += "Email: " +  extras.getString("Email")  + "\n": null;
        String Phone = (extras.getString("Phone") != null) ?  Text += "Phone: " + extras.getString("Phone") + "\n": null;

        String red = (extras.getString("Red") != null) ?  Text += "Red: " + extras.getString("Red") + "\n": null;
        String green = (extras.getString("Green") != null) ?  Text += "Green: " + extras.getString("Green") + "\n": null;
        String blue = (extras.getString("Blue") != null) ?  Text += "Blue: " + extras.getString("Blue") + "\n": null;

        return Text;
    }

    private void searching () {
        final android.widget.EditText input  = new android.widget.EditText(MainActivity.this);
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Update Status")
                .setView(input)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Editable x = input.getText();
                        Toast.makeText(MainActivity.this, "Now searching for: " +x.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Do nothing.
                    }
                }).show();
    }

    private void ListViewFetch() {

        // Construct the data source
        ArrayList<ViewOfDemos> arrayOfUsers = new ArrayList<ViewOfDemos>();
        // Create the adapter to convert the array to views
        UsersAdapter adapter = new UsersAdapter(this, arrayOfUsers);
        // Attach the adapter to a ListView
        ListView list = findViewById(R.id.ViewOfLists);
        list.setAdapter(adapter);


        // Add item to adapter
        adapter.add( new ViewOfDemos("Picker" , new Intent(MainActivity.this, Picker.class)));
        adapter.add( new ViewOfDemos("Slider" , new Intent(MainActivity.this, Slider.class)));
        adapter.add( new ViewOfDemos("Edit Text" , new Intent(MainActivity.this, EditText.class)));
    }
}

