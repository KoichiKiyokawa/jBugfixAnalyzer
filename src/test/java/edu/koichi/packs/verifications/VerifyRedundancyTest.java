package edu.koichi.packs.verifications;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import edu.koichi.packs.common.UseTestRepo;
import edu.koichi.packs.entities.Commit;
import edu.koichi.packs.entities.Repository;

public class VerifyRedundancyTest extends UseTestRepo {
  private final String sourceFilenameWithRelativePath = "src/Test1.java";
  private Repository repo = new Repository(testRepoDir);
  private Path sourceFilePath = Paths.get(Repository.relativeRepositoryPath + "/" + sourceFilenameWithRelativePath);

  public void testFileRead() {
    List<String> lines = new ArrayList<String>();
    try {
      lines = Files.readAllLines(sourceFilePath, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertEquals(5, lines.size());
    assertEquals("public class Test1 {", lines.get(0));
  }

  public void testCheckSourceHasIngredient() {
    VerifyRedundancy vRedundancy = new VerifyRedundancy(repo);
    Commit commit = new Commit("shasha", "Hoge");
    commit.insertedLines.add("public class Test1 {");
    commit.insertedLines.add("public static void main(String[] args) {");
    vRedundancy.isCommitHasIngredientInSource(commit, sourceFilePath.toString());
    assertEquals(2, vRedundancy.hasIngredientInsertedLines.size());
  }

  public void testIsBugfixCommit() {
    assertEquals(true, repo.commits.get(0).isBugfixCommit());
  }

  public void testVerify() {
    VerifyRedundancy vRedundancy = new VerifyRedundancy(repo);
    vRedundancy.verify();
    // + System.out.println("This is a test code10.");
    // + public static void main(String[] args) {
    assertEquals(2, vRedundancy.hasIngredientInsertedLines.size());
  }
}