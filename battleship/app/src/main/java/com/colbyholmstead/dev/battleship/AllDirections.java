package com.colbyholmstead.dev.battleship;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllDirections {

  @SerializedName("north")
  @Expose
  private int north;
  @SerializedName("east")
  @Expose
  private int east;
  @SerializedName("south")
  @Expose
  private int south;
  @SerializedName("west")
  @Expose
  private int west;

  /**
   * No args constructor for use in serialization
   */
  public AllDirections() {
  }

  /**
   * @param east
   * @param south
   * @param north
   * @param west
   */
  public AllDirections(int north, int east, int south, int west) {
    super();
    this.north = north;
    this.east = east;
    this.south = south;
    this.west = west;
  }

  public int getNorth() {
    return north;
  }

  public void setNorth(int north) {
    this.north = north;
  }

  public int getEast() {
    return east;
  }

  public void setEast(int east) {
    this.east = east;
  }

  public int getSouth() {
    return south;
  }

  public void setSouth(int south) {
    this.south = south;
  }

  public int getWest() {
    return west;
  }

  public void setWest(int west) {
    this.west = west;
  }

}