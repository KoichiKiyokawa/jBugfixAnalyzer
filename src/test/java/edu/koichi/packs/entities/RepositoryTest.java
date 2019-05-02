package edu.koichi.packs.entities;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import edu.koichi.packs.common.UseTestRepo;
import edu.koichi.packs.utilities.RunCommand;

public class RepositoryTest extends UseTestRepo {
  private int initialCommitLength;

  @Before
  public void setup() {
    this.repo = new Repository(this.testRepoDir);
    String res = RunCommand.run("git log --oneline", this.testRepoDir); // count line
    this.initialCommitLength = res.split("\n").length;
  }

  @Test
  public void testCommitLength() {
    assertEquals(initialCommitLength, repo.commits.size());
  }

  @Test
  public void testCheckout() {
    Commit secondCommit = repo.commits.get(1);
    repo.checkout(secondCommit);
    String headCommitMessage = RunCommand.run("git log --oneline -1 HEAD --pretty=format:%s", testRepoDir).trim();
    assertEquals(secondCommit.message, headCommitMessage);
    Repository repo2 = new Repository(testRepoDir);
    assertEquals(initialCommitLength - 1, repo2.commits.size());
    checkoutMaster();
  }

  @Test
  public void testGetSourceFiles() {
    String[] files = repo.getSourceFilenamesWithRelativePath();
    assertEquals(10, files.length);
  }

  @Test
  public void testCheckoutAndSourceFilesCount() {
    Commit secondCommit = repo.commits.get(1);
    repo.checkout(secondCommit);
    String[] files = repo.getSourceFilenamesWithRelativePath();
    assertEquals(10, files.length);
    checkoutMaster();
  }
}