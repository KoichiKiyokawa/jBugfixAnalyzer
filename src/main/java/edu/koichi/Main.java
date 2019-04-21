package edu.koichi;

import edu.koichi.packs.entities.*;
import edu.koichi.packs.verifications.VerifyRedundancy;

public class Main {
  public static void main(String args[]) {
    Repository repo = new Repository("../jBugfixAnalyzer-test");
    VerifyRedundancy vRedundancy = new VerifyRedundancy(repo);
    vRedundancy.verify();
  }
}