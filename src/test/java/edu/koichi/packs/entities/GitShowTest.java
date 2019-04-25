package edu.koichi.packs.entities;

import edu.koichi.packs.common.UseTestRepo;
import edu.koichi.packs.utilities.RunCommand;

public class GitShowTest extends UseTestRepo {
  public void testDiffSize() {
    GitShow gs = new GitShow(RunCommand.run("git show", testRepoDir));
    assertEquals(12, gs.diffs.size());
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
    assertEquals("public class Test7 {", gs.diffs.get(0).toCode());
    assertEquals("public static void main(String[] args) {", gs.diffs.get(1).toCode());
    assertEquals("System.out.println(\"This is a test code7.\");", gs.diffs.get(2).toCode());
    assertEquals("System.out.println(\"This is a fixed test code7.\");", gs.diffs.get(3).toCode());
    assertEquals("}", gs.diffs.get(4).toCode());
    assertEquals("}", gs.diffs.get(5).toCode());
    assertEquals("public class Test8 {", gs.diffs.get(6).toCode());
  }

  public void testCodeLine() {
    GitShow gs = new GitShow(RunCommand.run("git show", testRepoDir));
    CodeLine deleted = gs.diffs.get(2).toCodeLine();
    CodeLine inserted = gs.diffs.get(3).toCodeLine();
    assertEquals(3, deleted.lineNo);
    assertEquals(3, inserted.lineNo);

    CodeLine deleted2 = gs.diffs.get(8).toCodeLine();
    CodeLine inserted2 = gs.diffs.get(9).toCodeLine();
    assertEquals("System.out.println(\"This is a test code8.\");", deleted2.line);
    assertEquals("System.out.println(\"This is a fixed test code8.\");", inserted2.line);
    assertEquals(3, deleted2.lineNo);
    assertEquals(3, inserted2.lineNo);
  }
}