package edu.koichi.packs.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RunCommand {

  /**
   * 指定したディレクトリに移動してから、コマンドを実行 Reference)
   * https://chat-messenger.com/blog/java/runtime-getruntime-exec
   *
   * @param cmd                             実行するコマンド
   * @param relatilePathForWorkingDirectory 移動先のディレクトリ
   *
   * @return 実行結果の文字列
   *
   */
  public static String run(String cmd, String relatilePathForWorkingDirectory) {
    String retStr = "";
    try {
      String[] args = cmd.split(" ");
      String LINE_SEPA = "\n";
      ProcessBuilder pb = new ProcessBuilder(args);
      pb.directory(new File(relatilePathForWorkingDirectory));
      Process p = pb.start();
      InputStream in = null;
      BufferedReader br = null;
      try {
        in = p.getInputStream();
        StringBuffer out = new StringBuffer();
        br = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null) {
          out.append(line + LINE_SEPA);
        }
        retStr = out.toString();
      } finally {
        if (br != null) {
          br.close();
        }
        if (in != null) {
          in.close();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return retStr;
  }
}