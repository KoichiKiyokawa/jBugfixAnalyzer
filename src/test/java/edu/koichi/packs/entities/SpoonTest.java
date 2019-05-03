package edu.koichi.packs.entities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

import spoon.Launcher;
import spoon.refactoring.CtRenameLocalVariableRefactoring;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.LineFilter;
import spoon.reflect.visitor.filter.TypeFilter;

public class SpoonTest {
  @Test
  public void test() {
    Launcher launcher = new Launcher();
    launcher.addInputResource("../jBugfixAnalyzer-test/src/Test1.java");
    launcher.buildModel();
    CtModel model = launcher.getModel();
    assertTrue(model.isBuildModelFinished());

    Collection<CtPackage> packs = model.getAllPackages();
    assertEquals(1, packs.size());
  }

  @Test
  public void test2() {
    try {
      CtClass klass = Launcher.parseClass(readAll("../jBugfixAnalyzer-test/src/Test9.java"));
      assertEquals(1, klass.getMethods().size());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String readAll(String path) throws IOException {
    return Files.lines(Paths.get(path)).reduce("", (prev, line) -> prev + line + System.getProperty("line.separator"));
  }

  @Test
  public void test3() {
    CtClass klass = Launcher.parseClass("class Hoge { int x; private void hello(){System.out.println(\"Hello\")}}");
    // klass.getMethods().forEach(m -> System.out.println(m));
    assertEquals(1, klass.getMethods().size());
  }

  @Test
  public void testNormalize() {
    Launcher launcher = new Launcher();
    launcher.addInputResource("../jBugfixAnalyzer-test/src/Test1.java");
    launcher.buildModel();
    CtModel model = launcher.getModel();
    List<CtStatement> elements = model.getElements(new LineFilter());
    elements.forEach(System.out::println);
  }

  @Test
  public void testFoo() {
    Launcher launcher = new Launcher();
    launcher.addInputResource("src/test/java/edu/koichi/packs/testClasses/Foo.java");
    launcher.buildModel();
    CtModel model = launcher.getModel();
    List<CtStatement> elements = model.getElements(new LineFilter());
    elements.forEach(System.out::println);
  }

  @Test
  public void testRenameVar() {
    Launcher launcher = new Launcher();
    launcher.addInputResource("src/test/java/edu/koichi/packs/testClasses/Foo.java");
    launcher.buildModel();

    TypeFilter filterLocalVar = new TypeFilter(CtLocalVariable.class) {
      public boolean matches(CtLocalVariable var) {
        CtTypeReference type = var.getType();
        return type.isPrimitive() || type.isClass();
      }
    };
    CtModel model = launcher.getModel();
    List<CtLocalVariable> vars = model.getElements(filterLocalVar);
    vars.forEach(System.out::println);
    int i = 0;
    for (CtLocalVariable var : vars) {
      new CtRenameLocalVariableRefactoring().setTarget(var).setNewName("$" + i++).refactor();
    }

    System.out.println(("---after refactoring---"));
    List<CtElement> elements = new ArrayList<>();
    i = 0;
    for (CtElement elem : model.getElements(new LineFilter())) {
      if (elem.toString().equals("super()")) continue;
      System.out.println("" + i++ + elem);
    }
    System.out.println("size of elements is " + elements.size());
    // assertEquals("int $0 = 2", vars.get(0).toString());
  }
}
