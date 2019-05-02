package edu.koichi.packs.entities;

import java.util.Properties;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.koichi.packs.common.UseTestRepo;
import edu.koichi.packs.utilities.RunCommand;

public class CommitTest extends UseTestRepo {
  @Test
  public void testIsBugfixCommit() throws Exception {
    Commit commit = new Commit("hogehoge", "fix typo");
    assertEquals(commit.isBugfixCommit(), true);
  }

  @Test
  public void testIsNotBugfixCommit() throws Exception {
    Commit commit = new Commit("foofoo", "Add func");
    assertEquals(commit.isBugfixCommit(), false);
  }

  @Test
  public void testHasFixMessageButNotBugfixCommit() throws Exception {
    Properties properties = new Properties();
    properties.setProperty("exception_of_fix_message", "fixnum");

    Commit commit = new Commit("barbar", "Add Fixnum function");
    assertEquals(commit.isBugfixCommit(), false);
  }

  @Test
  public void testSeparation() throws Exception {
    this.repo = new Repository(testRepoDir);
    Commit thirdCommit = repo.commits.get(2);
    assertEquals(thirdCommit.message, "Fix Test10.java");
    assertEquals(thirdCommit.insertedLines.size(), 1);
    assertEquals(thirdCommit.insertedLines.get(0), "System.out.println(\"This is a test code10.\");");
  }

  public void testAllInsertedLineCount() {
    this.repo = new Repository(testRepoDir);
    int allInsertedLineCount = 0;
    for (Commit c : repo.commits) {
      allInsertedLineCount += c.insertedLines.size();
    }

    assertEquals(allInsertedLineCount, calcAllInsertedLineCountFromGitLog());
  }

  private int calcAllInsertedLineCountFromGitLog() {
    int allInsertedLineCountFromGitLog = 0;
    String[] gitLogs = RunCommand.run("git log --numstat --pretty=format: -- *.java", testRepoDir).split("\n");
    for (String gl : gitLogs) {
      // ex)1 0 src/Test9.java
      // 挿入行 削除行 ファイル名
      // TODO: 10行以上の追加に対応できない。。。
      if (!gl.isEmpty()) {
        allInsertedLineCountFromGitLog += Integer.parseInt(gl.substring(0, 1).trim());
      }
    }
    return allInsertedLineCountFromGitLog;
  }

  public void testBugfixInsertedLineCount() {
    this.repo = new Repository(testRepoDir);
    int bugfixInsertedLineCount = 0;
    for (Commit c : repo.commits) {
      if (c.isBugfixCommit()) {
        bugfixInsertedLineCount += c.insertedLines.size();
      }
    }

    // + System.out.println("This is a test code10.");
    // + public static void main(String[] args) {
    assertEquals(4, bugfixInsertedLineCount);
  }

  /**
   * ex)
   *
   * <pre>
   *    1 public class Test7 {
   *    2   public static void main(String[] args) {
   *    3   -    System.out.println("This is a test code7.");
   *    3   +    System.out.println("This is a fixed test code7.");
   *    4   }
   *    5 }
   * </pre>
   */
  public void testInsertedCodeLine() {
    Commit commit = new Repository(testRepoDir).commits.get(0);
    assertEquals(2, commit.insertedCodeLines.size());
    assertEquals("Fix Test7 and Test8", commit.insertedCodeLines.get(0).getCommitMessage());
    assertEquals(3, commit.insertedCodeLines.get(0).lineNo);
  }
}