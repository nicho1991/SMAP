package com.example.uidemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
    }

    public void Cancel(View v) {
        onBackPressed(); // just go back
    }

    public void Ok(View v) {
        Intent intent = new Intent(EditText.this, MainActivity.class);
        Bundle bundle = new Bundle();

        TextView email = (TextView) findViewById(R.id.editTextEmail);
        TextView Phone = (TextView) findViewById(R.id.editTextPhone);
        bundle.putString("Email" , email.getText().toString());
        bundle.putString("Phone", Phone.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
