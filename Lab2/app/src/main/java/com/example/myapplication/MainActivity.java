package com.example.myapplication;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int x = 0;
    private TextView text;


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        x = savedInstanceState.getInt("clicks");
        text.setText(String.valueOf(x));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("clicks",x);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("LOGS", "onCreate: called");
        text = (TextView) findViewById(R.id.text);

        // find button
        View btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // find text
                TextView text = (TextView) findViewById(R.id.text);
                CharSequence count = (text).getText();
                x = Integer.parseInt(String.valueOf(count));
                x = x+ 1;
                text.setText(String.valueOf(x));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LOGS", "onStart: called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LOGS", "onResume: called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LOGS", "onPause: called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LOGS", "onStop: called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LOGS", "onDestroy: called");
    }


}
