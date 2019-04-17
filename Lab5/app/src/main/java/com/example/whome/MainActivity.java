package com.example.whome;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText FirstName;
    EditText LastName;
    EditText Phone;
    EditText Age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstName = findViewById(R.id.editTextFirst);
        LastName = findViewById(R.id.editTextLast);
        Phone = findViewById(R.id.editText4);
        Age = findViewById(R.id.EditTextAge);

        SharedPreferences sharedPref = getSharedPreferences("Who me", MODE_PRIVATE);
        FirstName.setText( sharedPref.getString(getString(R.string.FirstName), null));
        LastName.setText( sharedPref.getString(getString(R.string.LastName), null));
        Phone.setText( sharedPref.getString(getString(R.string.PhoneNumber), null));
        Age.setText( sharedPref.getString(getString(R.string.Age), null));
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = getSharedPreferences("Who me", MODE_PRIVATE).edit();
        editor.putString(getString(R.string.FirstName) , FirstName.getText().toString());
        editor.putString(getString(R.string.LastName) , LastName.getText().toString());
        editor.putString(getString(R.string.PhoneNumber) , Phone.getText().toString());
        editor.putString(getString(R.string.Age) , Age.getText().toString());
        editor.apply();
    }
}
