/**
 * result of git show
 *
 * <pre>
 * commit f2279a7fe557e6109bfcbbd7e7b68076271cc2e6
 * Author: K.Kiyokawa <koichi20110068@gmail.com>
 * Date:   Mon Apr 22 22:33:08 2019 +0900
 *
 *     Add Test10.java
 *
 * diff --git a/src/Test10.java b/src/Test10.java
 * new file mode 100644
 * index 0000000..3881313
 * --- /dev/null
 * +++ b/src/Test10.java
 * &#64;@ -0,0 +1,5 @@ (startCodeLine 1)
 * +public class Test10 { (relativeCodeLine 0)
 * +  public static void main(String[] args) { (relativeCodeLine 1)
 * -    System.out.println("This is a test code10."); (relativeCodeLine 2) * 削除行のときはカウントを進めない。
 * +    System.out.println("This is a test code10."); (relativeCodeLine 2)
 * +  }
 * +}
 * </pre>
 *
 * startCodeLine + relativeCodeLineが実際のソースコードにおける行数になる