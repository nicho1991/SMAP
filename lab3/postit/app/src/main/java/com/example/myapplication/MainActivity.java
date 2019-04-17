package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String a = getIntent().getStringExtra("text");

        TextView aa = findViewById(R.id.textView);
        aa.setText(a);
    }

    public void edit(View v) {
        startActivity(new Intent(MainActivity.this , Edit.class));
    }
}
