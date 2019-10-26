package com.github.kalinochkind.abbyy;

public final class Main {

  private static double pointDistance(Point a, Point b) {
    long x = a.getX() - b.getX();
    long y = a.getY() - b.getY();
    return Math.sqrt(x * x + y * y);
  }

  public static void main(String[] args) {
    Point first = new Point(1, 2);
    Point second = new Point(4, -2);
    System.out.println(pointDistance(first, second));
  }
}
