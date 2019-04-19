package edu.koichi.packs.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.koichi.packs.utilities.RunCommand;

public class Repository {
  private String relativeRepositoryPath;
  protected List<Commit> commits = new ArrayList<Commit>();

  public Repository(String relativeFilePath) {
    this.relativeRepositoryPath = relativeFilePath;
    for (Commit c : getCommits()) {
      commits.add(c);
    }
  }

  public List<Commit> getCommits() {
    List<Commit> commits = new ArrayList<>();
    String logStr = RunCommand.run("git log --oneline", this.relativeRepositoryPath);
    Arrays.stream(logStr.split("\n")).forEach(log -> {
      String[] splitedLog = log.split(" ", 2);
      String sha = splitedLog[0];
      String msg = splitedLog[1];
      commits.add(new Commit(sha, msg, this.relativeRepositoryPath));
    });
    return commits;
  }

  public void checkout(Commit commit) {
    RunCommand.run(String.format("git checkout %s", commit.sha), relativeRepositoryPath);
  }
}