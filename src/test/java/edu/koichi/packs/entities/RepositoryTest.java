package edu.koichi.packs.entities;

import java.io.File;

import edu.koichi.packs.utilities.RunCommand;
import junit.framework.TestCase;

public class RepositoryTest extends TestCase {
  private final String testRepoDir = "../jBugfixAnalyzer-test";
  private Repository repo;
  private int initialCommitLength;
  private final String testRepoURL = "https://github.com/KoichiKiyokawa/jBugfixAnalyzer-test.git";

  public RepositoryTest() {
    super();
    initTestRepo();
    String res = RunCommand.run("git log --oneline", testRepoDir); // count line
    this.repo = new Repository(testRepoDir);
    this.initialCommitLength = res.split("\n").length;
  }

  private void initTestRepo() {
    File testRepo = new File(testRepoDir);
    if (testRepo.exists()) {
      RunCommand.run("git reset --hard origin/master", testRepoDir);
    } else {
      RunCommand.run(String.format("git clone %s", testRepoURL), "../");
    }
  }

  public void testCommitLength() {
    assertEquals(repo.commits.size(), initialCommitLength);
  }

  public void testCheckout() {
    Commit secondCommit = repo.commits.get(1);
    repo.checkout(secondCommit);
    assertEquals(secondCommit.message, "Add Test9.java");
    Repository repo2 = new Repository(testRepoDir);
    assertEquals(repo2.commits.size(), initialCommitLength - 1);
    RunCommand.run("git checkout master", testRepoDir);
  }

  public void testGetSourceFiles() {
    String[] files = repo.getSourceFilenames();
    assertEquals(files.length, 10);
  }
}