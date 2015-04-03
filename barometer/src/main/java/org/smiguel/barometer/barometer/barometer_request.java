package org.smiguel.barometer.barometer;

import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
// import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.TextView;

import org.smiguel.barometer.BarometerSrv;
import org.smiguel.barometer.R;


public class barometer_request extends Activity {

    private BarometerSrv mService;
    boolean mBound = false;

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            mService = ((BarometerSrv.LocalBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName className) {
            mService = null;
        }
    };

   void doBindService() {
       bindService(new Intent(barometer_request.this,BarometerSrv.class), mConnection, Context.BIND_AUTO_CREATE);
       mBound = true;
   }

    void doUnbindService() {
        if (mBound) {
            // Detach our existing connection.
            unbindService(mConnection);
            mBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_barometer_request);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_barometer_request, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        int iTest=0;

        // The activity is about to become visible.
        doBindService();

        if(mBound) {
            // this.setBaroNumberData(String.valueOf(mService.getRandomNumber()));
            this.setBaroNumberData("TEST");


            mService = new BarometerSrv();
            iTest = mService.getRandomNumber();

        }

        // doUnbindService();


        SensorManager sMgr;
        sMgr = (SensorManager)this.getSystemService(SENSOR_SERVICE);

        List<Sensor> list = sMgr.getSensorList(Sensor.TYPE_ALL);
        StringBuilder data = new StringBuilder();

        for(Sensor sensor: list){
            data.append(sensor.getName() + "\n");
           // data.append(sensor.getVendor() + "\n");
           // data.append(sensor.getVersion() + "\n");
        }
        TextView TextSensorOutput = (TextView)findViewById(R.id.SensorList);
        TextSensorOutput.setText(data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.CloseApp) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void setBaroTextData(String sBaroTextData) {
        EditText TextBaroData = (EditText)findViewById(R.id.TextBarometer);
        TextBaroData.setText(sBaroTextData);
    }

    public void setBaroNumberData(String sBaroNumberData) {
        EditText NumberBaroData = (EditText)findViewById(R.id.NumberBarometer);
        NumberBaroData.setText(sBaroNumberData);
    }


}

