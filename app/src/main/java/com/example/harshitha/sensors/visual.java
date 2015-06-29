package com.example.harshitha.sensors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class visual extends Activity implements SensorEventListener{
    Drawvisual ourvisual;
    Bitmap broom;
    SensorManager sm;
    float x, y, sensorX, sensorY;

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX= event.values[0];
        sensorY= event.values[1];
        Log.d("in onSensorChanged",String.valueOf(sensorX)+" , "+String.valueOf(sensorY));
    }

    public class Drawvisual extends SurfaceView implements Runnable{
        //surface holder manages surface
        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = true;

        public Drawvisual(Context context) {
            super(context);
            ourHolder = getHolder();
        }

        public void pause(){
            isRunning = false;
            while(true){
                try{
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread=null;
        }

        public void resume(){
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public void run() {
            while(isRunning){
               //see if surface is valid
                if(!ourHolder.getSurface().isValid())
                    continue;
                Canvas canvas = ourHolder.lockCanvas();
                if(canvas!=null)
                {
                    canvas.drawColor(Color.WHITE);
                    canvas.drawBitmap(broom,sensorX*30,sensorY*30,null);
                    ourHolder.unlockCanvasAndPost(canvas);
                }
              //  ourHolder.unlockCanvasAndPost(canvas);
            }

        }
    }

  /*  private void setVisual() {
        ourvisual = new Drawvisual(this);
        ourvisual.set(sensorX,sensorY);
        Log.d("in visual activity",String.valueOf(sensorX)+ " , " +String.valueOf(sensorY));
        setContentView(ourvisual);
    }
*/
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        broom = BitmapFactory.decodeResource(getResources(),R.drawable.broom);
        x=y=sensorX=sensorY=0;
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor acc = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, acc,SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("in visual activity",String.valueOf(sensorX)+" , "+String.valueOf(sensorY));
        ourvisual = new Drawvisual(this);
        ourvisual.resume();
        setContentView(ourvisual);
    }



    @Override
    protected void onPause(){
        sm.unregisterListener(this);
        super.onPause();
        Intent back = new Intent(getApplicationContext(),graph.class);
        startActivity(back);
        finish();
    }
}
