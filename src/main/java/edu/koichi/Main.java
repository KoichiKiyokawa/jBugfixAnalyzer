package edu.koichi;

import edu.koichi.packs.entities.*;
import edu.koichi.packs.verifications.VerifyRedundancy;
import edu.koichi.packs.verifications.VerifyRedundancyWithLevenshtein;;

public class Main {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    String repoPath = args[0];
    String verificationType = args[1];
    System.out.println("repoPath : " + repoPath);
    System.out.println("verificationType : " + verificationType);
    Repository repo = new Repository(repoPath);

    switch (verificationType) {
    case "VerifyRedundancy":
      new VerifyRedundancy(repo).verify();
    case "VerifyRedundancyWithLevenshtein":
      new VerifyRedundancyWithLevenshtein(repo).verify();
    default:
      new VerifyRedundancy(repo).verify();
    }

    long end = System.currentTimeMillis();
    System.out.println("------------------");
    System.out.println((end - start) + "ms");
  }
}