package edu.koichi.packs.entities;

import edu.koichi.packs.common.UseTestRepo;
import edu.koichi.packs.utilities.RunCommand;

public class RepositoryTest extends UseTestRepo {
  private int initialCommitLength;

  public RepositoryTest() {
    super();
    this.repo = new Repository(this.testRepoDir);
    String res = RunCommand.run("git log --oneline", this.testRepoDir); // count line
    this.initialCommitLength = res.split("\n").length;
  }

  public void testCommitLength() {
    assertEquals(repo.commits.size(), initialCommitLength);
  }

  public void testCheckout() {
    Commit secondCommit = repo.commits.get(1);
    repo.checkout(secondCommit);
    // チェックアウト処理がうまくいっていない？
    String headCommitMessage = RunCommand.run("git log --oneline -1 HEAD --pretty=format:%s", testRepoDir).trim();
    assertEquals(secondCommit.message, headCommitMessage);
    Repository repo2 = new Repository(testRepoDir);
    assertEquals(repo2.commits.size(), initialCommitLength - 1);
    checkoutMaster();
  }

  public void testGetSourceFiles() {
    String[] files = repo.getSourceFilenamesWithRelativePath();
    assertEquals(files.length, 10);
  }

  public void testCheckoutAndSourceFilesCount() {
    Commit secondCommit = repo.commits.get(1);
    repo.checkout(secondCommit);
    String[] files = repo.getSourceFilenamesWithRelativePath();
    assertEquals(files.length, 10);
    checkoutMaster();
  }
}