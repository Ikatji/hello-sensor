package se.lth.MAMN01_2021.ik0777ja;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mMagneticField;
    private Sensor mAccelerometer;

    // ALPHA is the lowpass-filter threshold constant. If ALPHA = 1, no filter applies.
    private static final float ALPHA = 1f;

    // define the compass picture that will be used
    private ImageView compassImage;

    // record the angle turned of the compass picture
    private double heading = 0;

    private float[] magSensorVals = { 0, 0, 0 };

    private float[] accSensorVals = { 0, 0, 0 };

    private float[] rotationMatrix = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    private float[] orientationAngles = { 0, 0, 0 };

    TextView headingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        compassImage = (ImageView) findViewById(R.id.compassImage);

        // TextView that will display the heading
        headingText = (TextView) findViewById(R.id.textHeadingValue);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    protected void onResume() {
        super.onResume();
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (mAccelerometer != null) {
            mSensorManager.registerListener(this, mAccelerometer,
                    SensorManager.SENSOR_DELAY_UI);
        }
        mMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mMagneticField != null) {
            mSensorManager.registerListener(this, mMagneticField,
                    SensorManager.SENSOR_DELAY_UI);
        }
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

    protected void updateOrientationAngles() {
        SensorManager.getRotationMatrix(rotationMatrix, null, accSensorVals, magSensorVals);
        float[] orientation = SensorManager.getOrientation(rotationMatrix, orientationAngles);
        double degrees = (Math.toDegrees(orientation[0] + 360.0)) % 360.0;
        heading = Math.round(degrees * 10000) / 10000.0;

        headingText.setText(heading + "°");

        compassImage.setRotation((float) (heading * -1));
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, accSensorVals,
                    0, accSensorVals.length);
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, magSensorVals,
                    0, magSensorVals.length);
        }
        updateOrientationAngles();
    }
}