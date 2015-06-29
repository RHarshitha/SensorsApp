package com.example.harshitha.sensors;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;


public class NoiseAlert extends Activity{
    private static final int POLL_INTERVAL = 300;
    private boolean mRunning = false; // running state
    private Handler mHandler = new Handler();
    private TextView Status,decibels,info;
    private Detect_noise mSensor;
    ProgressBar bar;



    // Create runnable thread to Monitor Voice
    private Runnable mPollTask = new Runnable() {
        public void run() {
            mSensor.start();
            float amp = mSensor.getAmplitude();
            Log.d("AMP",String.valueOf(amp));
            Log.d("Noise", "runnable mPollTask");
            updateDisplay("Listening...", amp);
            //Log.d("Noise", "==== onCreate ===");

            // Runnable(mPollTask) will again execute after POLL_INTERVAL
            mHandler.postDelayed(mPollTask, POLL_INTERVAL);
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noise);
        info=(TextView) findViewById(R.id.textView6);
        Status = (TextView) findViewById(R.id.textView4);
        decibels=(TextView)findViewById(R.id.textView5);
        bar=(ProgressBar)findViewById(R.id.progressBar);

        // Used to record voice
        mSensor = new Detect_noise();
    }

    @Override
    public void onResume() {
        super.onResume();

        //Log.d("Noise", "==== onResume ===");
        if (!mRunning) {
            mRunning = true;
            start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent back= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(back);
        this.finish();
    }

    @Override
    public void onStop() {
        super.onStop();
        // Log.d("Noise", "==== onStop ===");
        stop();
    }


    private void start() {
        //Log.d("Noise", "==== start ===");
        mSensor.start();

        // Runnable(mPollTask) will execute after POLL_INTERVAL
        mHandler.postDelayed(mPollTask, POLL_INTERVAL);
    }


    private void stop() {
        Log.d("Noise", "==== Stop Noise Monitoring===");
        mHandler.removeCallbacks(mPollTask);
        mSensor.stop();
        bar.setProgress(0);
        updateDisplay("stopped...",0);
        mRunning = false;

    }

    private void updateDisplay(String status, float amp) {
        String formattedString;
        Status.setText(status);
        if(amp<0){
            bar.setProgress((int)-amp);
            info.setText("bar max = -Infinity");

        }else {
            bar.setProgress((int)amp);
            info.setText("");
        }
        Log.d("SOUND", String.valueOf(amp));
        formattedString = String.format("%.02f", amp);
        if(formattedString.equals("-Infinity")){
            decibels.setText("<-30dB");
        }
        else {
            decibels.setText(formattedString + " dB");
        }
    }
}

