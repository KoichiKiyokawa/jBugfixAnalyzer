package edu.koichi.packs.entities;

import junit.framework.TestCase;

public class DiffTest extends TestCase {
  public void testIsInsertedLine() {
    Diff diff = new Diff("+package main");
    assertEquals(true, diff.isInsertedLine());
  }

  public void testIsInsertedLineException() {
    Diff diff = new Diff("+++ b/src/test/java/edu/koichi/packs/utilities/LangTest.java");
    assertEquals(false, diff.isInsertedLine());
  }

  public void testIsDeletedLine() {
    Diff diff = new Diff("-package main");
    assertEquals(true, diff.isDeletedLine());
  }

  public void testIsDeletedLineException() {
    Diff diff = new Diff("--- a/src/main/java/edu/koichi/packs/entities/Commit.java");
    assertEquals(false, diff.isDeletedLine());
  }

  public void testSeparation() {
    Diff diff = new Diff("+for (int i = 0 ; i < 10; i++ {\n");
    assertEquals("for (int i = 0 ; i < 10; i++ {", diff.toCode());
  }

  public void testSeparationWithIndent() {
    Diff diff = new Diff("+  for (int i = 0 ; i < 10; i++ {\n");
    assertEquals("for (int i = 0 ; i < 10; i++ {", diff.toCode());
  }
}