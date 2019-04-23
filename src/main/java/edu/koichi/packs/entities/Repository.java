package edu.koichi.packs.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.koichi.packs.utilities.RunCommand;

public class Repository {
  public static String relativeRepositoryPath;
  public List<Commit> commits = new ArrayList<Commit>();

  public Repository(String relativeFilePath) {
    Repository.relativeRepositoryPath = relativeFilePath;
    for (Commit c : getCommits()) {
      commits.add(c);
    }
  }

  public void init() {
    RunCommand.run("git checkout master", relativeRepositoryPath);
  }

  public List<Commit> getCommits() {
    List<Commit> commits = new ArrayList<Commit>();
    String logStr = RunCommand.run("git log --oneline --pretty=format:%h,%s", Repository.relativeRepositoryPath);
    Arrays.stream(logStr.split("\n")).forEach(log -> {
      String[] splitedLog = log.split(",");
      String sha = splitedLog[0];
      String msg = splitedLog[1];
      commits.add(new Commit(sha, msg));
    });
    return commits;
  }

  public void checkout(Commit commit) {
    RunCommand.run(String.format("git checkout %s", commit.sha), Repository.relativeRepositoryPath);
  }

  public String[] getSourceFilenamesWithRelativePath() {
    // TODO: ハードコーディングで拡張子を指定しているがプロパティから読み込めるようにする
    String filenames = RunCommand.run("git ls-files *.java", Repository.relativeRepositoryPath);
    return filenames.split("\n");
  }
}