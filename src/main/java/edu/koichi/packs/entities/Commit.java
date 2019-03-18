package edu.koichi.packs.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.koichi.packs.entities.Diff;
import edu.koichi.packs.utilities.RunCommand;

public class Commit {
  public String sha;
  public String message;
  public List<String> insertedLines;
  public List<String> deletedLines;

  public Commit(String sha, String message) {
    this.sha = sha;
    this.message = message;
    this.separateDiffsIntoInsertAndDelete();
  }

  private void separateDiffsIntoInsertAndDelete() {
    for (Diff diff : this.getDiffs()) {
      if (diff.isInsertedLine()) {
        this.insertedLines.add(diff.toString());
      } else if (diff.isdeletedLine()) {
        this.deletedLines.add(diff.toString());
      }
    }
  }

  private List<Diff> getDiffs() {
    String diffStr = RunCommand.run("git", "show", this.sha);
    String[] diffLines = diffStr.split("\n");
    List<Diff> diffs = new ArrayList<>();
    Arrays.stream(diffLines).forEach(diffLine -> diffs.add(new Diff(diffLine)));
    return diffs;
  }
}