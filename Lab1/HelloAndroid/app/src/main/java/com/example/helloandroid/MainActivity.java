package com.example.helloandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Hello(View view) {
        if (getString(R.string.Hello_World) == ((TextView) findViewById(R.id.Hello_Text)).getText()) {
            ((TextView)findViewById(R.id.Hello_Text)).setText(getString(R.string.Hello_Android));
        } else {
            ((TextView)findViewById(R.id.Hello_Text)).setText(getString(R.string.Hello_World));
        }
    }

    public void Exit(View view) {
        System.exit(0);
    }

}

