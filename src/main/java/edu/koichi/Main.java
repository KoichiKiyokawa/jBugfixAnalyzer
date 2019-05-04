package edu.koichi;

import edu.koichi.packs.entities.*;
import edu.koichi.packs.verifications.VerifyRedundancy;
import edu.koichi.packs.verifications.VerifyRedundancyWithLevenshtein;;

public class Main {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    String repoPath = args[0];
    String verificationType = "not selected";
    System.out.println("repoPath : " + repoPath);
    if (args.length >= 2) {
      verificationType = args[1];
      System.out.println("verificationType : " + verificationType);
    }
    Repository repo = new Repository(repoPath);
    switch (verificationType) {
    case "VerifyRedundancy":
      new VerifyRedundancy(repo).verify();
      break;
    case "VerifyRedundancyWithLevenshtein":
      new VerifyRedundancyWithLevenshtein(repo).verify();
      break;
    default:
      new VerifyRedundancy(repo).verify();
    }

    long end = System.currentTimeMillis();
    System.out.println("------------------");
    System.out.println((end - start) + "ms");
  }
}