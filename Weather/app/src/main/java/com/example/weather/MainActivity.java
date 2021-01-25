package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import static android.app.Notification.EXTRA_TEXT;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT="com.example.weather.example.EXTRA_TEXT";
    public static final String EXTRA_TEXT1="com.example.weather.example.EXTRA_TEXT1";
    public static final String EXTRA_NUMBER="com.example.weather.example.EXTRA_NUMBER";
    public static final String EXTRA_NUMBER1="com.example.weather.example.EXTRA_NUMBER1";



    EditText cn;
    Button btn;
    String temper;
  public static   String city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        cn = (EditText) findViewById(R.id.et);
        btn =(Button) findViewById(R.id.bt);
        btn.setBackgroundColor(Color.BLACK);

    }

         public void getWeather (View view){


             city = cn.getText().toString();

              final String API_KEY = BuildConfig.ApiKey;
              final String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=b39de1a4246f749bacef1271268b429a";



        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array= response.getJSONArray("weather");
                     JSONObject object =array.getJSONObject(0);

                    temper = main_object.getString("temp");
                    Double temp = Double.parseDouble(temper) - 273.15;
                    String WeatherType= object.getString("main");
                    String humid = main_object.getString("humidity");
                    String cityn = response.getString("name");

              // Send Data from One activity to another
                    Intent i=new Intent(MainActivity.this,showTemperature.class);

                    i.putExtra(EXTRA_TEXT,WeatherType);
                    i.putExtra(EXTRA_NUMBER,temp.toString().substring(0,4));
                    i.putExtra(EXTRA_NUMBER1,humid.toString());
                    i.putExtra(EXTRA_TEXT1,cityn);
                    startActivity(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                    {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { NetworkResponse response = error.networkResponse;
                if (response != null && response.statusCode == 404) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                        //use this json as you want
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }


               // Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG);
            }
        });
        queue.add(request);
    }


}