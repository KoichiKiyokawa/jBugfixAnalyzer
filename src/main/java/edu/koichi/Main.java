package edu.koichi;

import java.util.List;

import edu.koichi.packs.entities.*;

public class Main {
  public static void main(String args[]) {
    Repository repo = new Repository("../Native");
    List<Commit> commits = repo.getCommits();
    commits.forEach(c -> {
      System.out.println(c.message);
      c.insertedLines.forEach(l->System.out.println(l));
    });
  }
}