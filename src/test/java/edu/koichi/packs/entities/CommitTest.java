package edu.koichi.packs.entities;

import java.util.Properties;

import edu.koichi.packs.common.UseTestRepo;

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
    Commit firstCommit = repo.commits.get(0);
    assertEquals(firstCommit.message, "Fix Test10.java");
    assertEquals(firstCommit.insertedLines.size(), 1);
    assertEquals(firstCommit.insertedLines.get(0), "System.out.println(\"This is a test code10.\");");
  }
}