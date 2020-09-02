package com.colbyholmstead.dev.battleship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {



  EditText edtUsername, edtPassword;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);



    edtUsername = findViewById(R.id.edtUserName);
    edtPassword = findViewById(R.id.edtPassword);


  }






  public void onLoginClick(View view){
    username = edtUsername.getText().toString();
    password = edtPassword.getText().toString();
    if(username.equals("") && password.equals("")){
      username = "colbatronbmw@gmail.com";
      password = "passwords";
    }
   Log.d("LOGGER", "Log In Clicked");

    Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
    Network network = new BasicNetwork(new HurlStack());
    RequestQueue requestQueue = new RequestQueue(cache, network);
    requestQueue.start();
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
    gson = gsonBuilder.create();
    String url = "http://www.battlegameserver.com/api/v1/login.json";
    StringRequest request = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            // do the thing
            Log.d("INTERNET", "response " + response.toString());
            userPreferences = gson.fromJson(response, UserPreferences.class);
            Intent intent = new Intent(getApplicationContext(), Lobby.class);
            startActivity(intent);
          }
        },
        new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
            Log.d("INTERNET", "error " + error.toString());
          }
        }
    ){

      @Override
      public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        String credentials = username + ":" + password;
        String auth = "Basic " + Base64.encodeToString( credentials.getBytes(), Base64.NO_WRAP);
        headers.put("Authorization", auth);
        Log.d("LOGGER", "Credentials: " + credentials);
        return headers;
      }


    };



    requestQueue.add(request);


//
//    new CountDownTimer(300, 10) {
//      Intent intent = new Intent(getApplicationContext(), Lobby.class);
//      public void onTick(long millisUntilFinished) {
//      }
//
//      public void onFinish() {
//        startActivity(intent);
//      }
//
//    }.start();

//Log.d("LOGGER", response);


  }

  public void onRegisterClick(View view){

  }

}
