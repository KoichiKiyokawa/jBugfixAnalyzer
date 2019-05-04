package edu.koichi.packs.processors;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.declaration.CtElement;
import spoon.refactoring.CtRenameLocalVariableRefactoring;
import spoon.refactoring.RefactoringException;

public class NormalizeProcessor extends AbstractProcessor<CtElement> {
  private int i = 0;

  @Override
  public boolean isToBeProcessed(CtElement candidate) {
    return candidate instanceof CtLocalVariable;
  }

  @Override
  public void process(CtElement candidate) {
    if (!this.isToBeProcessed(candidate)) {
      return;
    }

    try {
      new CtRenameLocalVariableRefactoring().setTarget((CtLocalVariable) candidate).setNewName("$" + i++).refactor();
    } catch (RefactoringException e) {
      e.printStackTrace();
    }
  }
}