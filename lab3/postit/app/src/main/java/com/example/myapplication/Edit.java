package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Edit extends AppCompatActivity {

    public static String origi;
    public EditText texte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        texte = findViewById(R.id.aaa);

        texte.setText(origi);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        origi = savedInstanceState.getString("text");
        texte.setText(origi);

        Log.d("LOGS", "restore: called");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text",origi);

        Log.d("LOGS", "save: called");
    }


    public void Ok(View v) {
        origi = texte.getText().toString();

        Intent intent = new Intent(Edit.this , MainActivity.class);
        intent.putExtra("text", origi);

        startActivity(intent);
    }

    public void Cancel (View v) {
        Intent intent = new Intent(Edit.this , MainActivity.class);
        intent.putExtra("text", origi);

        startActivity(intent);
    }
}
