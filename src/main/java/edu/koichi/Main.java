package edu.koichi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import edu.koichi.packs.entities.*;

public class Main {
  public static void main(String args[]) {
    Repository repo = new Repository(args[0]);
    List<Commit> commits = repo.getCommits();
    commits.forEach(c -> {
      if (c.isBugfixCommit()) {
        System.out.println(c.message);
        // c.insertedLines.forEach(l -> System.out.println(l));
      }
    });
  }
}