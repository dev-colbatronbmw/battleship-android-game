package com.colbyholmstead.dev.battleship;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.gridlayout.widget.GridLayout;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.graphics.PorterDuff;
//import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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


public class GridActivity extends BaseActivity implements View.OnDragListener, View.OnLongClickListener {
  Spinner spinner;

  ArrayAdapter directionSpinnerArrayAdapter;
  String[] directionArray;
  static TreeMap<String, Integer> directionMap = new TreeMap<String, Integer>();
  String[] shipsArray;
  String currentShip;
  Integer shipLength;
  Integer[] activeCells;
  boolean drop;
  static TreeMap<String, Integer> shipMap = new TreeMap<String, Integer>();
  CellButton[][] buttons = new CellButton[11][11];
  int bId;
  String row;
  int col;
  boolean shipped;
  Button button;
  GridLayout grid;
  public int height;
  public int width;
  Integer direction;
  public ImageView bttlship, crrir, crzr, sub, dest;
//   static String[] numbers = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
//  static String[] letters = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_grid);
    spinner = findViewById(R.id.spinnerD2);
    GetGameStatus();
    GetAvailableDirections();
    GetAvailableShips();
    bttlship = findViewById(R.id.battleship);
    bttlship.setOnLongClickListener(this);
    bttlship.setTag("bttlShip");
    crrir = findViewById(R.id.carrier);
    crrir.setOnLongClickListener(this);
    crrir.setTag("crrir");
    crzr = findViewById(R.id.cruiser);
    crzr.setOnLongClickListener(this);
    crzr.setTag("crzr");
    sub = findViewById(R.id.submarine);
    sub.setOnLongClickListener(this);
    sub.setTag("sub");
    dest = findViewById(R.id.destroyer);
    dest.setOnLongClickListener(this);
    dest.setTag("dest");
//    buttons = new Button[11][11];
    grid = findViewById(R.id.homeGrid);
    grid.setColumnCount(11);
    grid.setRowCount(11);
//    Log.d("LOGGER", "height?" + grid.getHeight());
//    Log.d("LOGGER", "width?" + grid.getWidth());
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 11; j++) {
        button = buttons[i][j] = new CellButton(this);
        button.setBackground(getDrawable(R.drawable.cell));
        String idString = String.format("%s%s", i, j);
//        Log.d("LOGGER", "id String " + idString);
        button.setId(Integer.parseInt(idString));
//        Log.d("LOGGER", "button id" + button.getId());
        button.setPadding(0, 0, 0, 0);
        if (i == 0) {
          switch (j) {
            case 0:
              // code block
              break;
            case 1:
              button.setText("1");
              break;
            case 2:
              button.setText("2");
              break;
            case 3:
              button.setText("3");
              break;
            case 4:
              button.setText("4");
              break;
            case 5:
              button.setText("5");
              break;
            case 6:
              button.setText("6");
              break;
            case 7:
              button.setText("7");
              break;
            case 8:
              button.setText("8");
              break;
            case 9:
              button.setText("9");
              break;
            case 10:
              button.setText("10");
              break;
            default:

          }
        } else if (j == 0) {
          switch (i) {
            case 0:
              // code block
              break;
            case 1:
              button.setText("A");
              break;
            case 2:
              button.setText("B");
              break;
            case 3:
              button.setText("C");
              break;
            case 4:
              button.setText("D");
              break;
            case 5:
              button.setText("E");
              break;
            case 6:
              button.setText("F");
              break;
            case 7:
              button.setText("G");
              break;
            case 8:
              button.setText("H");
              break;
            case 9:
              button.setText("I");
              break;
            case 10:
              button.setText("J");
              break;
            default:
          }
        } else {
          // todo if
          button.setOnDragListener(this);

        }

        button.setTextSize(9);
        final GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        height = grid.getHeight();
        width = grid.getWidth();
        params.height = 125;
        params.width = 125;
        params.rowSpec = GridLayout.spec(i);
        params.columnSpec = GridLayout.spec(j);
        grid.addView(button, params);
      }


    }
    GetAvailableDirections();
  }

  View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
    public void onClick(View v) {
      findLocation(v, "onClick - ButtonOnClickListener", shipLength);
    }
  };

  public void findLocation(View v, String methodLocation, Integer shipLength) {
    Button[][] btns = new Button[11][11];
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 11; j++) {
        Button btn = btns[i][j] = new Button(GridActivity.this);
        String idString = String.format("%s%s", i, j);

        if (v.getId() == Integer.parseInt(idString)) {
          if(drop){
            col = j;
            switch (i){
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
          }
          String eastOneIdString = String.format("%s%s", i, j + 1);
          Integer intId = Integer.parseInt(eastOneIdString);
          Button eastOneButton = findViewById(R.id.intId);
//          Log.d("LOGGER", "Method: " + methodLocation + " Location: [" + i + "][" + j + "]");
          activeCells = new Integer[shipLength];
          for (int k = 1; k < shipLength; k++) {
            if (direction == 2) {
              // cell [i][j + k]
              String cellId = String.format("%s%s", i, j + 1);
              activeCells[k] = Integer.parseInt(cellId);
//              Log.d("LOGGER", " activeCells location: " + activeCells[k]);

            } else if (direction == 6) {
              String cellId = String.format("%s%s", i, j - 1);
              activeCells[k] = Integer.parseInt(cellId);
            }


          }

        }

      }
    }
  }



  @Override
  public boolean onDrag(View v, DragEvent event) {
    // Defines a variable to store the action type for the incoming event
    int action = event.getAction();
    // Handles each of the expected events
    switch (action) {
      case DragEvent.ACTION_DRAG_STARTED:
        drop = false;
        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
          return true;
        }
        return false;
      case DragEvent.ACTION_DRAG_ENTERED:
        drop = false;
//        v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        // need to find all the cells that all these need to apply too.
        findLocation(v, "Action_Drag_Entered", shipLength);
        if (direction == 2) {
          east(v, shipLength, true, false, event);
        } else if (direction == 6) {
          west(v, shipLength, true, false, event);
        } else if (direction == 0) {
          north(v, shipLength, true, false, event);
        } else if (direction == 4) {
          south(v, shipLength, true, false, event);
        }
        v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        v.invalidate();
        return true;
      case DragEvent.ACTION_DRAG_LOCATION:
        drop = false;
        return true;
      case DragEvent.ACTION_DRAG_EXITED:
        drop = false;
        findLocation(v, "Action_Drag_Entered", shipLength);
        if (direction == 2) {
          east(v, shipLength, false, true, event);
        } else if (direction == 6) {
          west(v, shipLength, false, true, event);
        } else if (direction == 0) {
          north(v, shipLength, false, true, event);
        } else if (direction == 4) {
          south(v, shipLength, false, true, event);
        }
        v.getBackground().clearColorFilter();
        v.invalidate();
        return true;
      case DragEvent.ACTION_DROP:
        drop = true;// if response success
        findLocation(v, "Action_Drag_Entered", shipLength);
       shipped = addShip();
  ClipData.Item item = event.getClipData().getItemAt(0);
  String dragData = item.getText().toString();
//  Toast.makeText(this, "Dragged data is " + dragData, Toast.LENGTH_SHORT).show();

  if (direction == 2) {
    east(v, shipLength, false, false, event);
  } else if (direction == 6) {
    west(v, shipLength, false, false, event);
  } else if (direction == 0) {
    north(v, shipLength, false, false, event);
  } else if (direction == 4) {
    south(v, shipLength, false, false, event);
  }
//        v.getBackground().clearColorFilter();
  v.setBackground(getDrawable(R.drawable.taken_cell));
  // Invalidates the view to force a redraw
  v.invalidate();
  View vw = (View) event.getLocalState();
  ViewGroup owner = (ViewGroup) vw.getParent();
  owner.removeView(vw);
  drop = false;

        return true;
      case DragEvent.ACTION_DRAG_ENDED:
        drop = false;
        v.getBackground().clearColorFilter();
        v.invalidate();
        if (event.getResult())
          Log.d("LOGGER", "The drop was handled.");
//          Toast.makeText(this, "The drop was handled.", Toast.LENGTH_SHORT).show();
        else
          Log.d("LOGGER", "The drop didn't work.");
//          Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show();
        return true;
      default:
        Log.d("LOGGER", "The drop didn't work.");
//        Log.e("DragDrop Example", "Unknown action type received by OnDragListener.");
        break;
    }
    return false;
  }


  @Override
  public boolean onLongClick(View v) {
    Log.d("LOGGER", "Ship name from tag " + v.getTag());
    Object tag = v.getTag();
    if ("sub".equals(tag)) {
      currentShip = "submarine";
      shipLength = 3;
    } else if ("bttlShip".equals(tag)) {
      shipLength = 4;
      currentShip = "battleship";
    } else if ("crrir".equals(tag)) {
      currentShip = "carrier";
      shipLength = 5;
    } else if ("crzr".equals(tag)) {
      currentShip = "cruiser";
      shipLength = 3;
    } else if ("dest".equals(tag)) {
      currentShip = "destroyer";
      shipLength = 2;
    }
    Log.d("LOGGER", "currentShip name " + currentShip);
    ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
    String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
    ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
    View.DragShadowBuilder dragshadow = new View.DragShadowBuilder(v);
    v.startDrag(data        // data to be dragged
        , dragshadow   // drag shadow builder
        , v           // local data about the drag and drop operation
        , 0          // flags (not currently used, set to 0)
    );
    return true;
  }


  public void east(View v, Integer ship, Boolean dragging, Boolean exited, DragEvent event) {
    Button[][] btns = new Button[11][11];
//    Log.d("LOGGER", " east");
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 11; j++) {
        Button btn = btns[i][j] = new Button(GridActivity.this);
        String idString = String.format("%s%s", i, j);
        if (v.getId() == Integer.parseInt(idString)) {
          //todo check locations to the dir of the ship {"north": 0, "east": 2, "south": 4, "west": 6}
//          Log.d("LOGGER", " Location: [" + i + "][" + j + "]");
//          Log.d("LOGGER", " Location 1 east: [" + i + "][" + (j + 1) + "]");
          for (int k = 1; k < ship; k++) {
            if (j + k > 10) {
              v.getBackground().clearColorFilter();
              v.invalidate();
              break;
            } else {
              String eastOneIdString = String.format("%s%s", i, j + k);
              Integer intId = Integer.parseInt(eastOneIdString);
              Button eastOneButton = findViewById(R.id.intId);
//              Log.d("LOGGER", "east: [" + i + "][" + (j + k) + "]");
              if (dragging) {
                v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                buttons[i][j + k].getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
//              Log.d("LOGGER", "[i] [j+k]: [" + i + "][" + (j + k) +"]");
//              Log.d("LOGGER", "[j] [i+k]: [" + j + "][" + (i + k) +"]");
                v.invalidate();
              } else if (exited) {
                v.getBackground().clearColorFilter();
                buttons[i][j + k].getBackground().clearColorFilter();
                v.invalidate();
              } else {
                buttons[i][j + k].setBackground(getDrawable(R.drawable.taken_cell));
                v.invalidate();
              }
//            Log.d("LOGGER", " Location:" + eastOneButton);
            }
          }
        }
      }
    }
  }

  public void west(View v, Integer ship, Boolean dragging, Boolean exited, DragEvent event) {
    Button[][] btns = new Button[11][11];
//    Log.d("LOGGER", " west");
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 11; j++) {
        Button btn = btns[i][j] = new Button(GridActivity.this);
        String idString = String.format("%s%s", i, j);
        if (v.getId() == Integer.parseInt(idString)) {
          //todo check locations to the dir of the ship {"north": 0, "east": 2, "south": 4, "west": 6}
//          Log.d("LOGGER", " Location: [" + i + "][" + j + "]");
//          Log.d("LOGGER", " Location 1 east: [" + i + "][" + (j + 1) + "]");
          for (int k = 1; k < ship; k++) {
//            Log.d("LOGGER", "K: [" + k + "]");
            if (j - k < 1) {
              v.getBackground().clearColorFilter();
              v.invalidate();
              break;
            } else {
//              Log.d("LOGGER", "west: [" + i + "][" + (j - k) + "]");
              if (dragging) {
                v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                buttons[i][j - k].getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
//              Log.d("LOGGER", "[i] [j+k]: [" + i + "][" + (j + k) +"]");
//              Log.d("LOGGER", "[j] [i+k]: [" + j + "][" + (i + k) +"]");
                v.invalidate();
              } else if (exited) {
                v.getBackground().clearColorFilter();
                buttons[i][j - k].getBackground().clearColorFilter();
                v.invalidate();
              } else {
                buttons[i][j - k].setBackground(getDrawable(R.drawable.taken_cell));
                v.invalidate();
              }
//            Log.d("LOGGER", " Location:" + eastOneButton);
            }
          }
        }
      }
    }
  }

  public void south(View v, Integer ship, Boolean dragging, Boolean exited, DragEvent event) {
    Button[][] btns = new Button[11][11];
//    Log.d("LOGGER", " south");
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 11; j++) {
        Button btn = btns[i][j] = new Button(GridActivity.this);
        String idString = String.format("%s%s", i, j);
        if (v.getId() == Integer.parseInt(idString)) {
          //todo check locations to the dir of the ship {"north": 0, "east": 2, "south": 4, "west": 6}
//          Log.d("LOGGER", " Location: [" + i + "][" + j + "]");
//          Log.d("LOGGER", " Location 1 east: [" + i + "][" + (j + 1) + "]");
          for (int k = 1; k < ship; k++) {
            if (i + k > 10) {
              v.getBackground().clearColorFilter();
              v.invalidate();
              break;
            } else {
              String eastOneIdString = String.format("%s%s", i + k, j);
              Integer intId = Integer.parseInt(eastOneIdString);
              Button eastOneButton = findViewById(R.id.intId);
//              Log.d("LOGGER", "south: [" + (i + k) + "][" + j + "]");
              if (dragging) {
                v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                buttons[i + k][j].getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
//              Log.d("LOGGER", "[i] [j+k]: [" + i + "][" + (j + k) +"]");
//              Log.d("LOGGER", "[j] [i+k]: [" + j + "][" + (i + k) +"]");
                v.invalidate();
              } else if (exited) {
                v.getBackground().clearColorFilter();
                buttons[i + k][j].getBackground().clearColorFilter();
                v.invalidate();
              } else {
                buttons[i + k][j].setBackground(getDrawable(R.drawable.taken_cell));
                v.invalidate();
              }
//            Log.d("LOGGER", " Location:" + eastOneButton);
            }
          }
        }
      }
    }
  }

  public void north(View v, Integer ship, Boolean dragging, Boolean exited, DragEvent event) {
    Button[][] btns = new Button[11][11];
//    Log.d("LOGGER", " north");
    for (int i = 0; i < 11; i++) {
      for (int j = 0; j < 11; j++) {
        Button btn = btns[i][j] = new Button(GridActivity.this);
        String idString = String.format("%s%s", i, j);
        if (v.getId() == Integer.parseInt(idString)) {
          //todo check locations to the dir of the ship {"north": 0, "east": 2, "south": 4, "west": 6}
//          Log.d("LOGGER", " Location: [" + i + "][" + j + "]");
//          Log.d("LOGGER", " Location 1 east: [" + i + "][" + (j + 1) + "]");
          for (int k = 1; k < ship; k++) {
//            Log.d("LOGGER", "K: [" + k + "]");
            if (i - k < 1) {
              v.getBackground().clearColorFilter();
              v.invalidate();
              break;
            } else {
//              Log.d("LOGGER", "north: [" + (i - k) + "][" + j + "]");
              if (dragging) {
                v.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                buttons[i - k][j].getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
//              Log.d("LOGGER", "[i] [j+k]: [" + i + "][" + (j + k) +"]");
//              Log.d("LOGGER", "[j] [i+k]: [" + j + "][" + (i + k) +"]");
                v.invalidate();
              } else if (exited) {
                v.getBackground().clearColorFilter();
                buttons[i - k][j].getBackground().clearColorFilter();
                v.invalidate();
              } else {
                buttons[i - k][j].setBackground(getDrawable(R.drawable.taken_cell));
                v.invalidate();
              }
//            Log.d("LOGGER", " Location:" + eastOneButton);
            }
          }
        }
      }
    }
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
//        Log.d("LOGGER", "shps Json: " + response.toString());
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
//        Log.d("LOGGER", "shpmap: " + directionMap);
        int size = directionMap.keySet().size();
        directionArray = new String[size];
        directionArray = directionMap.keySet().toArray(new String[]{});
        directionSpinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, directionArray);
        spinner.setAdapter(directionSpinnerArrayAdapter);
