package edu.koichi.packs.entities;

import static org.junit.Assert.*;
import org.junit.Test;

public class CodeLineTest {
  @Test
  public void testShouldIgnore() {
    CodeLine cLine = new CodeLine("src/Test10.java", 5, "}");
    assertEquals(true, cLine.shouldIgnore());
  }

  @Test
  public void testShouldNotIgnore() {
    CodeLine cLine = new CodeLine("src/Test10.java", 3, "System.out.println(\"This is a test code10.\");");
    assertEquals(false, cLine.shouldIgnore());
  }

  @Test
  public void testGetCommitMessage() {
    CodeLine cLine = new CodeLine("src/Test10.java", 3, "System.out.println(\"This is a test code10.\");");
    assertEquals("Add Test10.java", cLine.getCommitMessage());
  }
}