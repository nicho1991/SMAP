package com.example.lab6;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class BindService extends Service {
    // Binder given to clients
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        BindService getService() {
            // Return this instance of LocalService so clients can call public methods
            return BindService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }

    public int getRandomNumber() {
        return 42;
    }
}
