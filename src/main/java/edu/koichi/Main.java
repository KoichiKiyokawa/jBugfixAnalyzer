package edu.koichi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.koichi.packs.entities.*;

public class Main {
  public static void main(String args[]) {
    // Repository repo = new Repository(args[0]);
    // List<Commit> commits = repo.getCommits();
    // commits.forEach(c -> {
    // if (c.isBugfixCommit()) {
    // System.out.println(c.message);
    // // c.insertedLines.forEach(l -> System.out.println(l));
    // }
    // });

    List<String> lines = new ArrayList<String>();
    try {
      lines = Files.readAllLines(Paths.get("../jBugfixAnalyzer-test/src/Test1.java"), StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println(lines);
  }
}