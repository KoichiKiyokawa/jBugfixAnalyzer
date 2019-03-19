package edu.koichi.packs.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.koichi.packs.entities.Diff;
import edu.koichi.packs.utilities.RunCommand;

/**
 * Repository has many Commits. Commit has many Diffs.
 */
public class Commit {
  private String relativeRepositoryPath;
  public String sha;
  public String message;
  public List<String> insertedLines = new ArrayList<>();
  public List<String> deletedLines = new ArrayList<>();

  public Commit(String sha, String message, String relativeRepositoryPath) {
    this.sha = sha;
    this.message = message;
    this.relativeRepositoryPath = relativeRepositoryPath;
    this.separateDiffsIntoInsertAndDelete();
  }

  /**
   * 差分を挿入行と削除行に振り分ける
   */
  private void separateDiffsIntoInsertAndDelete() {
    for (Diff diff : this.getDiffs()) {
      if (diff.isInsertedLine()) {
        this.insertedLines.add(diff.toCode());
      } else if (diff.isDeletedLine()) {
        this.deletedLines.add(diff.toCode());
      }
    }
  }

  private List<Diff> getDiffs() {
    // TODO: 拡張子を指定できるように ex) git show <sha> -- '*.java'
    String diffStr = RunCommand.run(String.format("git show %s", this.sha), this.relativeRepositoryPath);
    String[] diffLines = diffStr.split("\n");
    List<Diff> diffs = new ArrayList<>();
    Arrays.stream(diffLines).forEach(diffLine -> diffs.add(new Diff(diffLine)));
    return diffs;
  }
}