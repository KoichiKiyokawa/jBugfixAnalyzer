package edu.koichi.packs.entities;

import java.util.Properties;

import edu.koichi.packs.common.UseTestRepo;
import edu.koichi.packs.utilities.RunCommand;

public class CommitTest extends UseTestRepo {
  public void testIsBugfixCommit() throws Exception {
    Commit commit = new Commit("hogehoge", "fix typo", "./");
    assertEquals(commit.isBugfixCommit(), true);
  }

  public void testIsNotBugfixCommit() throws Exception {
    Commit commit = new Commit("foofoo", "Add func", "./");
    assertEquals(commit.isBugfixCommit(), false);
  }

  public void testHasFixMessageButNotBugfixCommit() throws Exception {
    Properties properties = new Properties();
    properties.setProperty("exception_of_fix_message", "fixnum");

    Commit commit = new Commit("barbar", "Add Fixnum function", "./");
    assertEquals(commit.isBugfixCommit(), false);
  }

  public void testSeparation() throws Exception {
    this.repo = new Repository(testRepoDir);
    Commit secondCommit = repo.commits.get(1);
    assertEquals(secondCommit.message, "Fix Test10.java");
    assertEquals(secondCommit.insertedLines.size(), 1);
    assertEquals(secondCommit.insertedLines.get(0), "System.out.println(\"This is a test code10.\");");
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
    // + public static void main(String args[]) {
    assertEquals(bugfixInsertedLineCount, 2);
  }
}