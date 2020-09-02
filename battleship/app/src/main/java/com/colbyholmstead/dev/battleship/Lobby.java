package com.colbyholmstead.dev.battleship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
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
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Lobby extends BaseActivity {

  TextView txtUsername;
  ImageView imgAvatar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lobby);
    txtUsername = findViewById(R.id.txtUsername);
    imgAvatar = findViewById(R.id.imgAvatar);
    txtUsername.setText(userPreferences.getAvatarName());
    Picasso.with(getApplicationContext()).load("http://www.battlegameserver.com/" + userPreferences.getAvatarImage()).into(imgAvatar);
  }


  public void onLogoutClicked(View view) {
//    Log.d("LOGGER", "Log Out Clicked");
    Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
    Network network = new BasicNetwork(new HurlStack());
    RequestQueue requestQueue = new RequestQueue(cache, network);
    requestQueue.start();
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
    gson = gsonBuilder.create();
    String url = "http://www.battlegameserver.com/api/v1/logout.json";
    StringRequest request = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
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
//        Log.d("LOGGER", "Credentials: " + credentials);
        return headers;
      }


    };
    GameId gameId = null;
    userPreferences = null;
//    Log.d("LOGGER", "User Prefs: " + userPreferences.getAvatarName());
    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(intent);

  }

  public void onAllPlayersClicked(View view) {
//    Log.d("LOGGER", "all players clicked ");
    Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
    Network network = new BasicNetwork(new HurlStack());
    RequestQueue requestQueue = new RequestQueue(cache, network);
    requestQueue.start();
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
    gson = gsonBuilder.create();
    String url = "http://www.battlegameserver.com/api/v1/all_users.json";
    StringRequest request = new StringRequest(Request.Method.GET, url,
        new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {
            // do the thing
//            Log.d("LOGGER", "response " + response.toString());
            allUsers = gson.fromJson(response, AllUsers[].class);
            Intent intent = new Intent(getApplicationContext(), AllPlayers.class);
            startActivity(intent);

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
//        Log.d("LOGGER", "Credentials: " + credentials);
        return headers;
      }


    };
    requestQueue.add(request);
  }

  public void onShipsClicked(View view) {
//    Log.d("LOGGER", " Ships clicked ");
    Intent intent = new Intent(getApplicationContext(), Ships.class);
    startActivity(intent);
  }

  public void onDirectionsClicked(View view) {
//    Log.d("LOGGER", "Directions clicked ");
    Intent intent = new Intent(getApplicationContext(), DirectionsActivity.class);
    startActivity(intent);

  }
  public void onStartGameClicked(View view) {
//    Log.d("LOGGER", "Start Game clicked ");
    Intent intent = new Intent(getApplicationContext(), SetupActivity.class);
    startActivity(intent);

  }
  public void onMyGridClicked(View view) {
//    Intent intent = new Intent(getApplicationContext(), GridActivity.class);
//    startActivity(intent);


//    Log.d("LOGGER", "My Grid clicked ");

      Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
      Network network = new BasicNetwork(new HurlStack());
      RequestQueue requestQueue = new RequestQueue(cache, network);
      requestQueue.start();
      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
      gson = gsonBuilder.create();
      String url = BATTLE_SERVER_URL + "challenge_computer.json";
      JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
//          Log.d("LOGGER", "GameId Json: " + response.toString());
          try {
            gameId = response.getInt("game_id");
            Intent intent = new Intent(getApplicationContext(), GridActivity.class);
            startActivity(intent);

          } catch (JSONException e) {
            e.printStackTrace();
          }
//          Log.d("LOGGER", "GameId Int: " + gameId);

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
//          Log.d("LOGGER", "Credentials: " + credentials);
          return headers;
        }


      };
      request.setRetryPolicy(new DefaultRetryPolicy(
          10000,
          DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
          DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
      requestQueue.add(request);

    }







  }









