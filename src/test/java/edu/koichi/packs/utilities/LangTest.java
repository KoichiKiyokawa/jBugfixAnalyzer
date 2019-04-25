package edu.koichi.packs.utilities;

import junit.framework.TestCase;

import edu.koichi.packs.utilities.Lang;

public class LangTest extends TestCase {
  public void testCapitalize() {
    assertEquals("Foo", Lang.capitalize("foo"));
  }

  public void testUnCapitalize() {
    assertEquals("bar", Lang.uncapitalize("Bar"));
  }

  public void testIsCapital() {
    assertEquals(false, Lang.isCapital("foo"));
  }

  public void testToggleCapital1() {
    assertEquals("Foo", Lang.toggleCapital("foo"));
  }

  public void testToggleCapital2() {
    assertEquals("foo", Lang.toggleCapital("Foo"));
  }
}