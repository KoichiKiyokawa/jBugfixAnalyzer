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
      this.allInsertedLinesCount += c.insertedLines.size();
      if (c.isBugfixCommit()) {
        this.insertedBugfixLineCount += c.insertedLines.size();
        this.bugfixCommitCount++;

        repo.checkout(commits.get(i + 1));
        for (String sourceFilenameWithRelativePath : repo.getSourceFilenamesWithRelativePath()) {
          checkSourceHasIngredient(c.insertedLines, sourceFilenameWithRelativePath);
        }
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
    List<String> sourceFileLines = new ArrayList<String>();
    Path sourceFilePath = Paths.get(repo.relativeRepositoryPath + "/" + sourceFilenameWithRelativePath);
    try {
      sourceFileLines = Files.readAllLines(sourceFilePath, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (String insertedLine : insertedLines) {
      for (String srcline : sourceFileLines) {
        if (isIngredient(srcline, insertedLine)) {
          hasIngredientInsertedLines.add(insertedLine);
        }
      }
    }
  }

  private boolean isIngredient(String srcline, String insertedLine) {
    return srcline.trim().equals(insertedLine);
  }

  private void showResult() {
    System.out.println("BugfixCommitCount / AllCommitCount : " + bugfixCommitCount + " / " + repo.commits.size());
    System.out.println("hasIngredientInsertedLines / BugfixInsertedLineCount : " + hasIngredientInsertedLines.size()
        + "/" + insertedBugfixLineCount);
    System.out.println(allInsertedLinesCount);
  }
}