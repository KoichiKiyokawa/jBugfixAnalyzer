package edu.koichi.packs.entities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configration {
  public String[] includeFileExtensions; // 調査対象のファイルの拡張子 ex) .java
  public String[] exceptionOfFixWords; // バグ修正コミットと判定する際、例外として扱う単語 ex) Fixnum

  public Configration() {
    Properties properties = new Properties();
    try {
      InputStream istream = new FileInputStream("src/main/java/edu/koichi/config.properties");
      properties.load(istream);
      this.includeFileExtensions = properties.getProperty("include_file_extension").split(":");
      this.exceptionOfFixWords = properties.getProperty("exception_of_fix_message").split(":");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}