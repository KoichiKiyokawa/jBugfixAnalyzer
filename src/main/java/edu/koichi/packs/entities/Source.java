package edu.koichi.packs.entities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import spoon.Launcher;
import spoon.refactoring.CtRenameLocalVariableRefactoring;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.LineFilter;
import spoon.reflect.visitor.filter.TypeFilter;

public class Source {
  public String path;
  public Launcher launcher = new Launcher();
  public CtModel model;

  public Source(String path) {
    this.path = path;
    this.launcher.addInputResource(this.path);
    this.launcher.buildModel();
    this.model = launcher.getModel();
  }

  /**
   * 変数の正規化を行う
   */
  public void normalize() {
    // ローカル変数を抜き出すフィルター
    TypeFilter filterLocalVar = new TypeFilter(CtLocalVariable.class) {
      public boolean matches(CtLocalVariable var) {
        CtTypeReference type = var.getType();
        return type.isPrimitive() || type.isClass();
      }
    };

    List<CtLocalVariable> vars = this.model.getElements(filterLocalVar);
    vars.forEach(System.out::println);
    int i = 0;
    for (CtLocalVariable var : vars) {
      new CtRenameLocalVariableRefactoring().setTarget(var).setNewName("$" + i++).refactor();
    }
  }

  public List<CtStatement> getStatements() {
    return this.model.getElements(new LineFilter());
  }

  public void showStatements() {
    for (CtElement elem : this.getStatements()) {
      System.out.println(elem);
    }
  }

  public void showCode() {
    System.out.println(this.launcher.getFactory().Code().toString());
  }

  public void writeCode() {
    // 一時ファイルに書き出し
    try {
      File tmpDir = new File("tmp/");
      if (!tmpDir.exists()) {
        tmpDir.mkdir();
      }
      File file = new File("tmp/tmp.txt");
      if (!file.exists()) {
        file.createNewFile();
      }
      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

      for (CtStatement statement : this.model.getElements(new LineFilter())) {
        pw.println(statement);
      }

      pw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}