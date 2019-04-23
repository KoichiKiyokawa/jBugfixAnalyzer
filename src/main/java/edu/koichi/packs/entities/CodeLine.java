package edu.koichi.packs.entities;

import java.io.File;

import edu.koichi.packs.utilities.RunCommand;

public class CodeLine {
  public File filePathFromThisAppRoot;
  public int lineNo;
  public String line;
  private String relativeSourcePath; // 分析対象のリポジトリでの相対位置

  /**
   *
   * @param file   どのファイル
   * @param lineNo 何行目
   * @param line   実際の文字列
   */
  public CodeLine(File file, int lineNo, String line, String relativeSourcePath) {
    this.filePathFromThisAppRoot = file;
    this.lineNo = lineNo;
    this.line = line;
    this.relativeSourcePath = relativeSourcePath;
  }

  public String getCommitMessage() {
    String repoDir = filePathFromThisAppRoot.toPath().toString().replace(relativeSourcePath, "");
    String blameLine = RunCommand.run(String.format("git blame -L %d,%d %s", lineNo, lineNo, relativeSourcePath),
        repoDir);
    String sha = blameLine.substring(0, 8);
    String msg = RunCommand.run("git log " + sha + " -1 --pretty=format:%s", repoDir);
    return msg.trim();
  }

  public boolean shouldIgnore() {
    if (line.length() <= 1) {
      return true;
    }
    return (this.isComment() || this.isOnlyBracket());
  }

  private boolean isComment() {
    String startTwoChar = line.substring(0, 2);
    return (line.startsWith("*") || startTwoChar.equals("//") || startTwoChar.equals("/*"));
  }

  private boolean isOnlyBracket() {
    return (line.equals("}") && line.length() == 1);
  }

}