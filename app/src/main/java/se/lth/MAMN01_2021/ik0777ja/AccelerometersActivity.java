package se.lth.MAMN01_2021.ik0777ja;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerometersActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private static final float ALPHA = 0.5f; // if ALPHA = 1, no filter applies.
    private float[] accSensorVals = { 0, 0, 0 };

    // The string decimals is used to specify how many decimal points to display.
    private static final String decimals = "%.4f";

/*    public AccelerometersActivity() {
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometers);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    protected float[] lowPass( float[] input, float[] output ) {
        if ( output == null ) return input;
        for ( int i=0; i<input.length; i++ ) {
            output[i] = output[i] + ALPHA * (input[i] - output[i]);
        }
        return output;
    }

    public void onSensorChanged(SensorEvent event) {
        if (event == null) { return; }
        accSensorVals = lowPass(event.values.clone(), accSensorVals);
        TextView textView = findViewById(R.id.textXValue);
        textView.setText(String.format(decimals, accSensorVals[0]));
        textView = findViewById(R.id.textYValue);
        textView.setText(String.format(decimals, accSensorVals[1]));
        textView = findViewById(R.id.textZValue);
        textView.setText(String.format(decimals, accSensorVals[2]));
    }
}