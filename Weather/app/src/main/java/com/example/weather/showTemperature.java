package com.example.weather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class showTemperature extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_temperature);

        ImageView image =(ImageView)findViewById(R.id.img);
        TextView cn1=(TextView)findViewById(R.id.tv1);
        TextView temper=(TextView)findViewById(R.id.tv2);
        TextView desc=(TextView)findViewById(R.id.tv3);
        TextView hum=(TextView)findViewById(R.id.tv4);
        TextView date=(TextView)findViewById(R.id.dt);

        // Receive data from main activity
        Intent intent=getIntent();
        String temp =intent.getStringExtra(MainActivity.EXTRA_NUMBER);
        String humid =intent.getStringExtra(MainActivity.EXTRA_NUMBER1);
        String  WeatherType=intent.getStringExtra(MainActivity.EXTRA_TEXT);
        String cityn =intent.getStringExtra(MainActivity.EXTRA_TEXT1);

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy  hh:mm");
        String dateTime=sdf.format(calendar.getTime());

        //Display data on screen

            image.setImageResource(R.drawable.img1);
             temper.setText("Temperature  "+temp+ " °C");
            hum.setText("Humidity  "+humid);
            desc.setText("Weather  "+WeatherType);
           cn1.setText(cityn);
           date.setText("Updated "+dateTime);



    }


    }
