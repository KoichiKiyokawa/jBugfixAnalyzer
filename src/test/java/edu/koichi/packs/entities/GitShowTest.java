package edu.koichi.packs.entities;

import edu.koichi.packs.common.UseTestRepo;
import edu.koichi.packs.utilities.RunCommand;

public class GitShowTest extends UseTestRepo {
  public void testDiffSize() {
    GitShow gs = new GitShow(RunCommand.run("git show", testRepoDir));
    for (Diff d : gs.diffs) {
      System.out.println(d.diffLine);
    }
    assertEquals(gs.diffs.size(), 12);
  }

/* result of git show
example)
```
commit 0c9d31f7242ee73e4a4408fa93ed420aa6551cfa (HEAD -> master, origin/master)
Author: K.Kiyokawa <koichi20110068@gmail.com>
Date:   Wed Apr 24 23:48:28 2019 +0900

    Fix Test7 and Test8

diff --git a/src/Test7.java b/src/Test7.java
index 141bb1f..bf6a83d 100644
--- a/src/Test7.java
+++ b/src/Test7.java
@@ -1,5 +1,5 @@
 public class Test7 {
   public static void main(String[] args) {
-    System.out.println("This is a test code7.");
+    System.out.println("This is a fixed test code7.");
   }
 }
diff --git a/src/Test8.java b/src/Test8.java
index 47589aa..0222e0d 100644
--- a/src/Test8.java
+++ b/src/Test8.java
@@ -1,5 +1,5 @@
 public class Test8 {
   public static void main(String[] args) {
-    System.out.println("This is a test code8.");
+    System.out.println("This is a fixed test code8.");
   }
 }
```
  */

  public void testDiffFile() {
    GitShow gs = new GitShow(RunCommand.run("git show", testRepoDir));
    assertEquals(gs.diffs.get(0).toCode(), "public class Test7 {");
    assertEquals(gs.diffs.get(1).toCode(), "public static void main(String[] args) {");
    assertEquals(gs.diffs.get(2).toCode(), "System.out.println(\"This is a test code7.\");");
    assertEquals(gs.diffs.get(3).toCode(), "System.out.println(\"This is a fixed test code7.\");");
    assertEquals(gs.diffs.get(4).toCode(), "}");
    assertEquals(gs.diffs.get(5).toCode(), "}");
    assertEquals(gs.diffs.get(6).toCode(), "public class Test8 {");
  }
}