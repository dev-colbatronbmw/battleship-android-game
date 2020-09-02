package com.colbyholmstead.dev.battleship;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllUsers {

  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("first_name")
  @Expose
  private String firstName;
  @SerializedName("last_name")
  @Expose
  private String lastName;
  @SerializedName("avatar_name")
  @Expose
  private String avatarName;
  @SerializedName("level")
  @Expose
  private Integer level;
  @SerializedName("coins")
  @Expose
  private Integer coins;
  @SerializedName("battles_won")
  @Expose
  private Integer battlesWon;
  @SerializedName("battles_lost")
  @Expose
  private Integer battlesLost;
  @SerializedName("battles_tied")
  @Expose
  private Integer battlesTied;
  @SerializedName("experience_points")
  @Expose
  private Integer experiencePoints;
  @SerializedName("available")
  @Expose
  private Boolean available;
  @SerializedName("online")
  @Expose
  private Boolean online;
  @SerializedName("gaming")
  @Expose
  private Boolean gaming;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("avatar_image")
  @Expose
  private String avatarImage;

  /**
   * No args constructor for use in serialization
   *
   */
  public AllUsers() {
  }

  /**
   *
   * @param lastName
   * @param battlesLost
   * @param gaming
   * @param level
   * @param coins
   * @param experiencePoints
   * @param available
   * @param battlesWon
   * @param battlesTied
   * @param firstName
   * @param avatarName
   * @param avatarImage
   * @param online
   * @param id
   * @param email
   */
  public AllUsers(Integer id, String firstName, String lastName, String avatarName, Integer level, Integer coins, Integer battlesWon, Integer battlesLost, Integer battlesTied, Integer experiencePoints, Boolean available, Boolean online, Boolean gaming, String email, String avatarImage) {
    super();
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.avatarName = avatarName;
    this.level = level;
    this.coins = coins;
    this.battlesWon = battlesWon;
    this.battlesLost = battlesLost;
    this.battlesTied = battlesTied;
    this.experiencePoints = experiencePoints;
    this.available = available;
    this.online = online;
    this.gaming = gaming;
    this.email = email;
    this.avatarImage = avatarImage;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAvatarName() {
    return avatarName;
  }

  public void setAvatarName(String avatarName) {
    this.avatarName = avatarName;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Integer getCoins() {
    return coins;
  }

  public void setCoins(Integer coins) {
    this.coins = coins;
  }

  public Integer getBattlesWon() {
    return battlesWon;
  }

  public void setBattlesWon(Integer battlesWon) {
    this.battlesWon = battlesWon;
  }

  public Integer getBattlesLost() {
    return battlesLost;
  }

  public void setBattlesLost(Integer battlesLost) {
    this.battlesLost = battlesLost;
  }

  public Integer getBattlesTied() {
    return battlesTied;
  }

  public void setBattlesTied(Integer battlesTied) {
    this.battlesTied = battlesTied;
  }

  public Integer getExperiencePoints() {
    return experiencePoints;
  }

  public void setExperiencePoints(Integer experiencePoints) {
    this.experiencePoints = experiencePoints;
  }

  public Boolean getAvailable() {
    return available;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
  }

  public Boolean getOnline() {
    return online;
  }

  public void setOnline(Boolean online) {
    this.online = online;
  }

  public Boolean getGaming() {
    return gaming;
  }

  public void setGaming(Boolean gaming) {
    this.gaming = gaming;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAvatarImage() {
    return avatarImage;
  }

  public void setAvatarImage(String avatarImage) {
    this.avatarImage = avatarImage;
  }

}