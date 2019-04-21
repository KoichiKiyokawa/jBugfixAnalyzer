# init.rbとREADME.md以外を削除
# .gitも削除される
system "ls -a | grep -v -E 'init.rb|README.md$' | xargs rm -r"

system("
  git init;
  git remote add origin git@github.com:KoichiKiyokawa/jBugfixAnalyzer-test.git;
  git add .;
  git commit -m 'Initial commit'
")

("A".."Z").each do |alphabet|
  system("
    echo 'test#{alphabet}' > test#{alphabet}.txt;
    git add test#{alphabet}.txt;
    git commit -m 'Add test#{alphabet}'
  ")
end

system "mkdir src"
(1..10).each do |i|
  java_source = <<-EOS
public class Test#{i} {
  public static void main(String args[]) {
    System.out.println("This is a test code#{i}.");
  }
}
  EOS

  puts java_source
  File.open("./src/Test#{i}.java", "w") do |f|
    f.puts(java_source)
  end
  system("
    git add src/Test#{i}.java;
    git commit -m 'Add Test#{i}.java'
  ")
end

# generate bugfix commit
edited_java_source = <<-EOS
public class Test10 {
  public static void main(String args[]) {
    System.out.println("This is a test code10.");
    System.out.println("This is a test code10.");
  }
}
EOS
File.open("./src/Test10.java", "w") do |f|
  f.puts(edited_java_source)
end
system("
  git add src/Test10.java;
  git commit -m 'Fix Test10.java'
")
