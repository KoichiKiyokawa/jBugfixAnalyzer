package edu.koichi.packs.entities;

import java.util.Properties;

import junit.framework.TestCase;

public class CommitTest extends TestCase {
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
}