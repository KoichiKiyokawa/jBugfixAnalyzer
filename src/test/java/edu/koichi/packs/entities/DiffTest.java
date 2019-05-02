package edu.koichi.packs.entities;

import static org.junit.Assert.*;
import org.junit.Test;

public class DiffTest {
  @Test
  public void testIsInsertedLine() {
    Diff diff = new Diff("+package main");
    assertEquals(true, diff.isInsertedLine());
  }

  @Test
  public void testIsInsertedLineException() {
    Diff diff = new Diff("+++ b/src/test/java/edu/koichi/packs/utilities/LangTest.java");
    assertEquals(false, diff.isInsertedLine());
  }

  @Test
  public void testIsDeletedLine() {
    Diff diff = new Diff("-package main");
    assertEquals(true, diff.isDeletedLine());
  }

  @Test
  public void testIsDeletedLineException() {
    Diff diff = new Diff("--- a/src/main/java/edu/koichi/packs/entities/Commit.java");
    assertEquals(false, diff.isDeletedLine());
  }

  @Test
  public void testSeparation() {
    Diff diff = new Diff("+for (int i = 0 ; i < 10; i++ {\n");
    assertEquals("for (int i = 0 ; i < 10; i++ {", diff.toCode());
  }

  @Test
  public void testSeparationWithIndent() {
    Diff diff = new Diff("+  for (int i = 0 ; i < 10; i++ {\n");
    assertEquals("for (int i = 0 ; i < 10; i++ {", diff.toCode());
  }
}