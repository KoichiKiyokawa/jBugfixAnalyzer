# jBugfixAnalyzer

[![Build Status](https://travis-ci.org/KoichiKiyokawa/jBugfixAnalyzer.svg?branch=master)](https://travis-ci.org/KoichiKiyokawa/jBugfixAnalyzer)

# Dependencies

- Maven
- Java 1.8.\*
- Git
- Ruby (to generate test repository)

# How to start

```
git clone git@github.com:KoichiKiyokawa/jBugfixAnalyzer.git
cd jBugfixAnalyzer
mvn compile
java -cp target/classes edu.koichi.Main <path to directory you want to analyze>
```

You can analyze bugfix commit.
