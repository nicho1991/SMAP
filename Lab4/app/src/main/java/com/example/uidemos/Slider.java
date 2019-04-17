package com.example.uidemos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class Slider extends AppCompatActivity {
    int red;
    int green;
    int blue;

    private SeekBar RedSlider;
    private SeekBar GreenSlider;
    private SeekBar BlueSlider;

    SeekBar.OnSeekBarChangeListener x = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
        boolean fromUser) {

            red = RedSlider.getProgress();
            green = GreenSlider.getProgress();
            blue = BlueSlider.getProgress();

            findViewById(R.id.RootLayout).setBackgroundColor(Color.rgb(red,green,blue));

        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);


        RedSlider = (SeekBar) findViewById(R.id.RedSlider);
        GreenSlider = (SeekBar) findViewById(R.id.GreenSlider);
        BlueSlider = (SeekBar) findViewById(R.id.BlueSlider);

        RedSlider.setOnSeekBarChangeListener(this.x);
        GreenSlider.setOnSeekBarChangeListener(this.x);
        BlueSlider.setOnSeekBarChangeListener(this.x);

    }

    public void Cancel(View v) {
        onBackPressed(); // just go back
    }

    public void Ok(View v) {

        Intent intent = new Intent(Slider.this, MainActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("Red", Integer.toString(RedSlider.getProgress()));
        bundle.putString("Green", Integer.toString(GreenSlider.getProgress()));
        bundle.putString("Blue", Integer.toString(BlueSlider.getProgress()));
        intent.putExtras(bundle);
        startActivity(intent);

    }

}
