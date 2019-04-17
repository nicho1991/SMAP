package com.example.lab6;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


class MyBroadcastReceiver  extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("HELLO", intent.getStringExtra("loop"));
    }

}

public class MainActivity extends AppCompatActivity {
    private Intent Hello;
    private BindService mService;
    private boolean mBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // service
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.Hello  = new Intent(this, FirstService.class);

        MyBroadcastReceiver x = new MyBroadcastReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("FirstServiceLoop");
        registerReceiver(x,intentFilter);

        Intent IntentForIntentSerice = new Intent(this,MyIntentService.class);

        startService(IntentForIntentSerice);

    }

    public void Foo(View v) {
        MyIntentService.startActionFoo(this,"hello","Foo");
    }

    public void Baz(View v) {
        MyIntentService.startActionBaz(this,"hello","Baz");
    }
    public void GetBindState (View v) {
        if(mBound) {
            int num = mService.getRandomNumber();
            Toast.makeText(this, "number" + num, Toast.LENGTH_SHORT).show();
        }

    }

    public void bindToService(View v) {
        // bind
        try {
            Intent intent = new Intent(this, BindService.class);
            bindService(intent,connection, Context.BIND_AUTO_CREATE);
        } catch (Exception exceotion){

        }
;
    }

    public void UnBindService(View v) {
        try {
            unbindService(connection);
            mBound = false;
        } catch (Exception e) {


        }
    }


    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            BindService.LocalBinder binder = (BindService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    public void StartService(View v) {
        startService(this.Hello);
    }

    public void StopService(View v) {
        stopService(this.Hello);
    }
}
