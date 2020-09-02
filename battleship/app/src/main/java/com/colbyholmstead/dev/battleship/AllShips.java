package com.colbyholmstead.dev.battleship;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllShips {

  @SerializedName("carrier")
  @Expose
  private Integer carrier;
  @SerializedName("battleship")
  @Expose
  private Integer battleship;
  @SerializedName("cruiser")
  @Expose
  private Integer cruiser;
  @SerializedName("submarine")
  @Expose
  private Integer submarine;
  @SerializedName("destroyer")
  @Expose
  private Integer destroyer;

  /**
   * No args constructor for use in serialization
   *
   */
  public AllShips() {
  }

  /**
   *
   * @param carrier
   * @param battleship
   * @param submarine
   * @param destroyer
   * @param cruiser
   */
  public AllShips(Integer carrier, Integer battleship, Integer cruiser, Integer submarine, Integer destroyer) {
    super();
    this.carrier = carrier;
    this.battleship = battleship;
    this.cruiser = cruiser;
    this.submarine = submarine;
    this.destroyer = destroyer;
  }

  public Integer getCarrier() {
    return carrier;
  }

  public void setCarrier(Integer carrier) {
    this.carrier = carrier;
  }

  public Integer getBattleship() {
    return battleship;
  }

  public void setBattleship(Integer battleship) {
    this.battleship = battleship;
  }

  public Integer getCruiser() {
    return cruiser;
  }

  public void setCruiser(Integer cruiser) {
    this.cruiser = cruiser;
  }

  public Integer getSubmarine() {
    return submarine;
  }

  public void setSubmarine(Integer submarine) {
    this.submarine = submarine;
  }

  public Integer getDestroyer() {
    return destroyer;
  }

  public void setDestroyer(Integer destroyer) {
    this.destroyer = destroyer;
  }

}