package edu.koichi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import edu.koichi.packs.entities.*;

public class Main {
  public static void main(String args[]) {
    System.out.println(args[0]);
    Repository repo = new Repository(args[0]);
    List<Commit> commits = repo.getCommits();
    commits.forEach(c -> {
      System.out.println(c.message);
      // if (c.isBugfixCommit()) {
      // System.out.println("this is bugfix commit");
      // System.out.println(c.message);
      // // c.insertedLines.forEach(l -> System.out.println(l));
      // } else {
      // System.out.println(c.message);
      // }
    });
  }
}