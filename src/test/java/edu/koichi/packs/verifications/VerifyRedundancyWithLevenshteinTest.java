package edu.koichi.packs.verifications;

import org.apache.lucene.search.spell.LevensteinDistance;

import edu.koichi.packs.common.UseTestRepo;

public class VerifyRedundancyWithLevenshteinTest extends UseTestRepo {
  public void testCalcLevenshteinDistance() {
    LevensteinDistance lDis = new LevensteinDistance();
    assertEquals(lDis.getDistance("hoge", "fuga"), 0.25f);
  }
}