package edu.koichi.packs.entities;

import junit.framework.TestCase;

public class CodeLineTest extends TestCase {
  public void testShouldIgnore() {
    CodeLine cLine = new CodeLine("src/Test10.java", 5, "}");
    assertEquals(cLine.shouldIgnore(), true);
  }

  public void testShouldNotIgnore() {
    CodeLine cLine = new CodeLine("src/Test10.java", 3, "System.out.println(\"This is a test code10.\");");
    assertEquals(cLine.shouldIgnore(), false);
  }

  public void testGetCommitMessage() {
    CodeLine cLine = new CodeLine("src/Test10.java", 3, "System.out.println(\"This is a test code10.\");");
    assertEquals(cLine.getCommitMessage(), "Add Test10.java");
  }
}