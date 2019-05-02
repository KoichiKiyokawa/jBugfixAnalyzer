package edu.koichi.packs.entities;

import java.util.List;

public class CodeBlock {
  public List<CodeLine> codeLines;

  public CodeBlock(List<CodeLine> codeLines) {
    this.codeLines = codeLines;
  }

  // public CodeBlock normalize() {
  //   this.codeLines
  // }
}