package com.colbyholmstead.dev.battleship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Ships extends BaseActivity {
  Spinner spinner;
  ArrayAdapter shipSpinnerArrayAdapter;
  String[] shipsArray;
  static TreeMap<String, Integer> shipMap = new TreeMap<String, Integer>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ships);
    spinner = findViewById(R.id.spinner);
    GetAvailableShips();

  }


  private void GetAvailableShips() {
    Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
    Network network = new BasicNetwork(new HurlStack());
    RequestQueue requestQueue = new RequestQueue(cache, network);
    requestQueue.start();
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
    gson = gsonBuilder.create();
    String url = BATTLE_SERVER_URL + "available_ships.json";
    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject response) {
        Log.d("LOGGER", "shps Json: " + response.toString());
        Iterator iterator = response.keys();
        while (iterator.hasNext()) {
          String key = (String) iterator.next();
          try {
            Integer value = (Integer) response.get(key);
            shipMap.put(key, value);
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
        Log.d("LOGGER", "shpmap: " + shipMap);
        int size = shipMap.keySet().size();
        shipsArray = new String[size];
        shipsArray = shipMap.keySet().toArray(new String[]{});
        shipSpinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, shipsArray);
        spinner.setAdapter(shipSpinnerArrayAdapter);
        Log.d("LOGGER", "shpsArray: " + shipsArray);

        for(String ship : shipsArray){
          Log.d("LOGGER", "ship: " + ship);
        }


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            // your code here
            Log.d("LOGGER", "position: " + position);
            Log.d("LOGGER", "ship: " + shipsArray[position]);
            Log.d("LOGGER", "length: " + shipsArray[position]);
          }

          @Override
          public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
          }

        });
      }
    },
        new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            Log.d("INTERNET", "error " + error.toString());
          }
        }
    ) {

      @Override
      public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        String credentials = username + ":" + password;
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        headers.put("Authorization", auth);
        Log.d("LOGGER", "Credentials: " + credentials);
        return headers;
      }


    };
    requestQueue.add(request);

  }

}
