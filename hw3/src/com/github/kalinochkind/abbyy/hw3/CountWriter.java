package com.github.kalinochkind.abbyy.hw3;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.stream.Collectors;

class CountWriter {

  private Writer writer;

  public CountWriter(Writer writer) {
    this.writer = writer;
  }

  public void writeCounts(Map<Character, Integer> counts) throws IOException {
    for(Character c : counts.keySet().stream().sorted().collect(Collectors.toList())) {
      if (c > 32) {
        writer.write(c + " " + counts.get(c).toString() + "\n");
      }
    }
  }
}
