package com.github.kalinochkind.abbyy.hw3;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

class CharCounter {

  private Reader reader;

  public CharCounter(Reader reader) {
    this.reader = reader;
  }

  public Map<Character, Integer> countChars() throws IOException {
    HashMap<Character, Integer> result = new HashMap<>();
    int character;
    while((character = reader.read()) != -1) {
      result.put((char) character, result.getOrDefault((char) character, 0) + 1);
    }
    return result;
  }
}
