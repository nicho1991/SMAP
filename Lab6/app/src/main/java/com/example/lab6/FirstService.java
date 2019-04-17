package com.example.lab6;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

public class FirstService extends Service {
    private doWork doesWork;
    private final class doWork extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            int x = 0;
            while(true) {
                try {
                    if(isCancelled())
                        break;
                    Thread.sleep(2000);
                    x++;
                    publishProgress(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {

            super.onProgressUpdate();

            Intent localIntent = new Intent("FirstServiceLoop" );
            localIntent.putExtra("loop" , "HELLO" + values[0]);
            sendBroadcast(localIntent);
            Toast.makeText(FirstService.this, "service Loop", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "service started", Toast.LENGTH_SHORT).show();
        this.doesWork = new doWork();
        this.doesWork.execute();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.doesWork.cancel(false);
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();

    }
}
