package com.colbyholmstead.dev.battleship;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class CellButton extends androidx.appcompat.widget.AppCompatButton {
  public CellButton(Context context) {
    super(context);
  }

  private Boolean hasShip;
  private Boolean miss;
  private Boolean hit;
  private Boolean waiting;
  private String location;
  private Integer locator;

  public Boolean getHasShip() {
    return hasShip;
  }

  public void setHasShip(Boolean hasShip) {
    this.hasShip = hasShip;
  }

  public Boolean getMiss() {
    return miss;
  }

  public void setMiss(Boolean miss) {
    this.miss = miss;
  }

  public Boolean getHit() {
    return hit;
  }

  public void setHit(Boolean hit) {
    this.hit = hit;
  }

  public Boolean getWaiting() {
    return waiting;
  }

  public void setWaiting(Boolean waiting) {
    this.waiting = waiting;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Integer getLocator() {
    return locator;
  }

  public void setLocator(Integer locator) {
    this.locator = locator;
  }
}
