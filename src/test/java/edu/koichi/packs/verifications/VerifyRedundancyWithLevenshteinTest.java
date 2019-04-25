package edu.koichi.packs.verifications;

import org.apache.lucene.search.spell.LevensteinDistance;

import edu.koichi.packs.common.UseTestRepo;

public class VerifyRedundancyWithLevenshteinTest extends UseTestRepo {
  public void testCalcLevenshteinDistance() {
    LevensteinDistance lDis = new LevensteinDistance();
    assertEquals(0.25f, lDis.getDistance("hoge", "fuga"));
  }
}