package edu.koichi.packs.common;

import java.io.File;

import edu.koichi.packs.entities.Repository;
import edu.koichi.packs.utilities.RunCommand;
import junit.framework.TestCase;

public class UseTestRepo extends TestCase {
  protected final String testRepoDir = "../jBugfixAnalyzer-test";
  protected Repository repo;

  public UseTestRepo() {
    super();
    initTestRepo();
  }

  private void initTestRepo() {
    File testRepo = new File(testRepoDir);
    if (!testRepo.exists()) {
      testRepo.mkdir();
    }
    // TODO: git clone なしでレポジトリが作れたほうが良い？
    // RunCommand.run(String.format("cp test-repo-initializer/init.rb %s",
    // testRepoDir), "./");
    // RunCommand.run("ruby init.rb", testRepoDir);
    RunCommand.run("git remote add origin git@github.com:KoichiKiyokawa/jBugfixAnalyzer-test.git", testRepoDir);
    RunCommand.run("git reset --hard origin/master", testRepoDir);
  }

  protected void checkoutMaster() {
    RunCommand.run("git checkout master", testRepoDir);
  }
}