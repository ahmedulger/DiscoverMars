# Getting Started

### Reference Documentation
Used technologies
* Java 11
* No spring or similar frameworks used


### Guide
How to build and run

* If your OS is windows then you can type windows-runner.bat on command line.
* Otherwise, you should run commands below sequentially 
  * 1- mvnw clean 
  * 2- mvnw compile
  * 3- mvnw exec:java -Dexec.args=[args]. You can check windows-runner.bat file to see example argument using.
* It is hard to see output on command line. After last command completed the text below will be printed on console.
  <pre>[INFO] --- exec-maven-plugin:1.2.1:java (default-cli) @ hepsiburada ---
  1 3 N
  5 1 E
  [INFO] ------------------------------------------------------------------------
  </pre>