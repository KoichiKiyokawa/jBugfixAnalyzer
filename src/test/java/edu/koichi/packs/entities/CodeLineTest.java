package edu.koichi.packs.entities;

import java.io.File;

import junit.framework.TestCase;

public class CodeLineTest extends TestCase {
  public void test() {
    File file = new File("result/res.txt");
    assertEquals(file.toPath().toString(), "result/res.txt");
  }

  public void test1() {
    File file = new File("src/main/java/edu/koichi/Main.java");
    assertEquals(file.getParent().toString(), "src/main/java/edu/koichi");
  }

  public void testShouldIgnore() {
    File file = new File("../jBugfixAnalyzer-test/src/Test10.java");
    CodeLine cLine = new CodeLine(file, 5, "}", "src/Test10.java");
    assertEquals(cLine.shouldIgnore(), true);
  }

  public void testShouldNotIgnore() {
    File file = new File("../jBugfixAnalyzer-test/src/Test10.java");
    CodeLine cLine = new CodeLine(file, 3, "System.out.println(\"This is a test code10.\");", "src/Test10.java");
    assertEquals(cLine.shouldIgnore(), false);
  }

  public void testGetCommitMessage() {
    File file = new File("../jBugfixAnalyzer-test/src/Test10.java");
    CodeLine cLine = new CodeLine(file, 3, "System.out.println(\"This is a test code10.\");", "src/Test10.java");
    assertEquals(cLine.getCommitMessage(), "Add Test10.java");
  }
}