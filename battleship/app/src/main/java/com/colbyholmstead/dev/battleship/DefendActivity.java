package com.colbyholmstead.dev.battleship;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;


import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DefendActivity extends BaseActivity {
  GridLayout compGrid;
  CellButton[][] buttons = new CellButton[11][11];
  CellButton button;
  String row;
  int col;
  int rowInt;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_defend);
    compGrid = findViewById(R.id.compGrid);
    compGrid.setColumnCount(11);
    compGrid.setRowCount(11);
    InitializeBoard();
  }

  View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
    public void onClick(View v) {
      findLocation(v, "onClick - ButtonOnClickListener");
    }
  };

  public void findLocation(View v, String methodLocation) {
    Button[][] btns = new Button[11][11];
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 11; j++) {
        Button btn = btns[i][j] = new Button(this);
        String idString = String.format("%s%s", i, j);
        if (v.getId() == Integer.parseInt(idString)) {
          col = j;
          switch (i) {
            case 1:
              row = "A";
              break;
            case 2:
              row = "B";
              break;
            case 3:
              row = "C";
              break;
            case 4:
              row = "D";
              break;
            case 5:
              row = "E";
              break;
            case 6:
              row = "F";
              break;
            case 7:
              row = "G";
              break;
            case 8:
              row = "H";
              break;
            case 9:
              row = "I";
              break;
            case 10:
              row = "J";
              break;
          }
          String eastOneIdString = String.format("%s%s", i, j + 1);
          Integer intId = Integer.parseInt(eastOneIdString);
          Button eastOneButton = findViewById(R.id.intId);
//          Log.d("LOGGER", "Method: " + methodLocation + " Location: [" + i + "][" + j + "]");
        }

      }
    }
  }


  public void InitializeBoard() {
    String item;
    String[] valueArray;
    String[][] gridString;
    String[] lineArray;
    String[] location;
    String[] values = new String[100];
    int y;
    int x;
    int count = 0;
    Log.d("LOGGER", "values" + Arrays.toString(values));
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 11; j++) {
        button = buttons[i][j] = new CellButton(this);
        String matchCell = String.format("%s%s", i, j);
        lineArray = status.getDefendBoard().split("\n");
        x = 0;
        for (String line : lineArray) {
          x++;
          location = line.split("");
          y = 0;
          for (String l : location) {
            y++;
            if (i == x && j == y) {
              switch (l) {
                case "S":
                  button.setBackground(getDrawable(R.drawable.taken_cell));
                  button.invalidate();
                  break;
                case "*":
                  button.setBackground(getDrawable(R.drawable.hit_cell));
                  button.invalidate();
                  break;
                case "-":
                  button.setBackground(getDrawable(R.drawable.missed_cell));
                  button.invalidate();
                  break;
                default:
                  button.setBackground(getDrawable(R.drawable.cell));
                  button.invalidate();
                  break;
              }
            }
            count++;
          }
        }
        String idString = String.format("%s%s", i, j);
        button.setId(Integer.parseInt(idString));
        button.setPadding(0, 0, 0, 0);
        // writing grid positions
        if (i == 0) {
          switch (j) {
            case 0:
              // code block
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 1:
              button.setText("1");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 2:
              button.setText("2");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 3:
              button.setText("3");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 4:
              button.setText("4");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 5:
              button.setText("5");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 6:
              button.setText("6");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 7:
              button.setText("7");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 8:
              button.setText("8");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 9:
              button.setText("9");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 10:
              button.setText("10");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            default:

          }
        } else if (j == 0) {
          switch (i) {
            case 0:
              // code block
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 1:
              button.setText("A");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 2:
              button.setText("B");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 3:
              button.setText("C");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 4:
              button.setText("D");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 5:
              button.setText("E");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 6:
              button.setText("F");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 7:
              button.setText("G");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 8:
              button.setText("H");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 9:
              button.setText("I");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            case 10:
              button.setText("J");
              button.setBackground(getDrawable(R.drawable.cell));
              button.invalidate();
              break;
            default:
          }
        } else {
        }
        button.setOnClickListener(buttonOnClickListener);
        button.setTextSize(9);
        androidx.gridlayout.widget.GridLayout.LayoutParams params = new androidx.gridlayout.widget.GridLayout.LayoutParams();
        params.height = 125;
        params.width = 125;
        params.rowSpec = androidx.gridlayout.widget.GridLayout.spec(i);
        params.columnSpec = androidx.gridlayout.widget.GridLayout.spec(j);
        compGrid.addView(button, params);

      }


    }


  }
  public void GetBattleActivity(View view) {
    Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
    Network network = new BasicNetwork(new HurlStack());
    RequestQueue requestQueue = new RequestQueue(cache, network);
    requestQueue.start();
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
    gson = gsonBuilder.create();
    String url = BATTLE_SERVER_URL + "game/" + gameId + "/status/all.json";
    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject response) {
        Log.d("LOGGER", " Json: " + response.toString());
        status = gson.fromJson(String.valueOf(response), Status.class);
        Log.d("LOGGER", "gson WRITE HERE attack: \n" + status.getAttackBoard());
        Intent intent = new Intent(getApplicationContext(), BattleActivity.class);
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


}

