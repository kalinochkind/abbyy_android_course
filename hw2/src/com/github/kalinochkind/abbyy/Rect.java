package com.github.kalinochkind.abbyy;

public class Rect {
  private final Point topLeft;
  private final Point bottomRight;

  public Rect(Point topLeft, Point bottomRight) {
    if (topLeft.getX() > bottomRight.getX() || topLeft.getY() > bottomRight.getY()) {
      throw new IllegalArgumentException("Incorrect points");
    }
    this.topLeft = topLeft;
    this.bottomRight = bottomRight;
  }

  public Point getTopLeft() {
    return topLeft;
  }

  public Point getBottomRight() {
    return bottomRight;
  }
}
