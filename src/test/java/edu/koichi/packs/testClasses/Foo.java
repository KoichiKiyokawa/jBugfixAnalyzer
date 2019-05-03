package edu.koichi.packs.testClasses;

public class Foo {
  int x;

  void three() {
    this.x = 3;
    int y = 2;
    int yy = 4;
    System.out.println(x + y);
    System.out.println(x + yy);
  }

  void loop(int times) {
    for (int i = 0; i < times; i++) {
      System.out.println(i + " times");
    }
  }

  void hello() {
    System.out.println("Hello");
  }
}