package org.smiguel.barometer.barometer;

import java.util.List;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.TextView;

import org.smiguel.barometer.R;


public class barometer_request extends ActionBarActivity {

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
        // The activity is about to become visible.

        EditText TextBaroData = (EditText)findViewById(R.id.TextBarometer);
        EditText NumberBaroData = (EditText)findViewById(R.id.NumberBarometer);
        TextBaroData.setText("Toller Text");
        NumberBaroData.setText("4242");

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
}
