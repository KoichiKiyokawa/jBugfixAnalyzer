package edu.koichi.packs.verifications;

import org.apache.lucene.search.spell.LevensteinDistance;

import edu.koichi.packs.entities.Repository;

public class VerifyRedundancyWithLevenshtein extends VerifyRedundancy {
  // TODO: 閾値はプロパティファイルから読み込む
  private final float thresholdScore = 0.75f;

  public VerifyRedundancyWithLevenshtein(Repository repo) {
    super(repo);
  }

  @Override
  protected boolean isIngredient(String srcline, String insertedLine) {
    LevensteinDistance lDis = new LevensteinDistance();
    return lDis.getDistance(srcline.trim(), insertedLine) > thresholdScore;
  }
}