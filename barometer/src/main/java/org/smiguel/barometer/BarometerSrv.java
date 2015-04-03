package org.smiguel.barometer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class BarometerSrv extends Service {
    private final IBinder mBinder = new LocalBinder();
    private final Random mGenerator = new Random();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public BarometerSrv getService() {
            return BarometerSrv.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    public int getRandomNumber() {
        return mGenerator.nextInt();
    }
}
