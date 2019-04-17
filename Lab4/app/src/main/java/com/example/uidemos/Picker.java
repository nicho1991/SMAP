package com.example.uidemos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class Picker extends AppCompatActivity {
    private SeekBar slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
        slider = (SeekBar) findViewById(R.id.seekBar);
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

                TextView text = (TextView) findViewById(R.id.numberView);
                text.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
    }



    public void Cancel(View v) {
        onBackPressed(); // just go back
    }

    public void Ok(View v) {
        Intent intent = new Intent(Picker.this, MainActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt("Picker", slider.getProgress());
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
