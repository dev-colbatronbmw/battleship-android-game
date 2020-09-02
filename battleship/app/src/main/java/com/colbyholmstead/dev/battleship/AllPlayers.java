package com.colbyholmstead.dev.battleship;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AllPlayers extends BaseActivity {
  public ListView listView;
  public List<String> allPlayers = new ArrayList<String>();
  public List<String> allNames = new ArrayList<String>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_all_players);
//    int count = 0;
    for (AllUsers allUser : allUsers) {
      if (allUser.getAvatarName() != null) {

        allPlayers.add(allUser.getAvatarName());


      }
//      if (allUser.getAvatarName().contains("http") || allUser.getAvatarName().contains("test")){
//        Log.d("LOGGER", "bad avatar name " );
//      }

//
//count += 1;
    }
    for (String name : allPlayers) {

      if(!name.contains("http") && !name.contains(".com") &&  !name.contains("//") &&  !name.contains("https") &&  !name.contains("$") &&  !name.contains("Test") &&  !name.contains("test")){
        allNames.add(name);
        Log.d("LOGGER", "name " + name);
      }



    }
    listView = findViewById(R.id.listViewShips);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(AllPlayers.this, R.layout.activity_listview, allNames);
    listView.setAdapter(adapter);
  }


  public void onBackButtonClicked(View view){
    Intent intent = new Intent(getApplicationContext(), Lobby.class);
    startActivity(intent);
  }
}
