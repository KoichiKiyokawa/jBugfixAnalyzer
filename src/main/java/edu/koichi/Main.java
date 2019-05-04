package edu.koichi;

import edu.koichi.packs.entities.*;
import edu.koichi.packs.verifications.VerifyRedundancy;

public class Main {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    String repoPath = args[0];
    System.out.println(repoPath);
    Repository repo = new Repository(repoPath);
    new VerifyRedundancy(repo).verify();

    long end = System.currentTimeMillis();
    System.out.println("------------------");
    System.out.println((end - start) + "ms");
  }
}