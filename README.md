# Getting Started

### Reference Documentation
Used technologies
* Java 11
* No spring or similar frameworks used


### Guide
How to build and run

* If your OS is windows then you can type windows-runner.bat on command line.
* If your OS is based on linux kernel then you can type linux-runner.sh on terminal.
* Otherwise, you should run commands below sequentially 
  * 1- mvnw clean 
  * 2- mvnw compile
  * 3- mvnw exec:java -Dexec.args=[args]. You can check windows-runner.bat file to see example argument using.
* Result will be written on console like below.
  <pre>
  *************************
  ******** RESULT *********
  *************************
  1 3 N
  5 1 E
  *************************
  *************************
  </pre>
