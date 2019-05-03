package edu.koichi.packs.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * result of git show
 *
 * <pre>
 * commit f2279a7fe557e6109bfcbbd7e7b68076271cc2e6
 * Author: K.Kiyokawa <koichi20110068@gmail.com>
 * Date:   Mon Apr 22 22:33:08 2019 +0900
 *
 *     Add Test10.java
 *
 * diff --git a/src/Test10.java b/src/Test10.java
 * new file mode 100644
 * index 0000000..3881313
 * --- /dev/null
 * +++ b/src/Test10.java
 * &#64;@ -0,0 +1,5 @@ (startCodeLine 1)
 * +public class Test10 { (relativeCodeLine 0)
 * +  public static void main(String[] args) { (relativeCodeLine 1)
 * -    System.out.println("This is a test code10."); (relativeCodeLine 2) * 削除行のときはカウントを進めない。
 * +    System.out.println("This is a test code10."); (relativeCodeLine 2)
 * +  }
 * +}
 * </pre>
 *
 * startCodeLine + relativeCodeLineが実際のソースコードにおける行数になる
 */
public class GitShow {
  private String filePath = "";
  private int startCodeLine = 0;
  private int relativeCodeLine = 0;
  private boolean isCode = false;
  public List<Diff> diffs = new ArrayList<>();

  public GitShow(String gitShow) {
    for (String gitShowLine : gitShow.split("\n")) {
      analyze(gitShowLine);
    }
  }

  private void analyze(String gitShowLine) {
    if (gitShowLine.length() >= 4) {
      if (gitShowLine.substring(0, 4).equals("diff")) {
        // 別ファイルの差分表示が始まるので初期状態に戻しておく
        this.isCode = false;
        this.relativeCodeLine = 0;
      }
    }

    if (gitShowLine.length() >= 6) {
      if (gitShowLine.substring(0, 6).equals("+++ b/")) {
        this.filePath = gitShowLine.substring(6); // length(+++ b/) = 6
      }
    }

    if (gitShowLine.length() >= 2) {
      if (gitShowLine.substring(0, 2).equals("@@")) {
        setStartLineFromHunk(gitShowLine);
        this.isCode = true; // すぐ下からコードが書かれるのでtrueにする
        return; // @@ -0,0 +1,5 @@ の行自体はコードではないのでreturn
      }
    }

    if (this.isCode) {
      Diff diff = new Diff(this.filePath, gitShowLine, this.relativeCodeLine + this.startCodeLine);
      this.diffs.add(diff);
      if (!diff.isDeletedLine())
        this.relativeCodeLine++;
    }
  }

  /**
   * ex) @@ -0,0 +1,5 @@ -> 1
   *
   * @param hunk @@ -0,0 +1,5 @@
   */
  private void setStartLineFromHunk(String hunk) {
    this.startCodeLine = Integer.parseInt(hunk.split(" ")[2].split(",")[0].substring(1));
  }
}