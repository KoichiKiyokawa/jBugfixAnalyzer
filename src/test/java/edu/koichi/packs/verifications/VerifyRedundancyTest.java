package edu.koichi.packs.verifications;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.koichi.packs.common.UseTestRepo;
import edu.koichi.packs.entities.Repository;

public class VerifyRedundancyTest extends UseTestRepo {
  private final String sourceFilenameWithRelativePath = "src/Test1.java";
  private Repository repo = new Repository(testRepoDir);
  private Path sourceFilePath = Paths.get(repo.relativeRepositoryPath + "/" + sourceFilenameWithRelativePath);

  public void testFileRead() {
    List<String> lines = new ArrayList<String>();
    try {
      lines = Files.readAllLines(sourceFilePath, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertEquals(lines.size(), 5);
    assertEquals(lines.get(0), "public class Test1 {");
  }

  public void testCheckSourceHasIngredient() {
    VerifyRedundancy vRedundancy = new VerifyRedundancy(repo);
    List<String> insertions = Arrays.asList("public class Test1 {");
    vRedundancy.checkSourceHasIngredient(insertions, sourceFilePath.toString());
    assertEquals(vRedundancy.hasIngredientInsertedLines.size(), 1);
  }
}