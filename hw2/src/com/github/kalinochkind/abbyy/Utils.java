package com.github.kalinochkind.abbyy;

public class Utils {
  private Utils() {
  }

  public static int area(Rect r) {
    return (r.getBottomRight().getX() - r.getTopLeft().getX()) *
            (r.getBottomRight().getY() - r.getTopLeft().getY());
  }

  public static int perimeter(Rect r) {
    return (r.getBottomRight().getX() - r.getTopLeft().getX()) * 2 +
            (r.getBottomRight().getY() - r.getTopLeft().getY()) * 2;
  }

  public static Rect toOrigin(Rect r) {
    return new Rect(new Point(), new Point(
            r.getBottomRight().getX() - r.getTopLeft().getX(),
            r.getBottomRight().getY() - r.getTopLeft().getY()));
  }
}
