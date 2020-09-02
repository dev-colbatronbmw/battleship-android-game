package com.colbyholmstead.dev.battleship;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class DirectionsActivity extends BaseActivity {
  Spinner spinner;
  ArrayAdapter directionSpinnerArrayAdapter;
  String[] directionArray;
  static TreeMap<String, Integer> directionMap = new TreeMap<String, Integer>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_directions);
    spinner = findViewById(R.id.spinnerD);
    GetAvailableDirections();
  }

  private void GetAvailableDirections() {
    Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
    Network network = new BasicNetwork(new HurlStack());
    RequestQueue requestQueue = new RequestQueue(cache, network);
    requestQueue.start();
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
    gson = gsonBuilder.create();
    String url = BATTLE_SERVER_URL + "available_directions.json";
    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject response) {
        Log.d("LOGGER", "shps Json: " + response.toString());
        Iterator iterator = response.keys();
        while (iterator.hasNext()) {
          String key = (String) iterator.next();
          try {
            Integer value = (Integer) response.get(key);
            directionMap.put(key, value);
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
        Log.d("LOGGER", "shpmap: " + directionMap);
        int size = directionMap.keySet().size();
        directionArray = new String[size];
        directionArray = directionMap.keySet().toArray(new String[]{});
        directionSpinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, directionArray);
        spinner.setAdapter(directionSpinnerArrayAdapter);
        Log.d("LOGGER", "shpsArray: " + directionArray);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            // your code here
            Log.d("LOGGER", "position: " + position);
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
