package com.example.harshitha.sensors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class main_visual extends Activity {
    Drawvisual ourvisual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourvisual=new Drawvisual(this);
        setContentView(ourvisual);
        Intent ourIntent = new Intent(this,visual.class);
        startActivity(ourIntent);
    }
}
