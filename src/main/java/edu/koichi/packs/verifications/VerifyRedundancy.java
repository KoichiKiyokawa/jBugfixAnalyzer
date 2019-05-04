package edu.koichi.packs.verifications;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import edu.koichi.packs.entities.CodeLine;
import edu.koichi.packs.entities.Commit;
import edu.koichi.packs.entities.Repository;

/**
 * 冗長化仮説の検証
 */
public class VerifyRedundancy {
  private Repository repo;
  private int allInsertedLinesCount = 0;
  private int bugfixCommitCount = 0;
  private int insertedBugfixLineCount = 0;
  protected List<String> hasIngredientInsertedLines = new ArrayList<>();

  public VerifyRedundancy(Repository repo) {
    this.repo = repo;
    repo.init();
  }

  public void verify() {
    List<Commit> commits = repo.commits;
    for (int i = 0; i < commits.size(); i++) {
      Commit c = commits.get(i);
      this.allInsertedLinesCount += c.insertedLines.size();
      if (c.isBugfixCommit()) {
        this.insertedBugfixLineCount += c.insertedLines.size();
        this.bugfixCommitCount++;

        repo.checkout(commits.get(i + 1));
        for (String sourceFilenameWithRelativePath : repo.getSourceFilenamesWithRelativePath()) {
          if (isCommitHasIngredientInSource(c, sourceFilenameWithRelativePath)) {
            break;
          }
        }
      }
    }

    showResult();
    writeResult();
    repo.init();
  }

  /**
   * 指定された挿入行が、同じく指定されたソースに存在していれば、配列に格納
   *
   * @param insertedLines
   * @param sourceFilenameWithRelativePath
   */
  public boolean isCommitHasIngredientInSource(Commit commit, String sourceFilenameWithRelativePath) {
    boolean hasIngredient = false;
    List<String> sourceFileLines = new ArrayList<>();
    Path sourceFilePath = Paths.get(Repository.relativeRepositoryPath + "/" + sourceFilenameWithRelativePath);
    try {
      sourceFileLines = Files.readAllLines(sourceFilePath, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (CodeLine insertedCodeLine : commit.insertedCodeLines) {
      if (insertedCodeLine.shouldIgnore()) {
        continue;
      }
      for (String srcline : sourceFileLines) {
        if (isIngredient(srcline, insertedCodeLine.line)) {
          hasIngredientInsertedLines.add(insertedCodeLine.line);
          hasIngredient = true;
          break;
        }
      }
    }
    return hasIngredient;
  }

  protected boolean isIngredient(String srcline, String insertedLine) {
    return srcline.trim().equals(insertedLine);
  }

  private void showResult() {
    System.out.println("BugfixCommitCount / AllCommitCount : " + bugfixCommitCount + " / " + repo.commits.size());
    System.out.println("hasIngredientInsertedLines / BugfixInsertedLineCount : " + hasIngredientInsertedLines.size()
        + "/" + insertedBugfixLineCount);
    System.out.println(allInsertedLinesCount);
  }

  private void writeResult() {
    try {
      File resultDir = new File("result/");
      if (!resultDir.exists()) {
        resultDir.mkdir();
      }
      File file = new File("result/res.txt");
      if (!file.exists()) {
        file.createNewFile();
      }
      FileWriter fw = new FileWriter(file);
      PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

      for (String line : hasIngredientInsertedLines) {
        pw.println(line);
      }

      pw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}