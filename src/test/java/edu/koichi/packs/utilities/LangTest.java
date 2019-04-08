package edu.koichi.packs.utilities;

import junit.framework.TestCase;

import edu.koichi.packs.utilities.Lang;

public class LangTest extends TestCase {
  public void testCapitalize() {
    assertEquals(Lang.capitalize("foo"), "Foo");
  }

  public void testUnCapitalize() {
    assertEquals(Lang.uncapitalize("Bar"), "bar");
  }

  public void testIsCapital() {
    assertEquals(Lang.isCapital("foo"), false);
  }

  public void testToggleCapital1() {
    assertEquals(Lang.toggleCapital("foo"), "Foo");
  }

  public void testToggleCapital2() {
    assertEquals(Lang.toggleCapital("Foo"), "foo");
  }
}