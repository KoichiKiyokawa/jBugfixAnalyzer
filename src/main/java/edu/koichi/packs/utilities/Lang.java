package edu.koichi.packs.utilities;

public class Lang {
  public static String capitalize(String str) {
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }

  public static boolean isCapital(String str) {
    return Character.isUpperCase(str.charAt(0));
  }

  public static String uncapitalize(String str) {
    return str.substring(0, 1).toLowerCase() + str.substring(1);
  }

  public static String toggleCapital(String str) {
    if (isCapital(str)) {
      return uncapitalize(str);
    } else {
      return capitalize(str);
    }
  }
}