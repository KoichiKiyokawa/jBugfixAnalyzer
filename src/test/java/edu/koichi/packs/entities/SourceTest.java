package edu.koichi.packs.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class SourceTest {
  @Test
  public void testNormarize() {
    Source src = new Source("src/test/java/edu/koichi/packs/testClasses/Foo.java");
    src.show();
    src.normalize();
    src.show();
  }
}