package edu.koichi.packs.entities;

/**
 * ex1) - if (hoge)
 *
 * ex2) + hoge.fuga();
 */
public class Diff {
  public String diffLine;

  public Diff(String diffLine) {
    this.diffLine = diffLine;
  }

  public boolean isInsertedLine() {
    if (this.diffLine.isEmpty())
      return false;
    if (this.diffLine.charAt(0) == '+') {
      if (this.diffLine.length() < 3)
        return true;
      // 最初が+++で始まるのは、
      // +++ b/(変更されたファイルのパス)のような時なので挿入行じゃない
      if (this.diffLine.substring(0, 2) == "+++")
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
      if (this.diffLine.substring(0, 2) == "---")
        return false;
      return true;
    }
    return false;
  }

  /**
   * 差分を表す文字列からコード部分を抜き出す
   */
  public String toCode() {
    try {
      for (int i = 1; i < this.diffLine.length(); i++) {
        if (this.diffLine.charAt(i) == ' ') {
          continue;
        } else {
          return this.diffLine.substring(i);
        }
      }
    } catch (StringIndexOutOfBoundsException e) {
      e.printStackTrace();
    }
    return "";
  }
}