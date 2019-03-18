package edu.koichi.packs.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.koichi.packs.utilities.RunCommand;

public class Repository {
  public String relativeFilePath;

  public Repository(String relativeFilePath) {
    this.relativeFilePath = relativeFilePath;
  }

  public List<Commit> getCommits() {
    List<Commit> commits = new ArrayList<>();
    // String logStr = RunCommand.run("git", "log", "--oneline");
    String logStr = "- hoge();\n+ fuga();"; // for test
    Arrays.stream(logStr.split("\n")).forEach(log -> {
      String[] splitedLog = log.split(" ");
      String sha = splitedLog[0];
      String msg = splitedLog[1];
      commits.add(new Commit(sha, msg));
    });
    return commits;
  }
}