package edu.koichi.packs.verifications;

import org.apache.lucene.search.spell.LevensteinDistance;
import static org.junit.Assert.*;
import org.junit.Test;

import edu.koichi.packs.common.UseTestRepo;
import edu.koichi.packs.utilities.RunCommand;
import edu.koichi.packs.verifications.VerifyRedundancyWithLevenshtein;

public class VerifyRedundancyWithLevenshteinTest extends UseTestRepo {
  @Test
  public void testCalcLevenshteinDistance() {
    LevensteinDistance lDis = new LevensteinDistance();
    assertEquals(0.25f, lDis.getDistance("hoge", "fuga"), 0.0001f);
  }

  @Test
  public void testCalcLevenshteinDistance2() {
    LevensteinDistance lDis = new LevensteinDistance();
    assertEquals(0.75f, lDis.getDistance("fugu", "fuga"), 0.0001f);
  }

  @Test
  public void testVerify() {
    VerifyRedundancyWithLevenshtein vrwl = new VerifyRedundancyWithLevenshtein(repo);
    vrwl.verify();
    final int allBugfixCommitCount = 3;
    assertEquals(allBugfixCommitCount, vrwl.bugfixCommitCount);

    final int bugfixInsertedLineCount = 4;
    assertEquals(bugfixInsertedLineCount, vrwl.insertedBugfixLineCount);

    final int hasIngredientInsertedLines = 4;
    assertEquals(hasIngredientInsertedLines, vrwl.hasIngredientInsertedLines);
  }
}