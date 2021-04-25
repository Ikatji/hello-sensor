package se.lth.MAMN01_2021.ik0777ja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "se.lth.MAMN01_2021.ik0777ja.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

/*    *//** Called when the user taps the Send button *//*
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }*/

    /** Called when the user taps the Accelerometers button */
    public void launchAccelerometersActivity(View view) {
        Intent intent = new Intent(this, AccelerometersActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Compass button */
    public void launchCompassActivity(View view) {
        Intent intent = new Intent(this, CompassActivity.class);
        startActivity(intent);
    }
}