//        Log.d("LOGGER", "shpsArray: " + directionArray);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            // your code here
//            Log.d("LOGGER", "position: " + position);
            if (directionArray[position].equals("north")) {
              direction = 0;
//              Log.d("LOGGER", "Direction " + direction);
            } else if (directionArray[position].equals("east")) {
              direction = 2;
//              Log.d("LOGGER", "Direction " + direction);
            } else if (directionArray[position].equals("south")) {
              direction = 4;
//              Log.d("LOGGER", "Direction " + direction);
            } else if (directionArray[position].equals("west")) {
              direction = 6;
//              Log.d("LOGGER", "Direction " + direction);
            } else {
//              Log.d("LOGGER", "Direction error");
            }
//            0 = 2
//            1 = 0
//            2 = 4
            //3 = 6
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
//        Log.d("LOGGER", "Credentials: " + credentials);
        return headers;
      }


    };
    requestQueue.add(request);


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
//        Log.d("LOGGER", "shps Json: " + response.toString());
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
//        Log.d("LOGGER", "shpmap: " + shipMap);
        int size = shipMap.keySet().size();
        shipsArray = new String[size];
        shipsArray = shipMap.keySet().toArray(new String[]{});
//        shipSpinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, shipsArray);
//        spinner.setAdapter(shipSpinnerArrayAdapter);
//        Log.d("LOGGER", "shpsArray: " + shipsArray);
        for (String ship : shipsArray) {
//          Log.d("LOGGER", "ship: " + ship );

        }
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




  private Boolean addShip() {
    boolean success = false;
    Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
    Network network = new BasicNetwork(new HurlStack());
    RequestQueue requestQueue = new RequestQueue(cache, network);
    requestQueue.start();
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss");
    gson = gsonBuilder.create();
    // todo build string for send
//contains error

    String url = BATTLE_SERVER_URL + "game/" + gameId + "/add_ship/"+ currentShip +"/" + row + "/" + col + "/"+ direction +".json";
    Log.d("LOGGER", "String url : " + url);
    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
      @Override
      public void onResponse(JSONObject response) {
        Log.d("LOGGER", "add ship Json: " + response.toString());
        if(response.toString().contains("error")){
          boolean success = false;
        }else{
          boolean success = true;
        }


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
//    request.setRetryPolicy(new DefaultRetryPolicy(
//        10000,
//        DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
//        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    requestQueue.add(request);
return success; // todo dont leave this hard coded check for response
  }


public void onReadyButtonClicked(View view){

  Intent intent = new Intent(getApplicationContext(), BattleActivity.class);
  startActivity(intent);
}

  private void GetGameStatus() {
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

