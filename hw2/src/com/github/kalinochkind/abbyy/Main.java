package com.github.kalinochkind.abbyy;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    int[][] array = new int[6][7];
    fillArray(array);
    printArray(array);
    permuteRows(array);
    printArray(array);
  }

  private static void fillArray(int[][] array) {
    Random random = new Random();
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        array[i][j] = random.nextInt(10);
      }
    }
  }

  private static void printArray(int[][] array) {
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        System.out.print(array[i][j]);
        System.out.print(' ');
      }
      System.out.println();
    }
    System.out.println();
  }

  private static void permuteRows(int[][] array) {
    for (int i = 0; i < array.length; i++) {
      int maxIndex = 0;
      for (int j = 1; j < array[i].length; j++) {
        if (array[i][j] > array[i][maxIndex]) {
          maxIndex = j;
        }
      }
      if (maxIndex != 0) {
        int t = array[i][0];
        array[i][0] = array[i][maxIndex];
        array[i][maxIndex] = t;
      }
    }
  }
}
