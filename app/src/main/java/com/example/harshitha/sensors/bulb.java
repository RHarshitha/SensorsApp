package com.example.harshitha.sensors;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class bulb extends Activity implements SensorEventListener{
ImageView image;
TextView resolution,power,Maxrange;
SensorManager sm;
Sensor prox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulb_layout);
        image=(ImageView)findViewById(R.id.imageView);
        resolution=(TextView)findViewById(R.id.textView);
        power=(TextView)findViewById(R.id.textView2);
        Maxrange=(TextView)findViewById(R.id.textView3);

        //Assign a sensor manager
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        prox = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        List<Sensor> defaultSensor= sm.getSensorList(Sensor.TYPE_PROXIMITY);

        sm.registerListener(this, prox,SensorManager.SENSOR_DELAY_NORMAL);

        if(prox!=null&&defaultSensor.size()!=0){
            resolution.setText("Vendor:  "+ prox.getVendor());
            power.setText("Version:  " + prox.getVersion());
            Maxrange.setText("Maximum range:  "+ prox.getMaximumRange()+"cm");
        }

    }


    @Override
    protected void onPause() {
        sm.unregisterListener(this);
        super.onPause();
        Intent back= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(back);
        this.finish();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("in bulb","onSensorChanged");
       // Maxrange.setText(String.valueOf(event.values[0]));

    if(event.values[0]== 3){
        image.setImageResource(R.drawable.bulb_on);
        Log.d("in bulb","the bulb is on");
    }
    else{
        image.setImageResource(R.drawable.bulb_off);
        Log.d("in bulb","the bulb is off");
    }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
