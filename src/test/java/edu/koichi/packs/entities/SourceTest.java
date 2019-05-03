package edu.koichi.packs.entities;

import static org.junit.Assert.*;
import org.junit.Test;

public class SourceTest {
  @Test
  public void testNormarize() {
    Source src = new Source("src/test/java/edu/koichi/packs/testClasses/Foo.java");
    src.showStatements();
    src.normalize();
    System.out.println("---after normalize---");
    src.showStatements();
    System.out.println("---show code---");
    src.showCode();
  }
}