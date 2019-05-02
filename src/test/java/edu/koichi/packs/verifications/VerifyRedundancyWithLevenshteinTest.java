package edu.koichi.packs.verifications;

import org.apache.lucene.search.spell.LevensteinDistance;
import static org.junit.Assert.*;
import org.junit.Test;

import edu.koichi.packs.common.UseTestRepo;

public class VerifyRedundancyWithLevenshteinTest extends UseTestRepo {
  @Test
  public void testCalcLevenshteinDistance() {
    LevensteinDistance lDis = new LevensteinDistance();
    assertEquals(0.25f, lDis.getDistance("hoge", "fuga"), 0.0001f);
  }
}