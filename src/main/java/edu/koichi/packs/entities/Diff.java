package edu.koichi.packs.entities;

/**
 * ex1) - if (hoge) ex2) + hoge.fuga();
 */
public class Diff {
  public String diffLine;

  public Diff(String diffLine) {
    this.diffLine = diffLine;
  }

  public boolean isInsertedLine() {
    if (this.diffLine.substring(0, 2) == "+++") {
      // 最初が+++で始まるのは、
      // +++ b/(変更されたファイルのパス)のような時
      return false;
    }
    return this.diffLine.charAt(0) == '+';
  }

  public boolean isdeletedLine() {
    if (this.diffLine.substring(0, 2) == "---") {
      // 最初が---で始まるのは、
      // --- a/(変更されたファイルのパス)のような時
      return false;
    }
    return this.diffLine.charAt(0) == '-';
  }

  public String toString() {
    for (int i = 1; i < this.diffLine.length(); i++) {
      if (this.diffLine.charAt(i) == ' ') {
        continue;
      } else {
        return this.diffLine.substring(i);
      }
    }
    return "";
  }
}