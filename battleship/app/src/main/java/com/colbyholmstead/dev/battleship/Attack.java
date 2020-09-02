package com.colbyholmstead.dev.battleship;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attack {

  @SerializedName("game_id")
  @Expose
  private int gameId;
  @SerializedName("row")
  @Expose
  private String row;
  @SerializedName("col")
  @Expose
  private int col;
  @SerializedName("hit")
  @Expose
  private boolean hit;
  @SerializedName("comp_row")
  @Expose
  private String compRow;
  @SerializedName("comp_col")
  @Expose
  private int compCol;
  @SerializedName("comp_hit")
  @Expose
  private boolean compHit;
  @SerializedName("user_ship_sunk")
  @Expose
  private String userShipSunk;
  @SerializedName("comp_ship_sunk")
  @Expose
  private String compShipSunk;
  @SerializedName("num_computer_ships_sunk")
  @Expose
  private int numComputerShipsSunk;
  @SerializedName("num_your_ships_sunk")
  @Expose
  private int numYourShipsSunk;
  @SerializedName("winner")
  @Expose
  private String winner;

  /**
   * No args constructor for use in serialization
   *
   */
  public Attack() {
  }

  /**
   *
   * @param gameId
   * @param compShipSunk
   * @param col
   * @param hit
   * @param compHit
   * @param winner
   * @param numComputerShipsSunk
   * @param compCol
   * @param numYourShipsSunk
   * @param row
   * @param compRow
   * @param userShipSunk
   */
  public Attack(int gameId, String row, int col, boolean hit, String compRow, int compCol, boolean compHit, String userShipSunk, String compShipSunk, int numComputerShipsSunk, int numYourShipsSunk, String winner) {
    super();
    this.gameId = gameId;
    this.row = row;
    this.col = col;
    this.hit = hit;
    this.compRow = compRow;
    this.compCol = compCol;
    this.compHit = compHit;
    this.userShipSunk = userShipSunk;
    this.compShipSunk = compShipSunk;
    this.numComputerShipsSunk = numComputerShipsSunk;
    this.numYourShipsSunk = numYourShipsSunk;
    this.winner = winner;
  }

  public int getGameId() {
    return gameId;
  }

  public void setGameId(int gameId) {
    this.gameId = gameId;
  }

  public String getRow() {
    return row;
  }

  public void setRow(String row) {
    this.row = row;
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public boolean isHit() {
    return hit;
  }

  public void setHit(boolean hit) {
    this.hit = hit;
  }

  public String getCompRow() {
    return compRow;
  }

  public void setCompRow(String compRow) {
    this.compRow = compRow;
  }

  public int getCompCol() {
    return compCol;
  }

  public void setCompCol(int compCol) {
    this.compCol = compCol;
  }

  public boolean isCompHit() {
    return compHit;
  }

  public void setCompHit(boolean compHit) {
    this.compHit = compHit;
  }

  public String getUserShipSunk() {
    return userShipSunk;
  }

  public void setUserShipSunk(String userShipSunk) {
    this.userShipSunk = userShipSunk;
  }

  public String getCompShipSunk() {
    return compShipSunk;
  }

  public void setCompShipSunk(String compShipSunk) {
    this.compShipSunk = compShipSunk;
  }

  public int getNumComputerShipsSunk() {
    return numComputerShipsSunk;
  }

  public void setNumComputerShipsSunk(int numComputerShipsSunk) {
    this.numComputerShipsSunk = numComputerShipsSunk;
  }

  public int getNumYourShipsSunk() {
    return numYourShipsSunk;
  }

  public void setNumYourShipsSunk(int numYourShipsSunk) {
    this.numYourShipsSunk = numYourShipsSunk;
  }

  public String getWinner() {
    return winner;
  }

  public void setWinner(String winner) {
    this.winner = winner;
  }

}