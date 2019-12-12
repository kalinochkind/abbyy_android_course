package com.github.kalinochkind.abbyy.hw3;

import java.io.*;
import java.util.Map;

public final class Main {

  public static void main(String[] args) {
    if (args.length < 2) {
      System.err.println("Two filenames required");
      System.exit(1);
    }

    Map<Character, Integer> charCounts = null;
    try(BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
      CharCounter counter = new CharCounter(reader);
      charCounts = counter.countChars();
    } catch (IOException e) {
      System.err.println("I/O error");
      System.exit(1);
    }

    try(FileWriter writer = new FileWriter(args[1])) {
      CountWriter countWriter = new CountWriter(writer);
      countWriter.writeCounts(charCounts);
    } catch (IOException e) {
      System.err.println("I/O error");
      System.exit(1);
    }
  }
}
