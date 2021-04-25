# hello-sensor
The development process can be split up into four phases:
1. Getting used to using Android Studio and setting up the project's skeleton structure.
2. Programming the accelerometers test.
3. Programming the compass test.
4. Testing.

## 1. Learning the ropes
I started out doing the Android Developer *Build Your First App*-tutorial at <https://developer.android.com/training/basics/firstapp/index.html>. I then proceeded to create the Activities for the different tests, the main UI, and navigation between views by modifying code from and utilising the procedures in the tutorial.

## 2. Programming the Accelerometers Test
For the accelerometer-test activity I drew on example code from Android Developer `SensorManager` docs (<https://developer.android.com/reference/android/hardware/SensorManager.html>) and *Build Your First App*, with a lowpass function taken from <https://www.built.io/blog/applying-low-pass-filter-to-android-sensor-s-readings>.

The `SensorManager` docs provided implementation of Activity lifecycle callbacks that unregister the listener to save resources when the app is inactive. I could not use all of its the code unmodified however, but had to place some code it suggests should be placed in the Activity's constructor in its lifecycle method `onCreate()` instead, since implementing a constructor caused the app to crash when attempting to open the accelerometers-test activity.

## 3. Programming the Compass Test
For the compass-test I thought it would be interesting to use the sensor-fusion method described in *Sensors Tutorial for Android: Getting Started* (<https://www.raywenderlich.com/10838302-sensors-tutorial-for-android-getting-started>). I followed those instructions loosely, translating the kotlin example code into equivalent java code, with a little help from *Simple compass code with Android Studio* (https://www.codespeedy.com/simple-compass-code-with-android-studio/), Android Developer Position Sensor docs (<https://developer.android.com/guide/topics/sensors/sensors_position#sensors-pos-orient>), and my earlier accelerometer code.

The biggest difference between the Sensors Tutorial implementation and mine is that I record the sensor values and use them to update the UI in the same class, meaning I could skip a few steps associated with passing data between classes. Their version, creating a separate service for fetching sensor data, would be preferable in theory though.

## 4. Testing
Testing the compass outside as well as inside, comparing the heading to that of a real compass, it seemed pretty clear that the direction of the needle was not closely correlated to the magnetic field. The needle did not seem like it corrected its heading when the phone was rotated at all, or at best extremely sluggishly. Using a compass app from the play store worked well, which indicated that the problem lay in my implementation, and not in faulty sensors.

Given more time I'd like to troubleshoot some more, but it is what it is.
