package com.example.harshitha.sensors;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends Activity {
    ImageButton acc, prox,sou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        acc = (ImageButton)findViewById(R.id.imageButton);
       prox = (ImageButton)findViewById(R.id.imageButton2);
        sou = (ImageButton)findViewById(R.id.imageButton3);


        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent acc = new Intent(getApplicationContext(),graph.class);
                startActivity(acc);
            }
        });


        prox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prox = new Intent(getApplicationContext(),bulb.class);
                startActivity(prox);
                finish();
            }
        });


        sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sou = new Intent(getApplicationContext(),NoiseAlert.class);
                startActivity(sou);
                finish();
            }
        });
    }

}
