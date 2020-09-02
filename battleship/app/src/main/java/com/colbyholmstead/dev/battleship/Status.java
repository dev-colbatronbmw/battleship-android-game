package com.colbyholmstead.dev.battleship;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

  @SerializedName("game_id")
  @Expose
  private int gameId;
  @SerializedName("attack_board")
  @Expose
  private String attackBoard;
  @SerializedName("defend_board")
  @Expose
  private String defendBoard;

  /**
   * No args constructor for use in serialization
   *
   */
  public Status() {
  }

  /**
   *
   * @param gameId
   * @param defendBoard
   * @param attackBoard
   */
  public Status(int gameId, String attackBoard, String defendBoard) {
    super();
    this.gameId = gameId;
    this.attackBoard = attackBoard;
    this.defendBoard = defendBoard;
  }

  public int getGameId() {
    return gameId;
  }

  public void setGameId(int gameId) {
    this.gameId = gameId;
  }

  public String getAttackBoard() {
    return attackBoard;
  }

  public void setAttackBoard(String attackBoard) {
    this.attackBoard = attackBoard;
  }

  public String getDefendBoard() {
    return defendBoard;
  }

  public void setDefendBoard(String defendBoard) {
    this.defendBoard = defendBoard;
  }

}