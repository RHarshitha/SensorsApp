package com.example.harshitha.sensors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;


public class Drawvisual extends SurfaceView {
    Bitmap broom;

    public Drawvisual(Context context) {
        super(context);

        broom = BitmapFactory.decodeResource(getResources(), R.drawable.broom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("in draw visual","in onDraw fn");


    }
}
