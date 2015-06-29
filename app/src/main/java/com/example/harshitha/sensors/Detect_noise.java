package com.example.harshitha.sensors;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;

public class Detect_noise {

    private MediaRecorder mRecorder = null;

    public void start() {

        if (mRecorder == null) {
            //start mRecorder
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null");

            try {
                mRecorder.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mRecorder.start();

        }
    }

    public void stop() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    public float getAmplitude() {
        if (mRecorder != null)
        {
            return Float.valueOf(String.valueOf(20 * Math.log10(mRecorder.getMaxAmplitude() / 27.0)));
        }
        else
            return 0;

    }
}
