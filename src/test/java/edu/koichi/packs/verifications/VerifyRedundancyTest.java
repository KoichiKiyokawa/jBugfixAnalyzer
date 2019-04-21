package edu.koichi.packs.verifications;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import edu.koichi.packs.common.UseTestRepo;
import edu.koichi.packs.entities.Repository;
import junit.framework.TestCase;

public class VerifyRedundancyTest extends UseTestRepo {
  public void testFileRead() {
    this.repo = new Repository(testRepoDir);
    List<String> lines = new ArrayList<String>();
    String sourceFilename = "src/Test1.java";
    Path sourceFilePath = Paths.get(repo.relativeRepositoryPath + "/" + sourceFilename);
    try {
      lines = Files.readAllLines(sourceFilePath, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertEquals(lines.size(), 5);
  }


}