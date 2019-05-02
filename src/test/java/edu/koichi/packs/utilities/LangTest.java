package edu.koichi.packs.utilities;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.koichi.packs.utilities.Lang;

public class LangTest {
  @Test
  public void testCapitalize() {
    assertEquals("Foo", Lang.capitalize("foo"));
  }

  @Test
  public void testUnCapitalize() {
    assertEquals("bar", Lang.uncapitalize("Bar"));
  }

  @Test
  public void testIsCapital() {
    assertEquals(false, Lang.isCapital("foo"));
  }

  @Test
  public void testToggleCapital1() {
    assertEquals("Foo", Lang.toggleCapital("foo"));
  }

  @Test
  public void testToggleCapital2() {
    assertEquals("foo", Lang.toggleCapital("Foo"));
  }
}