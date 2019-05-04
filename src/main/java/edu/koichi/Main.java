package edu.koichi;

import edu.koichi.packs.entities.*;
import edu.koichi.packs.verifications.VerifyRedundancy;

public class Main {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    System.out.println(args[0]);
    Repository repo = new Repository(args[0]);
    VerifyRedundancy vRedundancy = new VerifyRedundancy(repo);
    vRedundancy.verify();

    long end = System.currentTimeMillis();
    System.out.println("------------------");
    System.out.println((end - start) + "ms");
  }
}