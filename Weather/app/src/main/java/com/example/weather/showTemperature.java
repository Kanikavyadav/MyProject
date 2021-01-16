package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class showTemperature extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_temperature);

        TextView get=(TextView)findViewById(R.id.tv1);

        Bundle bundle=getIntent().getExtras();
        String Temper= bundle.getString("temperature");


        get.setText("Temperature is: "+Temper);
    }
}