package edu.koichi.packs.entities;

/**
 * ex1) - if (hoge)
 *
 * ex2) + hoge.fuga();
 */
public class Diff {
  public String filePath;
  public String diffLine;
  public int lineNo;

  public Diff(String diffLine) {
    this.diffLine = diffLine;
  }

  public Diff(String filePath, String diffLine, int lineNo) {
    this.filePath = filePath;
    this.diffLine = diffLine;
    this.lineNo = lineNo;
  }

  public boolean isInsertedLine() {
    if (this.diffLine.isEmpty())
      return false;
    if (this.diffLine.charAt(0) == '+') {
      if (this.diffLine.length() < 3)
        return true;
      // 最初が+++で始まるのは、
      // +++ b/(変更されたファイルのパス)のような時なので挿入行じゃない
      if (this.diffLine.substring(0, 3).equals("+++"))
        return false;
      return true;
    }
    return false;
  }

  public boolean isDeletedLine() {
    if (this.diffLine.isEmpty())
      return false;
    if (this.diffLine.charAt(0) == '-') {
      if (this.diffLine.length() < 3)
        return true;
      // 最初が---で始まるのは、
      // --- a/(変更されたファイルのパス)のような時なので削除行じゃない
      if (this.diffLine.substring(0, 3).equals("---"))
        return false;
      return true;
    }
    return false;
  }

  /**
   * 差分を表す文字列からコード部分を抜き出すために、インデントと末尾の改行を削除
   */
  public String toCode() {
    return this.diffLine.substring(1).trim();
  }

  // public CodeLine toCodeLine() {
  // return new CodeLine(filePath, lineNo, toCode(), relativeSourcePath);
  // }
}