package com.example.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class MainActivity extends AppCompatActivity {
    private static final String DEBUG_TAG = "NetworkStatusExample";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void CheckConnection(View v) {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
        }

        Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);
        Log.d(DEBUG_TAG, "Mobile connected: " + isMobileConn);
        Toast.makeText(this,"Wifi connected: " + isWifiConn + "Mobile connected: " + isMobileConn, Toast.LENGTH_SHORT).show();
    }

    public void GetAarhusWheater(View v ) {
        /*
        7a2e9e7f11d3039d7d2066fadbf2dde5
        api.openweathermap.org/data/2.5/weather?id=2624652
        */
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.openweathermap.org/data/2.5/weather?id=2624652&APPID=7a2e9e7f11d3039d7d2066fadbf2dde5";
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            TextView text = (TextView) findViewById(R.id.AarhusWheater);
                            JSONObject resp = new JSONObject(response);
                            JSONObject weather = resp.getJSONArray("weather").getJSONObject(0);


                            JSONObject temp = resp.getJSONObject("main");
                            int temperatureCelciuous = (int) (temp.getInt("temp") - 273.15);
                                    text.setText(
                                    "Weather: " +weather.getString("main") + " " +  weather.getString("description") + "\n"
                                    + "Temp: "+ temperatureCelciuous + " degrees celcious");


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Json error", "error: " + e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Volley error" + error);
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
