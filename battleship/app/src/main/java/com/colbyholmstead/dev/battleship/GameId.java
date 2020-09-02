package com.colbyholmstead.dev.battleship;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameId {

  @SerializedName("game_id")
  @Expose
  private int gameId;

  /**
   * No args constructor for use in serialization
   *
   */
  public GameId() {
  }

  /**
   *
   * @param gameId
   */
  public GameId(int gameId) {
    super();
    this.gameId = gameId;
  }

  public int getGameId() {
    return gameId;
  }

  public void setGameId(int gameId) {
    this.gameId = gameId;
  }

}