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
  private int allInsertedLinesCount = 0;
  private int bugfixCommitCount = 0;
  private int insertedBugfixLineCount = 0;
  protected List<String> hasIngredientInsertedLines = new ArrayList<String>();

  public VerifyRedundancy(Repository repo) {
    this.repo = repo;
    repo.init();
  }

  public void verify() {
    List<Commit> commits = repo.commits;
    for (int i = 0; i < commits.size(); i++) {
      Commit c = commits.get(i);
      allInsertedLinesCount += c.insertedLines.size();
      if (!c.isBugfixCommit())
        continue;

      List<String> insertedLines = c.insertedLines;
      this.insertedBugfixLineCount += insertedLines.size();
      this.bugfixCommitCount++;

      repo.checkout(commits.get(i + 1));
      for (String sourceFilenameWithRelativePath : repo.getSourceFilenamesWithRelativePath()) {
        checkSourceHasIngredient(insertedLines, sourceFilenameWithRelativePath);
      }
    }

    showResult();
    repo.init();
  }

  /**
   * 指定された挿入行が、同じく指定されたソースに存在していれば、配列に格納
   *
   * @param insertedLines
   * @param sourceFilenameWithRelativePath
   */
  public void checkSourceHasIngredient(List<String> insertedLines, String sourceFilenameWithRelativePath) {
    List<String> linesFromSourceFile = new ArrayList<String>();
    Path sourceFilePath = Paths.get(repo.relativeRepositoryPath + "/" + sourceFilenameWithRelativePath);
    try {
      linesFromSourceFile = Files.readAllLines(sourceFilePath, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (String insertedLine : insertedLines) {
      List<String> unindentedLinesFromSourceFile = new ArrayList<String>();
      linesFromSourceFile.forEach(l -> unindentedLinesFromSourceFile.add(l.trim()));
      if (unindentedLinesFromSourceFile.contains(insertedLine)) {
        hasIngredientInsertedLines.add(insertedLine);
      }
    }
  }

  private void showResult() {
    System.out.println("BugfixCommitCount / AllCommitCount : " + bugfixCommitCount + " / " + repo.commits.size());
    System.out.println("hasIngredientInsertedLines / BugfixInsertedLineCount : " + insertedBugfixLineCount + "/"
        + hasIngredientInsertedLines.size());
    System.out.println(allInsertedLinesCount);
  }
}