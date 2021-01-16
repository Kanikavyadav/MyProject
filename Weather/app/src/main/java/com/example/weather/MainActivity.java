package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText cn;
    //TextView tem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cn = (EditText) findViewById(R.id.et);
        //tem = (TextView) findViewById(R.id.tv1);
    }


    public void getWeather (View view){
        String apikey = "b39de1a4246f749bacef1271268b429a";
        String city = cn.getText().toString();
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=b39de1a4246f749bacef1271268b429a";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("main");
                    String temper = object.getString("temp");
                    Double temp = Double.parseDouble(temper) - 273.15;
                    Intent i=new Intent(MainActivity.this,showTemperature.class);
                    i.putExtra("temperature",temp.toString().substring(0,4));
                    startActivity(i);
                   // tem.setText(temp.toString().substring(0, 5));
                    //tem.setText(temperature);
                } catch (JSONException e) {
                    e.printStackTrace();
                    {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG);
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG);
            }
        });
        queue.add(request);
    }


}