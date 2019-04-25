package edu.koichi.packs.entities;

import edu.koichi.packs.utilities.RunCommand;

public class CodeLine {
  private String relativeSourcePath; // 分析対象のリポジトリでの相対位置
  public int lineNo;
  public String line;

  /**
   *
   * @param relativeSourcePath 対象のリポジトリから、そのファイルまでの相対パス
   * @param lineNo             何行目
   * @param line               実際の文字列
   */
  public CodeLine(String relativeSourcePath, int lineNo, String line) {
    this.relativeSourcePath = relativeSourcePath;
    this.lineNo = lineNo;
    this.line = line;
  }

  public String getCommitMessage() {
    String blameLine = RunCommand.run(String.format("git blame -L %d,%d %s", lineNo, lineNo, relativeSourcePath),
        Repository.relativeRepositoryPath);
    String sha = blameLine.substring(0, 8);
    String msg = RunCommand.run("git log " + sha + " -1 --pretty=format:%s", Repository.relativeRepositoryPath);
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