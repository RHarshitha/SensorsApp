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
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import org.achartengine.GraphicalView;


import java.util.ArrayList;

public class graph extends Activity implements SensorEventListener,View.OnClickListener {

    private Button butStart, butStop, visual;
    private LinearLayout layout;
    private boolean started = false;
    private SensorManager sensorManager;


    private static GraphicalView view;
    public LineCharts line = new LineCharts();
    private long t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_lay);


        layout = (LinearLayout) findViewById(R.id.graph_holder);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        new ArrayList<AccelData>();

        butStart = (Button) findViewById(R.id.button);
        butStop = (Button) findViewById(R.id.button2);
        visual= (Button)findViewById(R.id.visual);
        if(butStart!=null){
            butStart.setOnClickListener(this);
            butStart.setEnabled(true);

        }
        if(butStop!=null){
            butStop.setOnClickListener(this);
            butStop.setEnabled(false);

        }

        visual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accV = new Intent(getApplicationContext(), visual.class);
                startActivity(accV);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (started) {

            double x = event.values[0];
            double y = event.values[1];
            double z = event.values[2];
            long time = System.currentTimeMillis();
            AccelData data = new AccelData(time, x, y, z);

            line.addNewData(data, t);
            view.repaint();




        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:

                view = line.getView(this);
                view.repaint();
                //setContentView(view);
                layout.addView(view);
                butStop.setEnabled(true);
                butStart.setEnabled(false);
                new ArrayList<AccelData>();
                started = true;
                t = System.currentTimeMillis();
                Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_NORMAL);



                break;
            case R.id.button2:
                butStop.setEnabled(false);
                butStart.setEnabled(true);
                started = false;
                sensorManager.unregisterListener(this);
                layout.removeAllViews();
                Intent refresh = new Intent(getApplicationContext(),graph.class);
                startActivity(refresh);
                finish();

                break;

            default:
                break;

        }




    }
}
