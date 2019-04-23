package edu.koichi.packs.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.koichi.packs.entities.Diff;
import edu.koichi.packs.utilities.Lang;
import edu.koichi.packs.utilities.RunCommand;

/**
 * Repository has many Commits. Commit has many Diffs.
 */
public class Commit {
  public String sha;
  public String message;
  public List<String> insertedLines = new ArrayList<String>();
  public List<String> deletedLines = new ArrayList<String>();

  public Commit(String sha, String message) {
    this.sha = sha;
    this.message = message;
    this.separateDiffsIntoInsertAndDelete();
  }

  public boolean isBugfixCommit() {
    List<String> messageWords = Arrays.asList(this.message.split(" "));
    final String[] fixWords = { "fix", "fixes", "fixed" };
    for (String fixWord : fixWords) {
      if (messageWords.contains(fixWord) || messageWords.contains(Lang.toggleCapital(fixWord)))
        return true;
    }
    return false;
  }

  /**
   * 差分を挿入行と削除行に振り分ける
   */
  // TOOD: コメントは除外
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
    // TODO: 拡張子を指定できるように ex) git show <sha> -- *.java
    // TODO: とりま、ハードコーディングしたが、プロパティから読み込んだ拡張子を使うように
    String diffStr = RunCommand.run(String.format("git show %s -- *.java", this.sha), Repository.relativeRepositoryPath);
    String[] diffLines = diffStr.split("\n");
    List<Diff> diffs = new ArrayList<Diff>();
    Arrays.stream(diffLines).forEach(diffLine -> diffs.add(new Diff(diffLine)));
    return diffs;
  }
}