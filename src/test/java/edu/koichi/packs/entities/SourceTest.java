package edu.koichi.packs.entities;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

import spoon.processing.FileGenerator;
import spoon.refactoring.Refactoring;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtStatement;
import spoon.reflect.visitor.filter.LineFilter;

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

  @Ignore
  public void testvar() {
    Source src = new Source("src/test/java/edu/koichi/packs/testClasses/Foo.java");
    FileGenerator fGenerator = src.launcher.getFactory().getEnvironment().getDefaultFileGenerator();
  }

  @Test
  public void testGatheringAllVariables() {
    Source src = new Source("src/test/java/edu/koichi/packs/testClasses/Foo.java");
    // フィールドも含めた変数を抜き出すことができる
    List<CtStatement> elements = src.model.getElements(new LineFilter());
    // List<CtStatement> elements = src.model.getElements(new LineFilter());
    // elements.forEach(System.out::println);

    for (int i = 0; i < elements.size(); i++) {
      // TODO: fieldをリネームすることができない
      if (!(elements.get(i) instanceof CtLocalVariable)) {
        continue;
      }
      Refactoring.changeLocalVariableName((CtLocalVariable) elements.get(i), "$" + i);
    }
    // elements.forEach(System.out::println);
    src.launcher.getEnvironment().setAutoImports(true);
    src.model.getElements(new LineFilter()).forEach(System.out::println);
  }
}