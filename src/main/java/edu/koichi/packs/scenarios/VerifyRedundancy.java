package edu.koichi.packs.scenarios;

import java.util.ArrayList;
import java.util.List;

import edu.koichi.packs.entities.Commit;
import edu.koichi.packs.entities.Repository;

/**
 * 冗長化仮説の検証
 */
public class VerifyRedundancy {
  private int bugfixCommitCount = 0;
  private int insertedBugfixLineCount = 0;
  protected List<String> hasIngredientInsertedLines = new ArrayList<String>();

  protected void verify(String repoPath) {
    Repository repo = new Repository(repoPath);
    List<Commit> commits = repo.commits;
    for (int i = 0; i < commits.size(); i++) {
      Commit c = commits.get(i);
      if (!c.isBugfixCommit())
        continue;

      List<String> insertedLines = c.insertedLines;
      this.insertedBugfixLineCount += insertedLines.size();
      this.bugfixCommitCount++;
      
      repo.checkout(commits.get(i + 1));
      for (String sourceFilename : repo.getSourceFilenames()) {
        checkSourceHasIngredient(insertedLines, sourceFilename);
      }
    }

    showResult();
  }

  /**
   * 指定された挿入行が、同じく指定されたソースに存在していれば、配列に格納
   *
   * @param insertedLines
   * @param sourceFilename
   */
  private void checkSourceHasIngredient(List<String> insertedLines, String sourceFilename) {
    for (String insertedLine : insertedLines) {
      // if source has insertedLine
      // hasIngredientInsertedLines.add(insertedLine);
      System.out.println(insertedLine);
    }
  }

  private void showResult() {
    System.out.println("BugfixCommitCount : " + bugfixCommitCount);
    System.out.println("BugfixInsertedLineCount : " + insertedBugfixLineCount);
  }
}