package edu.koichi.packs.verifications;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import edu.koichi.packs.entities.Commit;
import edu.koichi.packs.entities.Repository;

/**
 * 冗長化仮説の検証
 */
public class VerifyRedundancy {
  private Repository repo;
  private int bugfixCommitCount = 0;
  private int insertedBugfixLineCount = 0;
  protected List<String> hasIngredientInsertedLines = new ArrayList<String>();

  public VerifyRedundancy(Repository repo) {
    this.repo = repo;
  }

  protected void verify() {
    List<Commit> commits = repo.commits;
    for (int i = 0; i < commits.size(); i++) {
      Commit c = commits.get(i);
      if (!c.isBugfixCommit())
        continue;

      List<String> insertedLines = c.insertedLines;
      this.insertedBugfixLineCount += insertedLines.size();
      this.bugfixCommitCount++;

      repo.checkout(commits.get(i + 1));
      for (String sourceFilenameWithRelativePath : repo.getSourceFilenameWithRelativePaths()) {
        checkSourceHasIngredient(insertedLines, sourceFilenameWithRelativePath);
      }
    }

    showResult();
  }

  /**
   * 指定された挿入行が、同じく指定されたソースに存在していれば、配列に格納
   *
   * @param insertedLines
   * @param sourceFilenameWithRelativePath
   */
  public void checkSourceHasIngredient(List<String> insertedLines, String sourceFilenameWithRelativePath) {
    List<String> lines = new ArrayList<String>();
    Path sourceFilePath = Paths.get(repo.relativeRepositoryPath + "/" + sourceFilenameWithRelativePath);
    try {
      lines = Files.readAllLines(sourceFilePath, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (String insertedLine : insertedLines) {
      if (lines.contains(insertedLine)) {
        hasIngredientInsertedLines.add(insertedLine);
      }
    }
  }

  private void showResult() {
    System.out.println("BugfixCommitCount : " + bugfixCommitCount);
    System.out.println("BugfixInsertedLineCount : " + insertedBugfixLineCount);
  }
}