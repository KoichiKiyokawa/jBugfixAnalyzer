package edu.koichi.packs.common;

import java.io.File;

import org.junit.Before;

import edu.koichi.packs.entities.Repository;
import edu.koichi.packs.utilities.RunCommand;

public class UseTestRepo {
  protected final String testRepoDir = "../jBugfixAnalyzer-test";
  private final String testRepoURL = "https://github.com/KoichiKiyokawa/jBugfixAnalyzer-test.git";
  protected Repository repo;

  @Before
  public void setup() {
    Repository.relativeRepositoryPath = testRepoDir;
    // TODO: git clone なしでレポジトリが作れたほうが良い？
    File testRepo = new File(testRepoDir);
    if (testRepo.exists()) {
      RunCommand.run(String.format("git remote add origin %s", testRepoURL), testRepoDir);
      RunCommand.run("git reset --hard origin/master", testRepoDir);
    } else {
      RunCommand.run(String.format("git clone %s", testRepoURL), "../");
    }
    this.repo = new Repository(testRepoDir);
  }

  protected void checkoutMaster() {
    RunCommand.run("git checkout master", testRepoDir);
  }
}