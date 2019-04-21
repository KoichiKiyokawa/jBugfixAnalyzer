package edu.koichi.packs.common;

import java.io.File;

import edu.koichi.packs.entities.Repository;
import edu.koichi.packs.utilities.RunCommand;
import junit.framework.TestCase;

public class UseTestRepo extends TestCase {
  protected final String testRepoDir = "../jBugfixAnalyzer-test";
  protected final String testRepoURL = "https://github.com/KoichiKiyokawa/jBugfixAnalyzer-test.git";

  protected Repository repo;

  public UseTestRepo() {
    super();
    initTestRepo();
  }

  private void initTestRepo() {
    File testRepo = new File(testRepoDir);
    if (testRepo.exists()) {
      RunCommand.run("git reset --hard origin/master", testRepoDir);
    } else {
      RunCommand.run(String.format("git clone %s", testRepoURL), "../");
    }
  }

  protected void checkoutMaster() {
    RunCommand.run("git checkout master", testRepoDir);
  }
}