# init.rbとREADME.md以外を削除
# .gitも削除される
system "ls -a | grep -v -E 'init.rb|README.md$' | xargs rm -r"
system("
  git init;
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
