package edu.koichi.packs.utilities;

public class RunCommand {
  public static String run(String... args) {
    // TODO: gitコマンドを打てるように

    // try {
    // Process process = new ProcessBuilder(args).start();
    // InputStream is = process.getInputStream();
    // InputStreamReader isr = new InputStreamReader(is, "UTF-8");
    // BufferedReader reader = new BufferedReader(isr);
    // StringBuilder builder = new StringBuilder();
    // int c;
    // while ((c = reader.read()) != -1) {
    // builder.append((char) c);
    // }
    // // コンソール出力される文字列の格納
    // String text = builder.toString();
    // // 終了コードの格納(0:正常終了 1:異常終了)
    // int ret = process.waitFor();
    // System.out.println(text);
    // System.out.println(ret);

    // } catch (IOException | InterruptedException e) {
    // e.printStackTrace();
    return args[0];
  }
}