package edu.koichi.packs.entities;

import junit.framework.TestCase;

public class DiffTest extends TestCase {
  public void testIsInsertedLine() {
    Diff diff = new Diff("+package main");
    assertEquals(diff.isInsertedLine(), true);
  }

  public void testIsInsertedLineException() {
    Diff diff = new Diff("+++ b/src/test/java/edu/koichi/packs/utilities/LangTest.java");
    assertEquals(diff.isInsertedLine(), false);
  }

  public void testIsDeletedLine() {
    Diff diff = new Diff("-package main");
    assertEquals(diff.isDeletedLine(), true);
  }

  public void testIsDeletedLineException() {
    Diff diff = new Diff("--- a/src/main/java/edu/koichi/packs/entities/Commit.java");
    assertEquals(diff.isDeletedLine(), false);
  }

  public void testSeparation() {
    Diff diff = new Diff("+for (int i = 0 ; i < 10; i++ {\n");
    assertEquals(diff.toCode(), "for (int i = 0 ; i < 10; i++ {\n");
  }

  public void testSeparationWithIndent() {
    Diff diff = new Diff("+  for (int i = 0 ; i < 10; i++ {\n");
    assertEquals(diff.toCode(), "for (int i = 0 ; i < 10; i++ {\n");
  }
}